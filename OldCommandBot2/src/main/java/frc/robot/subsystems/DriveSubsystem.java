/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import java.util.function.ToIntFunction;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.cameraserver.CameraServer;


/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {

  
  public DoubleSolenoid m_shifters = new DoubleSolenoid(RobotMap.pcm_ID, RobotMap.shifter_ID0, RobotMap.shifter_ID1);

  public CANSparkMax m_l_leftMotor = new CANSparkMax(RobotMap.leftLeadMotor_ID, MotorType.kBrushless);
  public CANSparkMax m_f_leftMotor = new CANSparkMax(RobotMap.leftFollowMotor_ID, MotorType.kBrushless);
                      
  public CANSparkMax m_l_rightMotor = new CANSparkMax(RobotMap.rightLeadMotor_ID, MotorType.kBrushless);
  public CANSparkMax m_f_rightMotor = new CANSparkMax(RobotMap.rightFollowMotor_ID, MotorType.kBrushless);

  public DifferentialDrive m_drive = new DifferentialDrive(m_l_leftMotor, m_l_rightMotor);
  public NetworkTable Limelight;
  public AHRS navX = new AHRS(SPI.Port.kMXP);
  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  public DriverStation dstation = DriverStation.getInstance();
  public Alliance teamColor = dstation.getAlliance();

  public DriveSubsystem(){

    CameraServer.getInstance().startAutomaticCapture();

    //Left lead motor settings
    m_l_leftMotor.restoreFactoryDefaults();
    m_l_leftMotor.setInverted(false);
    m_l_leftMotor.setSmartCurrentLimit(40);
    m_l_leftMotor.setOpenLoopRampRate(0.5);
     
    
    //Left follower motor settings
    m_f_leftMotor.restoreFactoryDefaults();
    m_f_leftMotor.setInverted(false);
    m_f_leftMotor.setSmartCurrentLimit(40);
    m_f_leftMotor.setOpenLoopRampRate(0.5);
    m_f_leftMotor.follow(m_l_leftMotor);
     

    //Right lead motor settings
    m_l_rightMotor.restoreFactoryDefaults();
    m_l_rightMotor.setInverted(false);
    m_l_rightMotor.setSmartCurrentLimit(40);
    m_l_rightMotor.setOpenLoopRampRate(0.5);
      

    //Right follower motor settings
    m_f_rightMotor.restoreFactoryDefaults();
    m_f_rightMotor.setInverted(false);
    m_f_rightMotor.setSmartCurrentLimit(40);
    m_f_rightMotor.setOpenLoopRampRate(0.5);
    m_f_rightMotor.follow(m_l_rightMotor);
   

  }

  public double GetYaw(){

    return navX.getYaw();

  }


  public void ChangeGear(){   

    //A command that changes gear based on m_shifters current state

    boolean shiftedDown = false;

    DoubleSolenoid.Value isHighGear = m_shifters.get();
    System.out.println("The Method ran");
    
    if (isHighGear.toString() != "kForward"){
      ShiftDown();
      shiftedDown = true;
    }

    else if (isHighGear.toString() != "kReverse" & shiftedDown == false) {

      ShiftUp();

    }


  }

  public void ShiftUp(){

    m_shifters.set(Value.kReverse);
    System.out.println("Shift Up Ran");

  }

  public void ShiftDown(){

    m_shifters.set(Value.kForward);
    System.out.println("Shift Down Ran");

  }

  public Boolean IsBlue(){

    if (teamColor.toString() == "Blue") return true;

    else return false;
  }

  public double ConvertDistance(double distance){

  
    return distance * 0.65;

  
  }

  public double GetAverageDistance(){
    double averageDistance = 
    (m_l_leftMotor.getEncoder().getPosition() +
    m_f_leftMotor.getEncoder().getPosition() +
    m_l_rightMotor.getEncoder().getPosition() +
    m_f_rightMotor.getEncoder().getPosition()) / 4;


    return averageDistance;

  }



  public void AutoAim(){

    Update_Limelight_Tracking();

    if (m_LimelightHasValidTarget)
      {
            m_drive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
      }
      else
      {
            m_drive.arcadeDrive(0.0,0.0);
      }


}
    


public void Update_Limelight_Tracking()
{
    // These numbers must be tuned for your Robot!  Be careful!
    final double STEER_K = 0.03;                    // how hard to turn toward the target
    final double DRIVE_K = -0.5;                    // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 0.5;        // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

    if (tv < 1.0)
    {
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightSteerCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;

    // Start with proportional steering
    double steer_cmd = tx * STEER_K;
    m_LimelightSteerCommand = steer_cmd;

    // try to drive forward until the target area reaches our desired area
    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE)
    {
      drive_cmd = MAX_DRIVE;
    }
    m_LimelightDriveCommand = drive_cmd;
}

public void LimelightOn(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
}

public void LimelightOff(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
}
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveCommand());
  }

  public void drive(double speed, double rot){

    m_drive.arcadeDrive(speed, rot);

  }
}
