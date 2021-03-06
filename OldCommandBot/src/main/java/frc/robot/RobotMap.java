/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


  //CAN
    

      

        public static int leftLeadMotor_ID = 11;
        public static int leftFollowMotor_ID = 12;
        public static int rightLeadMotor_ID = 13;
        public static int rightFollowMotor_ID = 14;

        public static int pcm_ID = 20;
          public static int shifter_ID0 = 2;
          public static int shifter_ID1 = 3;
      

    
  //USB

        public static int driverController_ID = 0;

        public static int A_Button = 1;
        public static int B_Button = 2;
        public static int X_Button = 3;
        public static int Y_Button = 4;
        public static int L_Bumper = 5;
        public static int R_Bumper = 6;
        public static int Select_Button = 7;
        public static int Menu_Button = 8;
        public static int L_Stick_Button = 9;
        public static int R_Stick_Button = 10;


            /**
     * BUTTON MAP KEY:
     * 1 = A
     * 2 = B
     * 3 = X
     * 4 = Y
     * 5 = left bumper
     * 6 = right bumper
     * 7 = select
     * 8 = menu
     * 9 = left stick click
     * 10 = right stick click
     */
  

  //

  //Shooter PID
    public static double shooter_kP = 6e-5; 
    public static double shooter_kI = 0;
    public static double shooter_kD = 0; 
    public static double shooter_kIz = 0; 
    public static double shooter_kFF = 0.000015; 
    public static double shooter_kMaxOutput = 1; 
    public static double shooter_kMinOutput = -1;
    public static double shooter_maxRPM = 5700;
}
