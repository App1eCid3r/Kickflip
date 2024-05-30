// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controls;
import frc.robot.Subsystems.Drivebase;

public class Drivin extends CommandBase {
  /** Creates a new Drivin. */
  private Drivebase drivin;
  public double speed;
  public double right;
  public double left;
  public String side;
  private XboxController operator;
  public boolean endCommand;

  public Drivin(Drivebase m_drivin, String m_side) {
    operator = Controls.xbox_operator;
    drivin = m_drivin;
    side = m_side;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivin);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    endCommand = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (side == "right") {
      speed = operator.getRightTriggerAxis();
    } else {
      speed = operator.getLeftTriggerAxis();
    }
    drivin.move(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
