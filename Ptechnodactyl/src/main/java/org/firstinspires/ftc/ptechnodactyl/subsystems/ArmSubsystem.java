package org.firstinspires.ftc.ptechnodactyl.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

public class ArmSubsystem implements Subsystem, Loggable {

    @Config
    public static class ArmConstants {

        //ticks per motor
        public static double ENCODER_TICKS_TO_RAD = 28 / (2.0 * Math.PI);
        // motor ratio * gear ratio * 2PI
        public static double PITCH_RAD_TO_RAD = 50.9 * (28.0 / 10);

        // axon bevels ratio / pulley radius
        public static double SLIDE_RAD_TO_M = (52.0 / 18) / 0.01;

        public static double WRIST_UP_POSITION = 1;
        public static double WRIST_DOWN_POSITION = 0.64;
        public static double WRIST_SPEC_POSITION = 0.78;

        public static double PITCH_ARM_MIN = -0.05;
        public static double PITCH_ARM_MAX = Math.PI / 1.8;
        public static double SLIDE_M_MIN = -0.1;
        public static double SLIDE_M_MAX = 2 * 0.36;

        public static double SLIDE_MOTOR_KG = 0.5;
        public static Vector2d SLIDE_MOTOR_OFFSET = new Vector2d(0.067, 0.03);

        public static double WRIST_ASM_KG = 0.55;

        public static Vector2d WRIST_ASM_OFFSET = new Vector2d(0.44, 0);

        public static double FORCE_GRAVITY = 9.81;

        public static double getSlideTorque(double angle) {
            return (
                (Math.sin(angle + WRIST_ASM_OFFSET.angle()) * WRIST_ASM_KG * FORCE_GRAVITY) /
                SLIDE_RAD_TO_M
            );
        }

        public static double getArmTorque(double angle, double slidePos) {
            //apply coordinate transforms
            Vector2d wrist = WRIST_ASM_OFFSET.plus(new Vector2d(slidePos, 0)).rotated(angle);
            Vector2d slide = SLIDE_MOTOR_OFFSET.rotated(angle);

            //torque is force times lever arm. Find both lever arms and then the force being applied to both
            return (
                ((wrist.getX() * WRIST_ASM_KG + slide.getX() * SLIDE_MOTOR_KG) * FORCE_GRAVITY) /
                PITCH_RAD_TO_RAD
            );
        }

        public static double KT = 0.02; //determined from spec sheet
        public static double R = 2.4; //determined from spec sheet;
        public static double KV = 0; //determined from spec sheet

        public static Vector2d SPEC = new Vector2d(0.46, 0.6);

        public static Vector2d PICKUP = new Vector2d(0.75, 0.0);

        public static Vector2d SCORE = new Vector2d(-0.2, 1.3);

        public static Vector2d RETRACT = new Vector2d(0.4, 0.0);

        public static PIDCoefficients pitchPID = new PIDCoefficients(1, 0.008, 0.05);

        public static PIDCoefficients slidePID = new PIDCoefficients(2.8, 0.2, 0.0);
    }

    private final PIDFController pitchPidController, slidePidController;

    private final DcMotorEx pitchMotor, slideMotor;

    @Log.Number(name = "slide power")
    public double slidePower = 0;

    @Log.Number(name = "pitch power")
    public double pitchPower = 0;

    @Log.Number(name = "angle")
    public double angleM = 0;

    @Log.Number(name = "current")
    public double desiredCurrent = 0;

    @Log.Number(name = "slide distance")
    public double slideDistance = 0;

    private final Servo wristServo;

    @Log(name = "wristPosition")
    public double wristPosition = 0;

    private double pitchOffset = 0.15, slideOffset = 0.04;

    public ArmSubsystem(
        EncodedMotor<DcMotorEx> pitchMotor,
        EncodedMotor<DcMotorEx> slideMotor,
        Servo wristServo
    ) {
        this.pitchMotor = pitchMotor.getRawMotor(DcMotorEx.class);
        this.pitchMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.slideMotor = slideMotor.getRawMotor(DcMotorEx.class);
        this.wristServo = wristServo;

        pitchOffset = getArmAngleRad();
        slideOffset = getSlidePositionM();

        pitchPidController = new PIDFController(
            ArmConstants.pitchPID,
            0,
            0,
            0,
            (ticks, velocity) -> {
                double angle = getArmAngleRad();
                double slidePos = getSlidePositionM();

                double desiredCurrent =
                    ArmConstants.getArmTorque(angle, slidePos) / ArmConstants.KT;

                return (
                    (desiredCurrent * ArmConstants.R +
                        (ArmConstants.KV * velocity) / ArmConstants.ENCODER_TICKS_TO_RAD) /
                    12
                );
            }
        );

        slidePidController = new PIDFController(
            ArmConstants.slidePID,
            0,
            0,
            0.08,
            (ticks, velocity) -> {
                double angle = getArmAngleRad();

                double desiredCurrent = ArmConstants.getSlideTorque(angle) / ArmConstants.KT;

                return (
                    (desiredCurrent * ArmConstants.R +
                        (ArmConstants.KV * velocity) / ArmConstants.ENCODER_TICKS_TO_RAD) /
                    12
                );
            }
        );
        setPitchPos(0);
        setSlidePos(0);
    }

    @Override
    public void periodic() {
        pitchPower = Range.clip(
            pitchPidController.update(getArmAngleRad(), getArmAngleVelRadS()),
            -0.42,
            0.6
        );
        slidePower = Range.clip(
            slidePidController.update(getSlidePositionM(), getSlideVelMS()),
            -0.9,
            0.9
        );

        pitchMotor.setPower(pitchPower);

        slideMotor.setPower(slidePower);
        angleM = getArmAngleRad();
        slideDistance = getSlidePositionM();
        desiredCurrent = ArmConstants.getArmTorque(angleM, slideDistance) / ArmConstants.KT;
    }

    public void setPitchPos(double angle) {
        pitchPidController.setTargetPosition(
            Range.clip(angle, ArmConstants.PITCH_ARM_MIN, ArmConstants.PITCH_ARM_MAX)
        );
    }

    public void setArmPos(Vector2d pos) {
        setPitchPos(pos.angle());
        setSlidePos(
            Range.clip(
                pos.norm() - ArmConstants.WRIST_ASM_OFFSET.norm(),
                ArmConstants.SLIDE_M_MIN,
                ArmConstants.SLIDE_M_MAX
            )
        );
    }

    public void setSlidePos(double slidePos) {
        slidePidController.setTargetPosition(Range.clip(slidePos, 0, 0.7));
    }

    public double getArmAngleRad() {
        // our horizontal value starts at ARM_HORIZONTAL, so we need to
        // subtract it
        return (
            pitchMotor.getCurrentPosition() /
                ArmConstants.ENCODER_TICKS_TO_RAD /
                ArmConstants.PITCH_RAD_TO_RAD -
            pitchOffset
        );
    }

    public double getArmAngleVelRadS() {
        return (
            pitchMotor.getVelocity() /
            ArmConstants.ENCODER_TICKS_TO_RAD /
            ArmConstants.PITCH_RAD_TO_RAD
        );
    }

    public double getSlidePositionM() {
        return (
            slideMotor.getCurrentPosition() /
                ArmConstants.ENCODER_TICKS_TO_RAD /
                ArmConstants.SLIDE_RAD_TO_M -
            slideOffset
        );
    }

    public double getSlideVelMS() {
        return (
            slideMotor.getVelocity() /
            ArmConstants.ENCODER_TICKS_TO_RAD /
            ArmConstants.SLIDE_RAD_TO_M
        );
    }

    public boolean isAtTargetPosition() {
        return (
            Math.abs(pitchPidController.getLastError()) < 0.3 &&
            Math.abs(slidePidController.getLastError()) < 0.08
        );
    }

    private void setWristPosition(double d) {
        wristServo.setPosition(d);
        wristPosition = d;
    }

    public void wristUp() {
        setWristPosition(ArmConstants.WRIST_UP_POSITION);
    }

    public void wristDown() {
        setWristPosition(ArmConstants.WRIST_DOWN_POSITION);
    }
    public void wristSpec(){setWristPosition(ArmConstants.WRIST_SPEC_POSITION);}
}
