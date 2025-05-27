package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class ClawCmds {

    public static class cmds {

        public static Command OpenClaw(ClawSubsystem CS) {
            return Command.create(CS::openClaw, CS);
        }
        public static Command GravityAlign(ClawSubsystem CS) {
            return Command.create(CS::alignClaw, CS);
        }

        public static Command CloseClaw(ClawSubsystem CS) {
            return Command.create(CS::closeClaw, CS);
        }
        public static Command PivotLeft45(ClawSubsystem CS) {
            return Command.create(CS::pivotleft45, CS);
        }
        public static Command PivotRight45(ClawSubsystem CS) {
            return Command.create(CS::pivotright45, CS);
        }
        public static Command Pivot90(ClawSubsystem CS) {
            return Command.create(CS::pivot90, CS);
        }
        public static Command PivotNeutral(ClawSubsystem CS) {
            return Command.create(CS::pivotneutral, CS);
        }
    }
}
