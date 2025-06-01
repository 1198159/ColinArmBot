package org.firstinspires.ftc.ptechnodactyl.commands;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
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

public class paths {

    public static Command SampleScoring(Robot r) {
        return new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.START_TO_SCORE_)
                .alongWith(r.clawSubsystem::closeClaw)
                .andThen(new WaitCommand(0.2))
                .andThen(ArmCommand.score(r.armSubsystem),
                        r.armSubsystem::wristUp,
                        r.clawSubsystem::pivotneutral,
                        r.clawSubsystem::closeClaw)
            .andThen(new WaitCommand(0.9))
            .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
            .andThen(new WaitCommand(0.15))
            .andThen(ArmCommand.retract(r.armSubsystem), r.armSubsystem::wristDown)
            .andThen(

                new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE_TO_INTAKE1)
                    .andThen(new WaitCommand(0.2))
                    .andThen(r.clawSubsystem::closeClaw)
                    .andThen(new WaitCommand(0.25))
                    .andThen(
                        new TrajectorySequenceCommand(
                            r.drivebaseSubsystem,
                            AutoConstants.INTAKE1_TO_SCORE
                        )
                            .alongWith(
                                ArmCommand.score(r.armSubsystem),
                                r.armSubsystem::wristUp,
                                r.clawSubsystem::pivotneutral,
                                r.clawSubsystem::closeClaw
                            )
                            .andThen(new WaitCommand(0.05))
                            .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                            .andThen(new WaitCommand(0.15))
                            .andThen(ArmCommand.retract(r.armSubsystem), r.armSubsystem::wristDown)
                            .andThen(
                                new TrajectorySequenceCommand(
                                    r.drivebaseSubsystem,
                                    AutoConstants.SCORE_TO_INTAKE2
                                )
                                    .andThen(new WaitCommand(0.2))
                                    .andThen(r.clawSubsystem::closeClaw)
                                    .andThen(new WaitCommand(0.2))
                                    .andThen(
                                        new TrajectorySequenceCommand(
                                            r.drivebaseSubsystem,
                                            AutoConstants.INTAKE2_TO_SCORE
                                        )
                                            .alongWith(
                                                (ArmCommand.score(r.armSubsystem)),
                                                r.armSubsystem::wristUp,
                                                r.clawSubsystem::pivotneutral,
                                                r.clawSubsystem::closeClaw
                                            )
                                            .andThen(new WaitCommand(0.05))
                                            .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                                            .andThen(new WaitCommand(0.15))
                                            .andThen(
                                                ArmCommand.retract(r.armSubsystem),
                                                r.armSubsystem::wristDown
                                            )
                                            .andThen(
                                                new TrajectorySequenceCommand(
                                                    r.drivebaseSubsystem,
                                                    AutoConstants.SCORE_TO_INTAKE3
                                                )
                                                    .andThen(new WaitCommand(0.2))
                                                    .andThen(r.clawSubsystem::pivot90)
                                                    .andThen(new WaitCommand(0.2))
                                                    .andThen(r.clawSubsystem::closeClaw)
                                                    .andThen(new WaitCommand(0.2))
                                                    .andThen(
                                                        new TrajectorySequenceCommand(
                                                            r.drivebaseSubsystem,
                                                            AutoConstants.INTAKE3_TO_SCORE
                                                        )
                                                            .alongWith(
                                                                (ArmCommand.score(r.armSubsystem)),
                                                                r.armSubsystem::wristUp,
                                                                r.clawSubsystem::pivotneutral,
                                                                r.clawSubsystem::closeClaw
                                                            )
                                                            .andThen(new WaitCommand(0.05))
                                                            .andThen(
                                                                ClawCmds.cmds.OpenClaw(
                                                                    r.clawSubsystem
                                                                )
                                                            )
                                                            .andThen(new WaitCommand(0.15))
                                                            .andThen(
                                                                ArmCommand.retract(r.armSubsystem),
                                                                r.armSubsystem::wristDown
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );
    }
}
