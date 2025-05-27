package org.firstinspires.ftc.ptechnodactyl.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.ptechnodactyl.Hardware;

@Config
public class ClawSubsystem implements Subsystem {

    private Servo clawServo, pivotServo;

    @Log(name = "clawPosition")
    public double clawPosition = 0;
    @Log(name = "pivotPosition")
    public double pivotPosition = 0;
    public static double CLAW_OPEN_POSITION = 0.54;
    public static double CLAW_ALIGN_POSITION = 0.795;
    public static double CLAW_CLOSE_POSITION = 0.82;
    public static double PIVOT_NEUTRAL_POSITION = 0.5;
    public static double PIVOT_LEFT_45_POSITION = 0.33;
    public static double PIVOT_RIGHT_45_POSITION = 0.67;
    public static double PIVOT_90_POSITION = 0.16;
    public static double PIVOT_ALIGN = 1;
    private void setClawPosition(double d) {
        clawServo.setPosition(d);
        clawPosition = d;
    }
    private void setPivotPosition(double d) {
        pivotServo.setPosition(d);
        pivotPosition = d;
    }

    public ClawSubsystem(Servo clawServo, Servo pivotServo) {
        this.clawServo = clawServo;
        this.pivotServo = pivotServo;
    }


    public void openClaw() {
        setClawPosition(CLAW_OPEN_POSITION);
    }
    public void alignClaw() {
        setClawPosition(CLAW_ALIGN_POSITION);
        setPivotPosition(PIVOT_ALIGN);
    }

    public void closeClaw() {
        setClawPosition(CLAW_CLOSE_POSITION);
    }
    public void pivotneutral(){
        setPivotPosition(PIVOT_NEUTRAL_POSITION);
    }
    public void pivotleft45(){
        setPivotPosition(PIVOT_LEFT_45_POSITION);
    }
    public void pivotright45(){
        setPivotPosition(PIVOT_RIGHT_45_POSITION);
    }
    public void pivot90(){
        setPivotPosition(PIVOT_90_POSITION);
    }
}
