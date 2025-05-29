package org.firstinspires.ftc.ptechnodactyl.commands;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class ArmCommand implements Command {

    public static ArmCommand score(ArmSubsystem subsystem) {
        return new ArmCommand(subsystem, ArmSubsystem.ArmConstants.SCORE);
    }

    public static ArmCommand retract(ArmSubsystem subsystem) {
        return new ArmCommand(subsystem, ArmSubsystem.ArmConstants.RETRACT);
    }

    public static ArmCommand pickup(ArmSubsystem subsystem) {
        return new ArmCommand(subsystem, ArmSubsystem.ArmConstants.PICKUP);
    }

    public static ArmCommand spec(ArmSubsystem subsystem) {
        return new ArmCommand(subsystem, ArmSubsystem.ArmConstants.SPEC);
    }

    private ArmSubsystem subsystem;
    private Vector2d position;

    public ArmCommand(ArmSubsystem subsystem, Vector2d position) {
        addRequirements(subsystem);
        this.subsystem = subsystem;
        this.position = position;
    }

    @Override
    public void initialize() {
        subsystem.setArmPos(position);
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
