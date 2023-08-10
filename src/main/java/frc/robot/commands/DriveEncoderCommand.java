// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PIDConstants;
import frc.robot.subsystems.drive;

public class DriveEncoderCommand extends CommandBase {

private final PIDController drivePID;
drive driveSub;
double d;
private boolean done;

  public DriveEncoderCommand(double distance, drive drive) {
    driveSub = drive;
    d = distance;


    drivePID = new PIDController(PIDConstants.kP, PIDConstants.kI, PIDConstants.kD);
    addRequirements(driveSub);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    drivePID.setTolerance(.05);
    drivePID.reset();
    driveSub.resetEncoders();
    System.out.println("\n\nCommand has started\n\n");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    done = drivePID.atSetpoint();
    double speed = drivePID.calculate(driveSub.LeftTicksToMeters(), d);
    driveSub.move(-speed, -speed);

    SmartDashboard.putBoolean("Drive Command Check", done);
    SmartDashboard.putNumber("Drive Tolerance", drivePID.getPositionError());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    driveSub.move(0,0);
    System.out.println("\n\n\nCommand has finished\n\n");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
