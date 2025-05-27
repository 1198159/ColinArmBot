package org.firstinspires.ftc.ptechnodactyl.commands;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class ArmCommand implements Command {
    private ArmSubsystem subsystem;
    private Vector2d position;
    public ArmCommand(ArmSubsystem subsystem, Vector2d position){
        addRequirements(subsystem);
        this.subsystem = subsystem;
        this.position = position;
    }


    @Override
    public void initialize() {
        subsystem.setArmPos(position);
    }

    @Override
    public void execute() {

    }
    @Override
    public boolean isFinished(){
        return false;
    }

}
