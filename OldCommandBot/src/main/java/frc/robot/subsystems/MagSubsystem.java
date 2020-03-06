/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class MagSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public CANSparkMax topBelt = new CANSparkMax(16, MotorType.kBrushless);
  public CANSparkMax botBelt = new CANSparkMax(15, MotorType.kBrushless);

  public MagSubsystem(){
    topBelt.restoreFactoryDefaults();
    topBelt.setInverted(false);
    topBelt.setSmartCurrentLimit(20);
    topBelt.setOpenLoopRampRate(0.25);
     
    
    //Left follower motor settings
    botBelt.restoreFactoryDefaults();
    botBelt.setInverted(false);
    botBelt.setSmartCurrentLimit(20);
    botBelt.setOpenLoopRampRate(0.25);
  

  }

  public void sendIt(){
    topBelt.set(1);
    botBelt.set(-1);
  }

  public void stopBelts(){
    topBelt.set(0);
    botBelt.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
