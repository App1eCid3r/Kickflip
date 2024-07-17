// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

// WPI imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
// File imports
import frc.robot.Controls;
import frc.robot.Subsystems.Drivebase;

public class Drivin extends CommandBase {
  /** Creates a new Drivin. */
  private Drivebase drivin;
  // Left and Right speeds
  public double left;
  public double right;
  // X and Y axis of joystick
  public double x;
  public double y;
  public double sensitivity;
  public boolean isPrecision;
  public boolean isBreak;
  private XboxController driver;
  public boolean endCommand;

  public Drivin(Drivebase m_drivin) {
    driver = Controls.xbox_driver;
    drivin = m_drivin;

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
    // Joystick up Y is reversed
    x = (driver.getLeftX());
    y = (driver.getRightTriggerAxis()-driver.getLeftTriggerAxis());

    isPrecision = driver.getXButtonPressed();
    isBreak = driver.getAButtonPressed();

    // Always innocent till proven guilty
    left = 0;
    right = 0;
    sensitivity = 1;

    if (isBreak) {
      sensitivity = Constants.DrivebaseConstants.BREAK;
    } else if (isPrecision) {
      sensitivity = Constants.DrivebaseConstants.PRECISION_SENS;
    }

    if (0.15 < x) {  // Jack is a programmer tell him what he thinks this does (Basic Chart)
      left = x * sensitivity;
      right = (-x+y) * sensitivity;
    } else if (x < -0.25) {
      left = (x+y) * sensitivity;
      right = -x * sensitivity;
    } else if ((0.25 < y) || (y < -0.25)) {
      left = y * sensitivity;
      right = y * sensitivity;
    }

    drivin.drivin(left, right);
    endCommand = true;
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
