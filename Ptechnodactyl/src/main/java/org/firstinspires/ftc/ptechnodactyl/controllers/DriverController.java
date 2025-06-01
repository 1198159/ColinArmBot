package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.ArmCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawCmds;
import org.firstinspires.ftc.ptechnodactyl.commands.DrivingCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickDriveCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;

public class DriverController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton pivotLeft45;
    public CommandButton pivotRight45;
    public CommandButton pivot90;
    public CommandButton pivotNeutral;
    public CommandButton alignClaw;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, turboButton, snailButton;
    public CommandButton override;
    public CommandAxis driveStraighten;
    public CommandAxis drive45;

    public DriverController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = g.leftTrigger.getAsButton(0.5);

        AssignNamedControllerButton();
        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.ps_options;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraighten = gamepad.rightTrigger;
        drive45 = gamepad.leftTrigger;
        snailButton = gamepad.rightBumper;
    }

    public void bindDriveControls() {
        CommandScheduler.scheduleJoystick(
            new JoystickDriveCommand(
                robot.drivebaseSubsystem,
                driveLeftStick,
                driveRightStick,
                driveStraighten,
                drive45
            )
        );
        resetGyroButton.whenPressed(DrivingCommands.ResetGyro(robot.drivebaseSubsystem));
        snailButton.whenPressed(DrivingCommands.SnailDriving(robot.drivebaseSubsystem));
        snailButton.whenReleased(DrivingCommands.NormalDriving(robot.drivebaseSubsystem));
    }
}
