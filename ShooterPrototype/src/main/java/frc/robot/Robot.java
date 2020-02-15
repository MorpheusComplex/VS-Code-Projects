/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This sample program shows how to control a motor using a joystick. In the
 * operator control part of the program, the joystick is read and the value is
 * written to the motor.
 *
 * <p>Joystick analog values range from -1 to 1 and speed controller inputs also
 * range from -1 to 1 making it easy to work together.
 */
public class Robot extends TimedRobot {
  private static final int kLeftMotorPort = 11;
  private static final int kRightMotorPort = 12;
  private static final int kJoystickPort = 0;

  private CANSparkMax m_Leftmotor;
  private CANSparkMax m_Rightmotor;
  private Joystick m_joystick;

  @Override
  public void robotInit() {
    
    m_Leftmotor = new CANSparkMax(kLeftMotorPort, MotorType.kBrushless);
    m_Leftmotor.restoreFactoryDefaults();
    m_Leftmotor.setSmartCurrentLimit(40);
    m_Leftmotor.setInverted(false);
    m_Rightmotor = new CANSparkMax(kRightMotorPort, MotorType.kBrushless);
    m_Rightmotor.restoreFactoryDefaults();
    m_Rightmotor.setSmartCurrentLimit(40);
    m_Rightmotor.setInverted(false);
    m_joystick = new Joystick(kJoystickPort);
  }

  @Override
  public void teleopPeriodic() {
    m_Leftmotor.set(-m_joystick.getY());
    m_Rightmotor.set(m_joystick.getY());
    
  }
}
