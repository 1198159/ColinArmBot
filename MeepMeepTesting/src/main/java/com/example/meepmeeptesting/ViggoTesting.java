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
        public static Pose2d START = new Pose2d(-23.3, 65.3, toRadians(180));
        public static Pose2d TRANSFER = new Pose2d(-23.3, 48.2, toRadians(225));
        public static Pose2d SCORE1 = new Pose2d(-11, 37, toRadians(90));
        public static Pose2d SCORE2 = new Pose2d(-8, 37, toRadians(90));
        public static Pose2d SCORE3 = new Pose2d(-5, 37, toRadians(90));
        public static Pose2d SCORE4 = new Pose2d(-2, 37, toRadians(90));
        public static Pose2d INTAKE1 = new Pose2d(-48, 38, toRadians(270));
        public static Pose2d INTAKE2 = new Pose2d(-58, 38, toRadians(270));
        public static Pose2d INTAKE3 = new Pose2d(-58, 26, toRadians(180));
        public static Pose2d TRAVEL = new Pose2d(-40, 13, toRadians(0));
        public static Pose2d ASCENTL1 = new Pose2d(-23.6, 11, toRadians(0));
        public static Pose2d SQUEEZE = new Pose2d(-35.4, 43, toRadians(0));
        public static Pose2d SQUEEZEDOWN = new Pose2d(-29.2, 43, toRadians(0));
        public static Pose2d SQUEEZEDOWN2 = new Pose2d(-36.7, 12.3, toRadians(0));
        public static Pose2d SQUEEZEDOWN3 = new Pose2d(-47.2, 12.3, toRadians(0));
        public static Pose2d OBSZONE1 = new Pose2d(-47.9, 59.5, toRadians(0));
        public static Pose2d BACKUP = new Pose2d(-47.4, 47.9, toRadians(90));
        public static Pose2d BACKIN = new Pose2d(-47.4, 59.5, toRadians(90));


        public static Pose2d TEST9 = new Pose2d(1, 38, toRadians(0));
        public static final Supplier<Trajectory> START_TO_TRANSFER = () ->
            func.apply(START).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> TRANSFER_TO_SCORE1 = () ->
            func.apply(TRANSFER).lineToLinearHeading(SCORE1).build();
        public static final Supplier<Trajectory> TRANSFER_TO_SCORE2 = () ->
                func.apply(TRANSFER).lineToLinearHeading(SCORE2).build();
        public static final Supplier<Trajectory> TRANSFER_TO_SCORE3 = () ->
                func.apply(TRANSFER).lineToLinearHeading(SCORE3).build();
        public static final Supplier<Trajectory> TRANSFER_TO_SCORE4 = () ->
                func.apply(TRANSFER).lineToLinearHeading(SCORE4).build();
        public static final Supplier<Trajectory> TRANSFER_TO_INTAKE1 = () ->
                func.apply(TRANSFER).lineToLinearHeading(INTAKE1).build();
        public static final Supplier<Trajectory> INTAKE1_TO_TRANSFER = () ->
            func.apply(INTAKE1).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> TRANSFER_TO_INTAKE2 = () ->
                func.apply(TRANSFER).lineToLinearHeading(INTAKE1).build();
        public static final Supplier<Trajectory> INTAKE2_TO_TRANSFER = () ->
                func.apply(INTAKE1).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> TRANSFER_TO_INTAKE3 = () ->
                func.apply(TRANSFER).lineToLinearHeading(INTAKE1).build();
        public static final Supplier<Trajectory> INTAKE3_TO_TRANSFER = () ->
                func.apply(INTAKE1).lineToLinearHeading(TRANSFER).build();

        public static final Supplier<Trajectory> SCORE1_TO_TRANSFER = () ->
            func.apply(SCORE1).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> SCORE2_TO_TRANSFER = () ->
                func.apply(SCORE2).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> SCORE3_TO_TRANSFER = () ->
                func.apply(SCORE3).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> SCORE4_TO_TRANSFER = () ->
                func.apply(SCORE4).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> INTAKE2_TO_SCORE = () ->
            func.apply(INTAKE2).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> SCORE_TO_INTAKE3 = () ->
            func.apply(TRANSFER).lineToLinearHeading(INTAKE3).build();
        public static final Supplier<Trajectory> INTAKE3_TO_SCORE = () ->
            func.apply(INTAKE3).lineToLinearHeading(TRANSFER).build();
        public static final Supplier<Trajectory> SCORE_TO_TRAVEL = () ->
            func.apply(TRANSFER).lineToLinearHeading(TRAVEL).build();
        public static final Supplier<Trajectory> TRAVEL_TO_ASCENTL1 = () ->
            func.apply(TRAVEL).lineToLinearHeading(ASCENTL1).build();
        public static final Supplier<Trajectory> TRANSFER_TO_SQUEEZE = () ->
                func.apply(TRANSFER).lineToLinearHeading(SQUEEZE).build();
        public static final Supplier<Trajectory> SQUEEZEDOWN_TO_SQUEEZEDOWN3 = () ->
                func.apply(SQUEEZE).lineToLinearHeading(SQUEEZEDOWN).build();
        public static final Supplier<Trajectory> SQUEEZE_TO_SQUEEZEDOWN2 = () ->
                func.apply(SQUEEZE).lineToLinearHeading(SQUEEZEDOWN2).build();
        public static final Supplier<Trajectory> SQUEEZEDOWN3_TO_OBSZONE1 = () ->
                func.apply(SQUEEZEDOWN3).lineToLinearHeading(OBSZONE1).build();
        public static final Supplier<Trajectory> SQUEEZEDOWN2_TO_SQUEEZEDOWN3 = () ->
                func.apply(SQUEEZEDOWN2).lineToLinearHeading(SQUEEZEDOWN3).build();
        public static final Supplier<Trajectory> OBSZONE1_TO_BACKUP = () ->
                func.apply(OBSZONE1).lineToLinearHeading(BACKUP).build();
        public static final Supplier<Trajectory> BACKUP_TO_BACKIN = () ->
                func.apply(BACKUP).lineToLinearHeading(BACKIN).build();
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
            .addTrajectory(AutoConstants.START_TO_TRANSFER.get())

            .addTrajectory(AutoConstants.TRANSFER_TO_SQUEEZE.get())
            .addTrajectory(AutoConstants.SQUEEZE_TO_SQUEEZEDOWN2.get())
                .addTrajectory(AutoConstants.SQUEEZEDOWN2_TO_SQUEEZEDOWN3.get())
                .addTrajectory(AutoConstants.SQUEEZEDOWN3_TO_OBSZONE1.get())
                .addTrajectory(AutoConstants.OBSZONE1_TO_BACKUP.get())
                .addTrajectory(AutoConstants.BACKUP_TO_BACKIN.get())
            .build();
    }
}
