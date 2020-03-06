/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

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
  private static final int kLeftMotorPort = 16;
  //private static final int kRightMotorPort = 11;
  //private static final int kTopBelt = 15;
  //private static final int kBotBelt = 16;
  //private static final int kJoystickPort = 0;
  //private double beltSpeed = 0;

  //private CANSparkMax m_Leftmotor;
  //private CANSparkMax m_Rightmotor;
  private DoubleSolenoid m_intake;
  //private CANSparkMax m_TopBelt;
  //private CANSparkMax m_BotBelt;
  private Joystick m_joystick;

  @Override
  public void robotInit() {
    
    //m_Leftmotor = new CANSparkMax(kLeftMotorPort, MotorType.kBrushless);
    //m_Leftmotor.restoreFactoryDefaults();
    //m_Leftmotor.setSmartCurrentLimit(40);
    //m_Leftmotor.setOpenLoopRampRate(0.1);
    //m_Leftmotor.setInverted(false);
    
    //m_Rightmotor = new CANSparkMax(kRightMotorPort, MotorType.kBrushless);
    //m_Rightmotor.restoreFactoryDefaults();
    //m_Rightmotor.setSmartCurrentLimit(40);
    //m_Rightmotor.setOpenLoopRampRate(0.1);
    //m_Rightmotor.setInverted(false);

    m_intake = new DoubleSolenoid(20, 0, 1);
/*
    m_BotBelt = new CANSparkMax(kBotBelt, MotorType.kBrushless);
    m_BotBelt.restoreFactoryDefaults();
    m_BotBelt.setSmartCurrentLimit(20);
    m_BotBelt.setOpenLoopRampRate(0.7);
    m_BotBelt.setInverted(false);
    m_TopBelt = new CANSparkMax(kTopBelt, MotorType.kBrushless);
    m_TopBelt.restoreFactoryDefaults();
    m_TopBelt.setSmartCurrentLimit(20);
    m_TopBelt.setOpenLoopRampRate(0.7);
    m_TopBelt.setInverted(true);*/
    m_joystick = new Joystick(0);
    
  }

  @Override
  public void teleopPeriodic() {
    //m_Leftmotor.set(-m_joystick.getY());
    //m_Rightmotor.set(m_joystick.getY());
   // m_BotBelt.set(beltSpeed);
    //m_TopBelt.set(beltSpeed);

    if (m_joystick.getRawButton(1)){

      m_intake.set(Value.kReverse);


     // beltSpeed = 1;
    }
    else {

      m_intake.set(Value.kForward);

      //beltSpeed = 0;

    }
    
  }
}
