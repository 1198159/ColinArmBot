package org.firstinspires.ftc.ptechnodactyl;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;
import java.util.function.Supplier;

public class AutoConstants {

    public static Function<Pose2d, TrajectorySequenceBuilder> func;
    public static Pose2d START = new Pose2d(41.75, 66, toRadians(180));
    public static Pose2d SCORE = new Pose2d(55, 55, toRadians(225));
    public static Pose2d INTAKE1 = new Pose2d(48.75, 43.3, toRadians(270));
    public static Pose2d INTAKE2 = new Pose2d(60, 43.3, toRadians(270));
    public static Pose2d INTAKE3 = new Pose2d(58, 34, toRadians(0));
    public static Pose2d TRAVEL = new Pose2d(40, 13, toRadians(0));
    public static Pose2d ASCENTL1 = new Pose2d(23.6, 11, toRadians(0));
    public static Pose2d TEST9 = new Pose2d(1, 38, toRadians(0));
    public static final Supplier<TrajectorySequence> START_TO_SCORE_ = () ->
            func.apply(START).lineToLinearHeading(SCORE).build();
    public static final Supplier<TrajectorySequence> SCORE_TO_INTAKE1 = () ->
            func.apply(SCORE).lineToLinearHeading(INTAKE1).build();
    public static final Supplier<TrajectorySequence> INTAKE1_TO_SCORE = () ->
            func.apply(INTAKE1).lineToLinearHeading(SCORE).build();

    public static final Supplier<TrajectorySequence> SCORE_TO_INTAKE2 = () ->
            func.apply(SCORE).lineToLinearHeading(INTAKE2).build();
    public static final Supplier<TrajectorySequence> INTAKE2_TO_SCORE = () ->
            func.apply(INTAKE2).lineToLinearHeading(SCORE).build();
    public static final Supplier<TrajectorySequence> SCORE_TO_INTAKE3 = () ->
            func.apply(SCORE).lineToLinearHeading(INTAKE3).build();
    public static final Supplier<TrajectorySequence> INTAKE3_TO_SCORE = () ->
            func.apply(INTAKE3).lineToLinearHeading(SCORE).build();
    public static final Supplier<TrajectorySequence> SCORE_TO_TRAVEL = () ->
            func.apply(SCORE).lineToLinearHeading(TRAVEL).build();
    public static final Supplier<TrajectorySequence> TRAVEL_TO_ASCENTL1 = () ->
            func.apply(TRAVEL).lineToLinearHeading(ASCENTL1).build();

}
