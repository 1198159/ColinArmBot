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
            .alongWith(
                ArmCommand.score(r.armSubsystem),
                r.armSubsystem::wristUp,
                r.clawSubsystem::pivotneutral,
                r.clawSubsystem::closeClaw
            )
            .andThen(new WaitCommand(0.1))
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
                            .andThen(new WaitCommand(0.1))
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
                                    .andThen(new WaitCommand(0.25))
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
                                            .andThen(new WaitCommand(0.1))
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
                                                    .andThen(r.clawSubsystem::closeClaw)
                                                    .andThen(new WaitCommand(0.25))
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
                                                            .andThen(new WaitCommand(0.1))
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
                                                            .andThen(
                                                                new TrajectorySequenceCommand(
                                                                    r.drivebaseSubsystem,
                                                                    AutoConstants.SCORE_TO_TRAVEL
                                                                ).andThen(
                                                                    new TrajectorySequenceCommand(
                                                                        r.drivebaseSubsystem,
                                                                        AutoConstants.TRAVEL_TO_ASCENTL1
                                                                    )
                                                                )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );
    }

    public static Command SpecimenScoring(Robot r){
        return new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.START_TO_SCORE1)
                .alongWith(
                        ArmCommand.spec(r.armSubsystem),
                        r.armSubsystem::wristUp,
                        r.clawSubsystem::pivotneutral,
                        r.clawSubsystem::closeClaw
                )
                .andThen(new WaitCommand(0.1))
                .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                .andThen(new WaitCommand(0.15))
                .andThen(ArmCommand.retract(r.armSubsystem))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE1_TO_SQUEEZE))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZE_TO_SQUEEZEDOWN2))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZEDOWN2_TO_SQUEEZEDOWN3))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZEDOWN3_TO_OBSZONE1))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.OBSZONE1_TO_BACKUP))
                .andThen(new WaitCommand(4))
                // NEED SPEC INTAKE HERE
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.BACKUP_TO_BACKIN))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.BACKIN_TO_SCORE2))
                .alongWith(
                        ArmCommand.spec(r.armSubsystem),
                        r.armSubsystem::wristUp,
                        r.clawSubsystem::pivotneutral,
                        r.clawSubsystem::closeClaw
                )
                .andThen(new WaitCommand(0.1))
                .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                .andThen(new WaitCommand(0.15))
                .andThen(ArmCommand.retract(r.armSubsystem))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE2_TO_SQUEEZE))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZE_TO_SQUEEZEDOWN32))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZEDOWN32_TO_OBSZONE2))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.OBSZONE2_TO_BACKUP2))
                .andThen(new WaitCommand(4))
                // NEED SPEC INTAKE HERE
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.BACKUP2_TO_BACKIN2))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.BACKIN2_TO_SCORE3))
                .alongWith(
                        ArmCommand.spec(r.armSubsystem),
                        r.armSubsystem::wristUp,
                        r.clawSubsystem::pivotneutral,
                        r.clawSubsystem::closeClaw
                )
                .andThen(new WaitCommand(0.1))
                .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                .andThen(new WaitCommand(0.15))
                .andThen(ArmCommand.retract(r.armSubsystem))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE3_TO_SQUEEZE))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZE_TO_SQUEEZEDOWN33))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SQUEEZEDOWN33_TO_OBSZONE3))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.OBSZONE3_TO_BACKUP3))
                .andThen(new WaitCommand(4))
                // NEED SPEC INTAKE HERE
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.BACKUP3_TO_BACKIN3))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.BACKIN3_TO_SCORE4))
                .alongWith(
                        ArmCommand.spec(r.armSubsystem),
                        r.armSubsystem::wristUp,
                        r.clawSubsystem::pivotneutral,
                        r.clawSubsystem::closeClaw
                )
                .andThen(new WaitCommand(0.1))
                .andThen(ClawCmds.cmds.OpenClaw(r.clawSubsystem))
                .andThen(new WaitCommand(0.15))
                .andThen(ArmCommand.retract(r.armSubsystem))
                .andThen(new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.SCORE4_TO_OBSPARK));




    }
}
