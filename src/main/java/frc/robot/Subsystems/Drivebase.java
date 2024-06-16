// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

// wpi imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// rev imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class Drivebase extends SubsystemBase {
  /** Creates a new Drivebase. */
    private CANSparkMax leftDrive1;
    private CANSparkMax leftDrive2;
    private CANSparkMax rightDrive1;
    private CANSparkMax rightDrive2;

  public Drivebase() {
    // Initiates CANSparkMax'i
    leftDrive1 = new CANSparkMax(Constants.DrivebaseConstants.LEFT_DRIVE_1_ID, MotorType.kBrushed);
    leftDrive2 = new CANSparkMax(Constants.DrivebaseConstants.LEFT_DRIVE_2_ID, MotorType.kBrushed);
    rightDrive1 = new CANSparkMax(Constants.DrivebaseConstants.RIGHT_DRIVE_1_ID, MotorType.kBrushed);
    rightDrive2 = new CANSparkMax(Constants.DrivebaseConstants.RIGHT_DRIVE_2_ID, MotorType.kBrushed);
    
    // Idlemode
    leftDrive1.setIdleMode(IdleMode.kBrake);
    leftDrive2.setIdleMode(IdleMode.kBrake);
    rightDrive1.setIdleMode(IdleMode.kBrake);
    rightDrive2.setIdleMode(IdleMode.kBrake);

    // Factory Defaults
    leftDrive1.restoreFactoryDefaults();
    leftDrive2.restoreFactoryDefaults();
    rightDrive1.restoreFactoryDefaults();
    rightDrive2.restoreFactoryDefaults();
    
    // Unison March
    leftDrive2.follow(leftDrive1);
    rightDrive2.follow(rightDrive1);
    
    // Inverts left direction
    leftDrive1.setInverted(true);
  }

  public void drivin(double Lspeed, double Rspeed) {
    // We want our left and rightside to move independently of eachother (Different speeds)
    leftDrive1.set(Lspeed);
    rightDrive1.set(Rspeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
