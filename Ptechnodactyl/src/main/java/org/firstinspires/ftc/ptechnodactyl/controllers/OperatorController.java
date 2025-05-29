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
import org.firstinspires.ftc.ptechnodactyl.commands.DrivingCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickIncDecCmd;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
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
    public CommandButton alignClaw;
    public CommandButton score;
    public CommandButton retract;
    public CommandButton pickup;
    public CommandButton spec;
    public Stick armStick;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        if (Setup.Connected.CLAW) {
            bindClawSubsystemControls();
        }
        if (Setup.Connected.ARM) {
            gamepad.ps_triangle
                .whenPressed(ArmCommand.score(robot.armSubsystem))
                .whenPressed(robot.armSubsystem::wristUp)
                .whenPressed(robot.clawSubsystem::pivotneutral);
            gamepad.ps_cross
                .whenPressed(new ArmCommand(robot.armSubsystem, ArmSubsystem.ArmConstants.RETRACT))
                .whenPressed(robot.armSubsystem::wristUp)
                .whenPressed(robot.clawSubsystem::pivotneutral);
            gamepad.ps_circle
                .whenPressed(new ArmCommand(robot.armSubsystem, ArmSubsystem.ArmConstants.PICKUP))
                .whenPressed(robot.armSubsystem::wristUp)
                .whenPressed(robot.clawSubsystem::pivotneutral);
            gamepad.ps_square
                .whenPressed(new ArmCommand(robot.armSubsystem, ArmSubsystem.ArmConstants.SPEC))
                .whenPressed(robot.armSubsystem::wristUp)
                .whenPressed(robot.clawSubsystem::pivotneutral);
            if (Setup.Connected.CLAW) {
                pivotNeutral.whenPressed(robot.armSubsystem::wristDown);
            }
        }
    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.leftBumper;
        closeClaw = gamepad.rightBumper;
        pivotLeft45 = gamepad.dpadLeft;
        pivotRight45 = gamepad.dpadRight;
        pivot90 = gamepad.dpadUp;
        pivotNeutral = gamepad.dpadDown;
        alignClaw = gamepad.ps_share;
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
        alignClaw.whenPressed(ClawCmds.cmds.GravityAlign(robot.clawSubsystem));
        CommandScheduler.scheduleJoystick(new JoystickIncDecCmd(robot.clawSubsystem, armStick));
    }
}
