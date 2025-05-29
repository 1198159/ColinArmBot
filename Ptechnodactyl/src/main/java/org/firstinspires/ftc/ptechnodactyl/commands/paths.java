package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.ptechnodactyl.AutoConstants;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;

public class paths {

    public static Command SampleScoring(Robot r) {
        return new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.START_TO_SCORE)
                .alongWith(new ParallelCommandGroup(
                        new ArmCommand(r.armSubsystem, ArmSubsystem.ArmConstants.SCORE),
                        r.armSubsystem::wristUp,
                        r.clawSubsystem::pivotneutral,
                        r.clawSubsystem::closeClaw))
                .andThen(new WaitCommand(0.1))
                .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                .andThen(new WaitCommand(0.15))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE_TO_INTAKE1))

                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.INTAKE1_TO_SCORE))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE_TO_INTAKE2))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE_TO_INTAKE2))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.INTAKE2_TO_SCORE))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE_TO_INTAKE3))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.INTAKE3_TO_SCORE))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE_TO_TRAVEL))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.TRAVEL_TO_ASCENTL1));



    }
}
