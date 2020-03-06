/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public CANSparkMax intakeMotor = new CANSparkMax(19, MotorType.kBrushless);

  public DoubleSolenoid intakeBoom = new DoubleSolenoid(20, 0, 1);

  public IntakeSubsystem(){

    intakeMotor.restoreFactoryDefaults();
    intakeMotor.setInverted(true);
    intakeMotor.setSmartCurrentLimit(40);
    intakeMotor.setOpenLoopRampRate(0.25);

  }

  public void intakeIn(){
    intakeMotor.set(1);
    if (Robot.m_oi.getStick().getRawButton(3)) intakeOut();
  
  }

  public void intakeOut(){intakeMotor.set(-1);}

  public void intakeStop(){intakeMotor.set(0);}

  public void intakeUp(){intakeBoom.set(Value.kForward);}

  public void intakeDown(){intakeBoom.set(Value.kReverse);}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
