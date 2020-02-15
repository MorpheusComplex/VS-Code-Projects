/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This is a demo program showing the use of the DifferentialDrive class.
 * Runs the motors with arcade steering.
 */
public class Robot extends TimedRobot {

  private final CANSparkMax m_l_leftMotor = new CANSparkMax(11, MotorType.kBrushless);
  private final CANSparkMax m_l_rightMotor = new CANSparkMax(13, MotorType.kBrushless);
  private final CANSparkMax m_f_leftMotor = new CANSparkMax(12, MotorType.kBrushless);
  private final CANSparkMax m_f_rightMotor = new CANSparkMax(14, MotorType.kBrushless);

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_l_leftMotor, m_l_rightMotor);
  private final Joystick m_stick = new Joystick(0);

  @Override
  public void robotInit() {

    m_f_leftMotor.follow(m_l_leftMotor);
    m_f_rightMotor.follow(m_l_rightMotor);

    m_l_leftMotor.setSmartCurrentLimit(40);
    m_l_rightMotor.setSmartCurrentLimit(40);
    m_f_leftMotor.setSmartCurrentLimit(40);
    m_f_rightMotor.setSmartCurrentLimit(40);
    
    m_l_leftMotor.setOpenLoopRampRate(1);
    m_l_rightMotor.setOpenLoopRampRate(1);
    m_f_leftMotor.setOpenLoopRampRate(1);
    m_f_rightMotor.setOpenLoopRampRate(1);

  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
  }
}
