/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.AutoAimCommand;
import frc.robot.commands.ChangeGearCommand;
import frc.robot.commands.RunIntakeCommand;
import frc.robot.commands.RunBeltsCommand;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);
  XboxController m_stick = new XboxController(RobotMap.driverController_ID);

  Joystick m_buttons = new Joystick(RobotMap.driverController_ID);
  public Button aButton = new JoystickButton(m_buttons, RobotMap.A_Button);
  public Button bButton = new JoystickButton(m_buttons, RobotMap.B_Button);
  public Button yButton = new JoystickButton(m_buttons, 4);
  public Button leftBumper = new JoystickButton(m_buttons, 5);
  //public Button xButton = new JoystickButton(m_buttons, RobotMap.X_Button);
  //public Button yButton = new JoystickButton(m_buttons, RobotMap.Y_Button);
  //public Button lBumper = new JoystickButton(m_buttons, RobotMap.L_Bumper);
  //public Button rBumper = new JoystickButton(m_buttons, RobotMap.R_Bumper);
  //public Button selectButton = new JoystickButton(m_buttons, RobotMap.Select_Button);
  //public Button menuButton = new JoystickButton(m_buttons, RobotMap.Menu_Button);
  //public Button lStickButton = new JoystickButton(m_buttons, RobotMap.L_Stick_Button);
  //public Button rStickButton = new JoystickButton(m_buttons, RobotMap.R_Stick_Button);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  public OI(){
  aButton.whenPressed(new ChangeGearCommand());
  bButton.toggleWhenPressed(new RunIntakeCommand());
  yButton.whenPressed(new AutoAimCommand());
  leftBumper.whileHeld(new RunBeltsCommand());

  
    //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  }
  public XboxController getStick(){

    return m_stick;
  }

  public double getRightTrigger(){

    return m_stick.getTriggerAxis(Hand.kRight);
  }
 
}


