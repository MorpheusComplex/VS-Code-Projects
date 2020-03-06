/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


/**
 * Add your docs here.
 */
public class ClimbSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public TalonSRX m_liftMotor = new TalonSRX(18);


  public ClimbSubsystem(){
    m_liftMotor.configFactoryDefault();
    
    m_liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    m_liftMotor.configNominalOutputForward(0, RobotMap.kTimeoutMs);
		m_liftMotor.configNominalOutputReverse(0, RobotMap.kTimeoutMs);
		m_liftMotor.configPeakOutputForward(1.0, RobotMap.kTimeoutMs);
    m_liftMotor.configPeakOutputReverse(-0.2, RobotMap.kTimeoutMs);
    m_liftMotor.configAllowableClosedloopError(0, RobotMap.kPIDLoopIdx, RobotMap.kTimeoutMs);
    m_liftMotor.setInverted(RobotMap.kMotorInvert);
		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		m_liftMotor.config_kF(RobotMap.kPIDLoopIdx, RobotMap.kP, RobotMap.kTimeoutMs);
		m_liftMotor.config_kP(RobotMap.kPIDLoopIdx, RobotMap.kP, RobotMap.kTimeoutMs);
		m_liftMotor.config_kI(RobotMap.kPIDLoopIdx, RobotMap.kI, RobotMap.kTimeoutMs);
		m_liftMotor.config_kD(RobotMap.kPIDLoopIdx, RobotMap.kD, RobotMap.kTimeoutMs);
    m_liftMotor.configClosedloopRamp(RobotMap.kRampRate);

  }

  public int getClimb(){

    return m_liftMotor.getSelectedSensorPosition();
  }

  public void liftPole(){

    m_liftMotor.set(ControlMode.Position, 4096*2.7);
    

  }

  public void dropPole(){

    m_liftMotor.set(ControlMode.Position, 0);
    

    

  }

  public void climb(){


  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
