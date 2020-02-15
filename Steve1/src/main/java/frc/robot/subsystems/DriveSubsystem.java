/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;

public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */

  private final CANSparkMax m_l_leftMotor = new CANSparkMax(Constants.DriveBaseConstants.kLeadLeftMotor, MotorType.kBrushless);
  private final CANSparkMax m_l_rightMotor = new CANSparkMax(Constants.DriveBaseConstants.kleadRightMotor, MotorType.kBrushless);
  private final CANSparkMax m_f_leftMotor = new CANSparkMax(Constants.DriveBaseConstants.kFollowLeftMotor, MotorType.kBrushless);
  private final CANSparkMax m_f_rightMotor= new CANSparkMax(Constants.DriveBaseConstants.kFollowRightMotor, MotorType.kBrushless);
  private final DifferentialDrive m_drive = new DifferentialDrive(m_l_leftMotor, m_l_rightMotor);
  private final Solenoid shifter = new Solenoid(Constants.DriveBaseConstants.kPCM, Constants.DriveBaseConstants.kShifterPort);
  private final NetworkTable Limelight = NetworkTableInstance.getDefault().getTable("limelight");
  private Float tx;
  private Float ty;
  private Float ta;
  private final AHRS navX = new AHRS(SPI.Port.kMXP);
  private final DriverStation dstation = DriverStation.getInstance();

  public DriveSubsystem() {

    m_l_leftMotor.setInverted(false);
    m_l_leftMotor.setSmartCurrentLimit(40);
    m_l_leftMotor.setOpenLoopRampRate(1);

    m_f_leftMotor.setInverted(false);
    m_f_leftMotor.setSmartCurrentLimit(40);
    m_f_leftMotor.setOpenLoopRampRate(1);
    m_f_leftMotor.follow(m_l_leftMotor);

    m_l_rightMotor.setInverted(true);
    m_l_rightMotor.setSmartCurrentLimit(40);
    m_l_rightMotor.setOpenLoopRampRate(1);

    m_f_rightMotor.setInverted(true);
    m_f_rightMotor.setSmartCurrentLimit(40);
    m_f_rightMotor.setOpenLoopRampRate(1);
    m_f_rightMotor.follow(m_l_rightMotor);

    addChild("Differential Drive 1",m_drive);
            m_drive.setSafetyEnabled(true);
            m_drive.setExpiration(0.1);
            m_drive.setMaxOutput(1.0);

            addChild("Shifter",shifter);

    SmartDashboard.putNumber("LimelightX", tx);
    SmartDashboard.putNumber("LimelightY", ty);
    SmartDashboard.putNumber("LimelightArea", ta);


    SmartDashboard.putString( "Driver Station",     dstation.toString());
    SmartDashboard.putString( "Alliance",           dstation.getAlliance().toString());
    SmartDashboard.putNumber( "Match Number",       dstation.getMatchNumber());
    SmartDashboard.putNumber( "Match Time",         dstation.getMatchTime());
    SmartDashboard.putString( "Match Time",         dstation.getGameSpecificMessage());

    SmartDashboard.putBoolean(  "IMU_Connected",        navX.isConnected());
    SmartDashboard.putBoolean(  "IMU_IsCalibrating",    navX.isCalibrating());
    SmartDashboard.putNumber(   "IMU_Yaw",              navX.getYaw());
    SmartDashboard.putNumber(   "IMU_Pitch",            navX.getPitch());
    SmartDashboard.putNumber(   "IMU_Roll",             navX.getRoll());

  }


  public void drive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);

  }


  public void shiftUp(){

    shifter.set(true);

  }


  public void shiftDown(){

    shifter.set(false);

  }

  public boolean getGear(){

    return shifter.get();

  }

  public double getYaw(){

    return navX.getYaw();
    
  }
}
