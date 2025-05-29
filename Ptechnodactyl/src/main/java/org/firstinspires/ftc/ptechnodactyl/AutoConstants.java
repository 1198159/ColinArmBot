package org.firstinspires.ftc.ptechnodactyl;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.technototes.path.geometry.ConfigurablePoseD;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;

import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

public class AutoConstants {


    public static ConfigurablePoseD START = new ConfigurablePoseD(35, 66, toRadians(180));
    public static ConfigurablePoseD SCORE = new ConfigurablePoseD(55, 55, toRadians(225));
    public static ConfigurablePoseD INTAKE1 = new ConfigurablePoseD(48, 38, toRadians(270));
    public static ConfigurablePoseD INTAKE2 = new ConfigurablePoseD(58, 38, toRadians(270));
    public static ConfigurablePoseD INTAKE3 = new ConfigurablePoseD(58, 26, toRadians(0));
    public static ConfigurablePoseD TRAVEL = new ConfigurablePoseD(40, 13, toRadians(0));
    public static ConfigurablePoseD ASCENTL1 = new ConfigurablePoseD(23.6, 11, toRadians(0));
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> START_TO_SCORE = func ->
            func
                    .apply(START.toPose())
                    .lineToLinearHeading(SCORE.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SCORE_TO_INTAKE1 = func ->
            func
                    .apply(SCORE.toPose())
                    .lineToLinearHeading(INTAKE1.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> INTAKE1_TO_SCORE = func ->
            func
                    .apply(INTAKE1.toPose())
                    .lineToLinearHeading(SCORE.toPose())
                    .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SCORE_TO_INTAKE2 = func ->
            func
                    .apply(SCORE.toPose())
                    .lineToLinearHeading(INTAKE2.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> INTAKE2_TO_SCORE = func ->
            func
                    .apply(INTAKE2.toPose())
                    .lineToLinearHeading(SCORE.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SCORE_TO_INTAKE3 = func ->
            func
                    .apply(SCORE.toPose())
                    .lineToLinearHeading(INTAKE3.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> INTAKE3_TO_SCORE = func ->
            func
                    .apply(INTAKE3.toPose())
                    .lineToLinearHeading(SCORE.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SCORE_TO_TRAVEL = func ->
            func
                    .apply(SCORE.toPose())
                    .lineToLinearHeading(TRAVEL.toPose())
                    .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TRAVEL_TO_ASCENTL1 = func ->
            func
                    .apply(TRAVEL.toPose())
                    .lineToLinearHeading(ASCENTL1.toPose())
                    .build();


}
