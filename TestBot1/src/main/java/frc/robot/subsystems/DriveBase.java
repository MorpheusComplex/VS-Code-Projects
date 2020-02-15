/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;


public class DriveBase extends SubsystemBase {

  private DifferentialDrive arc_Drive;

  private AHRS gyro_navx;

  private CANSparkMax leftMotor_1;
  private CANSparkMax leftMotor_2;
  private CANSparkMax rightMotor_1;
  private CANSparkMax rightMotor_2;

  private Solenoid gearbox;

  private double rampRate;

  /**
   * Creates a new DriveBase.
   */
  public DriveBase() {

    rampRate = Constants.DriveBase.kRampRate;

    leftMotor_1 = new CANSparkMax(Constants.DriveBase.leftMotor_1_ID, MotorType.kBrushless);
      leftMotor_1.setInverted(false);
      leftMotor_1.setSmartCurrentLimit(40);
      leftMotor_1.setOpenLoopRampRate(rampRate);

    leftMotor_2 = new CANSparkMax(Constants.DriveBase.leftMotor_2_ID, MotorType.kBrushless);
      leftMotor_2.follow(leftMotor_1);
      leftMotor_2.setInverted(false);
      leftMotor_2.setSmartCurrentLimit(40);
      leftMotor_2.setOpenLoopRampRate(rampRate);

    rightMotor_1 = new CANSparkMax(Constants.DriveBase.rightMotor_1_ID, MotorType.kBrushless);
      rightMotor_1.setInverted(true);
      rightMotor_1.setSmartCurrentLimit(40);
      rightMotor_1.setOpenLoopRampRate(rampRate);

    rightMotor_2 = new CANSparkMax(Constants.DriveBase.rightMotor_2_ID, MotorType.kBrushless);
      rightMotor_2.setInverted(true);
      rightMotor_2.follow(rightMotor_1);
      rightMotor_2.setSmartCurrentLimit(40);
      rightMotor_2.setOpenLoopRampRate(rampRate);

    gearbox = new Solenoid(10, 0);

    gyro_navx = new AHRS(SPI.Port.kMXP);

    arc_Drive = new DifferentialDrive(leftMotor_1, rightMotor_1);
      arc_Drive.setSafetyEnabled(true);
      arc_Drive.setExpiration(0.1);
      arc_Drive.setMaxOutput(1.0);

  }

  public void driveRobot(double Speed, double Rotation)
  {

    arc_Drive.arcadeDrive(Speed, Rotation);

  }

  public void shiftUp(){

    gearbox.set(true);

  }

  public void shiftDown(){

    gearbox.set(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

   
  }
}
