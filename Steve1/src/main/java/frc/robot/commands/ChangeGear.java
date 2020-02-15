/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ChangeGear extends CommandBase {
  /**
   * Creates a new ChangeGear.
   */
   private final DriveSubsystem m_Drive;

   private boolean isHighGear;

  public ChangeGear(DriveSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Drive = subsystem;
    addRequirements(m_Drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    isHighGear = m_Drive.getGear();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean shiftedDown = false;

    if (isHighGear){
      m_Drive.shiftDown();
      shiftedDown = true;
    }

    else if (!isHighGear & shiftedDown == false) {

      m_Drive.shiftUp();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
