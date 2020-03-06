/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.RobotMap;
import frc.robot.commands.ShootHighCommand;

/**
 * Add your docs here.
 */
public class ShooterSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public CANSparkMax l_Shooter = new CANSparkMax(17, MotorType.kBrushless);
  public CANSparkMax r_Shooter = new CANSparkMax(18, MotorType.kBrushless);
  public CANEncoder shooterEncoder = l_Shooter.getEncoder();


  public ShooterSubsystem(){

    l_Shooter.restoreFactoryDefaults();
    l_Shooter.setInverted(false);
    l_Shooter.setSmartCurrentLimit(40);
    l_Shooter.setOpenLoopRampRate(0.25);
     
    
    //Left follower motor settings
    r_Shooter.restoreFactoryDefaults();
    r_Shooter.setInverted(true);
    r_Shooter.setSmartCurrentLimit(40);
    r_Shooter.setOpenLoopRampRate(0.25);




  }

  public void shooterHigh(double power){
    if (power < 0.1){
    l_Shooter.set(0);
    r_Shooter.set(0);
    SmartDashboard.putNumber("Shooter Power", power);
  }
    else{
    l_Shooter.set(-power);
    r_Shooter.set(-power);
    }
  }
  

  public void shooterStop(){

    l_Shooter.set(0);
    r_Shooter.set(0);

  }




  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new ShootHighCommand());
  }
}
