// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

// WPI imports
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

// File imports
import frc.robot.Controls;
import frc.robot.Subsystems.Drivebase;

// Math import??
import java.lang.Math;

public class Drivin extends CommandBase {
  /** Creates a new Drivin. */
  private Drivebase drivin;
  public double left;
  public double right;
  public double x;
  public double y;
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
    y = -(driver.getLeftY());

    left = 0;
    right = 0;

    if ((0.1 < y) || (y < -0.1)) {
      left = y;
      right = y;
    } else if (0.1 < x) {
      left = x;
      right = y-x;
    } else if (x < -0.1) {
      left = y+x;
      right = -x;
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
