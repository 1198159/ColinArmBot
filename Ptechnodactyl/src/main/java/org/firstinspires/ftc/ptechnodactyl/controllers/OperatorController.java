package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.ArmCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawCmds;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickIncDecCmd;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton pivotLeft45;
    public CommandButton pivotRight45;
    public CommandButton pivot90;
    public CommandButton pivotNeutral;
    public Stick armStick;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();

    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.leftBumper;
        closeClaw = gamepad.rightBumper;
        armStick = gamepad.rightStick;
        pivotLeft45 = gamepad.dpadLeft;
        pivotRight45 = gamepad.dpadRight;
        pivot90 = gamepad.dpadDown;
        pivotNeutral = gamepad.dpadUp;
    }

    public void BindControls() {
        if (Setup.Connected.CLAW) {
            bindClawSubsystemControls();
        }
    }

    public void bindClawSubsystemControls() {
        openClaw.whenPressed(ClawCmds.cmds.OpenClaw(robot.clawSubsystem));
        closeClaw.whenPressed(ClawCmds.cmds.CloseClaw(robot.clawSubsystem));
        pivotLeft45.whenPressed(ClawCmds.cmds.PivotLeft45(robot.clawSubsystem));
        pivotRight45.whenPressed(ClawCmds.cmds.PivotRight45(robot.clawSubsystem));
        pivotNeutral.whenPressed(ClawCmds.cmds.PivotNeutral(robot.clawSubsystem));
        pivot90.whenPressed(ClawCmds.cmds.Pivot90(robot.clawSubsystem));
        CommandScheduler.scheduleJoystick(new JoystickIncDecCmd(robot.clawSubsystem, armStick));
    }
}
