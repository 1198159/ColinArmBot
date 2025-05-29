package org.firstinspires.ftc.ptechnodactyl.opmodes.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.ptechnodactyl.AutoConstants;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.commands.paths;
import org.firstinspires.ftc.ptechnodactyl.helpers.HeadingHelper;
import org.firstinspires.ftc.ptechnodactyl.helpers.StartingPosition;

@Autonomous(name = "NetScoring", preselectTeleOp = "Dual Control")
@SuppressWarnings("unused")
public class basketauto extends CommandOpMode {

    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.START);
        CommandScheduler.register(robot.armSubsystem);
        CommandScheduler.scheduleForState(
            new SequentialCommandGroup(
                paths.SampleScoring(robot),
                CommandScheduler::terminateOpMode
            ),
            OpModeState.RUN
        );
    }

    public void uponStart() {}

    public void end() {
        HeadingHelper.savePose(robot.drivebaseSubsystem.getPoseEstimate());
    }
}
