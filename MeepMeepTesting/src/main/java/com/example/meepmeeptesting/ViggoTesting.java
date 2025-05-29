package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;
import com.acmerobotics.roadrunner.trajectory.constraints.*;
import java.io.*;
import java.util.Arrays;
import java.util.function.*;
import javax.imageio.ImageIO;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.*;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import org.rowlandhall.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class ViggoTesting {

    public static class AutoConstants {

        public static Function<Pose2d, TrajectoryBuilder> func;
        public static Pose2d START = new Pose2d(35, 66, toRadians(180));
        public static Pose2d OBSERVATION_ZONE = new Pose2d(-48.2, 64.1, toRadians(180));
        public static Pose2d SCORE = new Pose2d(55, 55, toRadians(225));
        public static Pose2d INTAKE1 = new Pose2d(48, 38, toRadians(270));
        public static Pose2d INTAKE2 = new Pose2d(58, 38, toRadians(270));
        public static Pose2d INTAKE3 = new Pose2d(58, 26, toRadians(0));
        public static Pose2d TRAVEL = new Pose2d(40, 13, toRadians(0));
        public static Pose2d ASCENTL1 = new Pose2d(23.6, 11, toRadians(0));
        public static Pose2d TEST9 = new Pose2d(1, 38, toRadians(0));
        public static final Supplier<Trajectory> START_TO_SCORE_ = () ->
            func.apply(START).lineToLinearHeading(SCORE).build();
        public static final Supplier<Trajectory> SCORE_TO_INTAKE1 = () ->
            func.apply(SCORE).lineToLinearHeading(INTAKE1).build();
        public static final Supplier<Trajectory> INTAKE1_TO_SCORE = () ->
            func.apply(INTAKE1).lineToLinearHeading(SCORE).build();
        public static final Supplier<Trajectory> START_TO_OBS = () ->
                func.apply(START).lineToLinearHeading(OBSERVATION_ZONE).build();

        public static final Supplier<Trajectory> SCORE_TO_INTAKE2 = () ->
            func.apply(SCORE).lineToLinearHeading(INTAKE2).build();
        public static final Supplier<Trajectory> INTAKE2_TO_SCORE = () ->
            func.apply(INTAKE2).lineToLinearHeading(SCORE).build();
        public static final Supplier<Trajectory> SCORE_TO_INTAKE3 = () ->
            func.apply(SCORE).lineToLinearHeading(INTAKE3).build();
        public static final Supplier<Trajectory> INTAKE3_TO_SCORE = () ->
            func.apply(INTAKE3).lineToLinearHeading(SCORE).build();
        public static final Supplier<Trajectory> SCORE_TO_TRAVEL = () ->
            func.apply(SCORE).lineToLinearHeading(TRAVEL).build();
        public static final Supplier<Trajectory> TRAVEL_TO_ASCENTL1 = () ->
            func.apply(TRAVEL).lineToLinearHeading(ASCENTL1).build();
    }

    public static void main(String[] args) {
        // Make this as large as possible while still fitting on our laptop screens:
        MeepMeep meepMeep = new MeepMeep(550);
        try {
            // Try to load the field image from the repo:
            meepMeep.setBackground(ImageIO.read(new File("Field.jpg")));
        } catch (IOException io) {
            // If we can't find the field image, fall back to the gray grid
            meepMeep.setBackground(MeepMeep.Background.GRID_GRAY);
        }
        // Constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, trackWidth
        // maxVel: The fastest dist/sec we'll travel (velocity)
        // maxAcc: The fastest rate (dist/sec/sec) we'll change our velocity (acceleration)
        // maxAngVel: the fastest degrees/sec we'll rotate (angular velocity)
        // maxAngAcc: the fastest rate (deg/sec/sec) we'll change our rotation (angular acceleration) @MaxAngleAccel
        // trackWidth: The width of our wheelbase (not clear what this really affects...) @TrackWidth
        MinVelocityConstraint min_vel = new MinVelocityConstraint(
            Arrays.asList(
                new AngularVelocityConstraint(60/* @MaxAngleVelo */),
                new MecanumVelocityConstraint(75/* @MaxVelo */, 14.75/* @TrackWidth */)
            )
        );
        ProfileAccelerationConstraint prof_accel = new ProfileAccelerationConstraint(
            75/* @MaxAccel */
        );
        AutoConstants.func = (Pose2d pose) -> new TrajectoryBuilder(pose, min_vel, prof_accel);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setDimensions(11.7, 12.3)
            .followTrajectorySequence(drive -> getTestTrajectory(drive));
        meepMeep.setBackgroundAlpha(0.75f).addEntity(myBot).start();
    }

    private static TrajectorySequence getTestTrajectory(DriveShim drive) {
        return drive
            .trajectorySequenceBuilder(AutoConstants.START)
            .addTrajectory(AutoConstants.START_TO_OBS.get())
            .build();
    }
}
