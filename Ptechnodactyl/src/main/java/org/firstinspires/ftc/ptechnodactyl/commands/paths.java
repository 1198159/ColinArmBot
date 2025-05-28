package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.trajectorysequence.TrajectorySequence;

import org.firstinspires.ftc.ptechnodactyl.AutoConstants;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.commands.ArmCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawCmds;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import com.technototes.library.command.SequentialCommandGroup;

public class paths {

    public static Command SampleScoring(Robot r) {
public static TrajectorySequence {
    return new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants. START_TO_SCORE_)
            .alongWith((new ArmCommand(r.armSubsystem,ArmSubsystem.ArmConstants.SCORE,r.armSubsystem::wristUp,r.clawSubsystem::pivotneutral,r.clawSubsystem::closeClaw)
            .andThen(new WaitCommand(0.1))
            .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                    .andThen(new WaitCommand(0.15))
                    .andThen()
            .addTrajectory(AutoConstants.SCORE_TO_INTAKE1.get())
            .addTrajectory(AutoConstants.INTAKE1_TO_SCORE.get())
            .addTrajectory(AutoConstants.SCORE_TO_INTAKE2.get())
            .addTrajectory(AutoConstants.INTAKE2_TO_SCORE.get())
            .addTrajectory(AutoConstants.SCORE_TO_INTAKE3.get())
            .addTrajectory(AutoConstants.INTAKE3_TO_SCORE.get())
            .addTrajectory(AutoConstants.SCORE_TO_TRAVEL.get())
            .addTrajectory(AutoConstants.TRAVEL_TO_ASCENTL1.get())
            .build();
}