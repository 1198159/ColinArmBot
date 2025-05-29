package org.firstinspires.ftc.ptechnodactyl;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IGyro;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class Hardware implements Loggable {

    public List<LynxModule> hubs;

    public EncodedMotor<DcMotorEx> flMotor, frMotor, rlMotor, rrMotor;
    public EncodedMotor<DcMotorEx> pitchMotor, slideMotor;

    public Servo clawServo, pivotServo, wristServo;

    public IGyro imu;

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);

        imu = new IMU(
            Setup.HardwareNames.IMU,
            RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP
        );

        if (Setup.Connected.DRIVEBASE) {
            this.frMotor = new EncodedMotor<>(Setup.HardwareNames.FRMOTOR);
            this.flMotor = new EncodedMotor<>(Setup.HardwareNames.FLMOTOR);
            this.rrMotor = new EncodedMotor<>(Setup.HardwareNames.RRMOTOR);
            this.rlMotor = new EncodedMotor<>(Setup.HardwareNames.RLMOTOR);
        }

        if (Setup.Connected.CLAW) {
            this.clawServo = new Servo(Setup.HardwareNames.CLAWSERVO);
            this.pivotServo = new Servo(Setup.HardwareNames.PIVOTSERVO);
        }

        if (Setup.Connected.ARM) {
            this.pitchMotor = new EncodedMotor<>(Setup.HardwareNames.PITCHMOTOR);
            this.slideMotor = new EncodedMotor<>(Setup.HardwareNames.SLIDEMOTOR);
            this.wristServo = new Servo(Setup.HardwareNames.WRISTSERVO);
        }
    }

    // We can read the voltage from the different hubs for fun...
    public double voltage() {
        double volt = 0;
        double count = 0;
        for (LynxModule lm : hubs) {
            count += 1;
            volt += lm.getInputVoltage(VoltageUnit.VOLTS);
        }
        return volt / count;
    }
}
