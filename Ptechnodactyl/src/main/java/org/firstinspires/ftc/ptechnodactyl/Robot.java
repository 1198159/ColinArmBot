package org.firstinspires.ftc.ptechnodactyl;

import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.ptechnodactyl.helpers.StartingPosition;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;

public class Robot implements Loggable {

    public StartingPosition position;
    public double initialVoltage;
    public DrivebaseSubsystem drivebaseSubsystem;
    public ClawSubsystem clawSubsystem;
    public ArmSubsystem armSubsystem;

    public Robot(Hardware hw) {
        this.initialVoltage = hw.voltage();
        if (Setup.Connected.DRIVEBASE) {
            this.drivebaseSubsystem = new DrivebaseSubsystem(
                hw.flMotor,
                hw.frMotor,
                hw.rlMotor,
                hw.rrMotor,
                hw.imu
            );
        }
        if (Setup.Connected.CLAW) {
            this.clawSubsystem = new ClawSubsystem(hw.clawServo, hw.pivotServo);
        }
        if (Setup.Connected.ARM) {
            this.armSubsystem = new ArmSubsystem(hw.pitchMotor, hw.slideMotor, hw.wristServo);
        }


    }
}
