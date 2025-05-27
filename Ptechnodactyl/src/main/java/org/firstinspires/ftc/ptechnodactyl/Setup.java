package org.firstinspires.ftc.ptechnodactyl;

import com.acmerobotics.dashboard.config.Config;

public class Setup {

    @Config
    public static class Connected {

        public static boolean DRIVEBASE = true;
        public static boolean ARM = true;
        public static boolean CLAW = true;

    }

    @Config
    public static class HardwareNames {


        public static String PITCHMOTOR = "Arm";
        public static String SLIDEMOTOR = "Slide";
        public static String FLMOTOR = "LF";
        public static String FRMOTOR = "RF";
        public static String RLMOTOR = "LB";
        public static String RRMOTOR = "RB";
        public static String WRISTSERVO = "Wrist";
        public static String PIVOTSERVO = "Pivot";
        public static String IMU = "imu";
        public static String CLAWSERVO = "Claw";
    }

    @Config
    public static class GlobalSettings {

        public static double STICK_DEAD_ZONE = 0.1;
        public static double STRAIGHTEN_SCALE_FACTOR = 0.25;
        public static double STRAIGHTEN_RANGE = .15; // Fraction of 45 degrees...
        public static double TRIGGER_THRESHOLD = 0.7;
        public static double STRAIGHTEN_DEAD_ZONE = 0.08;
    }
}
