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
    public static Pose2d START = new Pose2d(35, 66, toRadians(180));
    public static Pose2d SCORE = new Pose2d(55, 55, toRadians(225));
    public static Pose2d INTAKE1 = new Pose2d(48, 38, toRadians(270));
    public static Pose2d INTAKE2 = new Pose2d(58, 38, toRadians(270));
    public static Pose2d INTAKE3 = new Pose2d(58, 26, toRadians(0));
    public static Pose2d TRAVEL = new Pose2d(40, 13, toRadians(0));
    public static Pose2d ASCENTL1 = new Pose2d(23.6, 11, toRadians(0));
    public static Pose2d SPEC_SCORE1 = new Pose2d(-11, 37, toRadians(90));
    public static Pose2d SPEC_SCORE2 = new Pose2d(-8, 37, toRadians(90));
    public static Pose2d SPEC_SCORE3 = new Pose2d(-5, 37, toRadians(90));
    public static Pose2d SPEC_SCORE4 = new Pose2d(-2, 37, toRadians(90));
    public static Pose2d SQUEEZE = new Pose2d(-35.4, 35, toRadians(0));
    public static Pose2d SQUEEZEDOWN2 = new Pose2d(-36.7, 12.3, toRadians(0));
    public static Pose2d SQUEEZEDOWN3 = new Pose2d(-47.2, 12.3, toRadians(0));
    public static Pose2d SQUEEZEDOWN32 = new Pose2d(-57.2, 12.3, toRadians(0));
    public static Pose2d SQUEEZEDOWN33 = new Pose2d(-64.1, 12.3, toRadians(0));
    public static Pose2d OBSPARK = new Pose2d(-54.3, 65, toRadians(0));
    public static Pose2d OBSZONE1 = new Pose2d(-47.9, 59.5, toRadians(0));
    public static Pose2d OBSZONE2 = new Pose2d(-57.9, 59.5, toRadians(0));
    public static Pose2d OBSZONE3 = new Pose2d(-64.1, 59.5, toRadians(0));
    public static Pose2d BACKUP = new Pose2d(-47.4, 47.9, toRadians(90));
    public static Pose2d BACKUP2 = new Pose2d(-57.4, 47.9, toRadians(90));
    public static Pose2d BACKUP3 = new Pose2d(-64.1, 47.9, toRadians(90));
    public static Pose2d BACKIN = new Pose2d(-47.4, 59.5, toRadians(90));
    public static Pose2d BACKIN2 = new Pose2d(-57.4, 59.5, toRadians(90));
    public static Pose2d BACKIN3 = new Pose2d(-64.1, 59.5, toRadians(90));
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


    public static final Supplier<TrajectorySequence> START_TO_SCORE1 = () ->
            func.apply(START).lineToLinearHeading(SPEC_SCORE1).build();
    public static final Supplier<TrajectorySequence> BACKIN_TO_SCORE2 = () ->
            func.apply(BACKIN).lineToLinearHeading(SPEC_SCORE2).build();
    public static final Supplier<TrajectorySequence> BACKIN2_TO_SCORE3 = () ->
            func.apply(BACKIN2).lineToLinearHeading(SPEC_SCORE3).build();
    public static final Supplier<TrajectorySequence> BACKIN3_TO_SCORE4 = () ->
            func.apply(BACKIN3).lineToLinearHeading(SPEC_SCORE4).build();

    public static final Supplier<TrajectorySequence> SCORE1_TO_SQUEEZE = () ->
            func.apply(SPEC_SCORE1).lineToLinearHeading(SQUEEZE).build();
    public static final Supplier<TrajectorySequence> SCORE2_TO_SQUEEZE = () ->
            func.apply(SPEC_SCORE2).lineToLinearHeading(SQUEEZE).build();
    public static final Supplier<TrajectorySequence> SCORE3_TO_SQUEEZE = () ->
            func.apply(SPEC_SCORE3).lineToLinearHeading(SQUEEZE).build();
    public static final Supplier<TrajectorySequence> SQUEEZE_TO_SQUEEZEDOWN2 = () ->
            func.apply(SQUEEZE).lineToLinearHeading(SQUEEZEDOWN2).build();
    public static final Supplier<TrajectorySequence> SQUEEZE_TO_SQUEEZEDOWN32 = () ->
            func.apply(SQUEEZE).lineToLinearHeading(SQUEEZEDOWN32).build();
    public static final Supplier<TrajectorySequence> SQUEEZE_TO_SQUEEZEDOWN33 = () ->
            func.apply(SQUEEZE).lineToLinearHeading(SQUEEZEDOWN33).build();
    public static final Supplier<TrajectorySequence> SQUEEZEDOWN3_TO_OBSZONE1 = () ->
            func.apply(SQUEEZEDOWN3).lineToLinearHeading(OBSZONE1).build();
    public static final Supplier<TrajectorySequence> SQUEEZEDOWN32_TO_OBSZONE2 = () ->
            func.apply(SQUEEZEDOWN32).lineToLinearHeading(OBSZONE2).build();
    public static final Supplier<TrajectorySequence> SQUEEZEDOWN33_TO_OBSZONE3 = () ->
            func.apply(SQUEEZEDOWN33).lineToLinearHeading(OBSZONE3).build();
    public static final Supplier<TrajectorySequence> SQUEEZEDOWN2_TO_SQUEEZEDOWN3 = () ->
            func.apply(SQUEEZEDOWN2).lineToLinearHeading(SQUEEZEDOWN3).build();
    public static final Supplier<TrajectorySequence> OBSZONE1_TO_BACKUP = () ->
            func.apply(OBSZONE1).lineToLinearHeading(BACKUP).build();
    public static final Supplier<TrajectorySequence> OBSZONE2_TO_BACKUP2 = () ->
            func.apply(OBSZONE2).lineToLinearHeading(BACKUP2).build();
    public static final Supplier<TrajectorySequence> OBSZONE3_TO_BACKUP3 = () ->
            func.apply(OBSZONE3).lineToLinearHeading(BACKUP3).build();
    public static final Supplier<TrajectorySequence> BACKUP_TO_BACKIN = () ->
            func.apply(BACKUP).lineToLinearHeading(BACKIN).build();
    public static final Supplier<TrajectorySequence> BACKUP2_TO_BACKIN2 = () ->
            func.apply(BACKUP2).lineToLinearHeading(BACKIN2).build();
    public static final Supplier<TrajectorySequence> BACKUP3_TO_BACKIN3 = () ->
            func.apply(BACKUP3).lineToLinearHeading(BACKIN3).build();
    public static final Supplier<TrajectorySequence> SCORE4_TO_OBSPARK = () ->
            func.apply(SPEC_SCORE4).lineToLinearHeading(OBSPARK).build();

}
