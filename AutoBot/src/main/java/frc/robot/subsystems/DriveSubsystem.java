/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.commands.DriveCommand;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


/**
 * Add your docs here.
 */
  
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public CANSparkMax m_l_leftMotor = new CANSparkMax(11, MotorType.kBrushless);
  public CANSparkMax m_f_leftMotor = new CANSparkMax(12, MotorType.kBrushless);
  public CANSparkMax m_l_rightMotor = new CANSparkMax(13, MotorType.kBrushless);
  public CANSparkMax m_f_rightMotor = new CANSparkMax(14, MotorType.kBrushless);
  public DifferentialDrive m_drive = new DifferentialDrive(m_l_leftMotor, m_l_rightMotor);
  //public DoubleSolenoid m_shifters = new DoubleSolenoid(20,0,1);
  
  public DriveSubsystem(){

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

  public void Drive(double speed, double turn){

    m_drive.arcadeDrive(speed, turn);

    SmartDashboard.putNumber("m_l_leftMotor", m_l_leftMotor.getEncoder().getPosition());
    SmartDashboard.putNumber("m_f_leftMotor", m_f_leftMotor.getEncoder().getPosition());
    SmartDashboard.putNumber("m_l_rightMotor", m_l_rightMotor.getEncoder().getPosition());
    SmartDashboard.putNumber("m_f_rightMotor", m_f_rightMotor.getEncoder().getPosition());

  }

  public double ConvertDistance(double distance){

  
    return distance * 0.65;

  
  }

  public double GetAverageDistance(){
    double averageDistance = 
    (m_l_leftMotor.getEncoder().getPosition() +
    m_f_leftMotor.getEncoder().getPosition() +
    -m_l_rightMotor.getEncoder().getPosition() +
    -m_f_rightMotor.getEncoder().getPosition()) / 4;

    


    return averageDistance;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new DriveCommand());
  }
}
