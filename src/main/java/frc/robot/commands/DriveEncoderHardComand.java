// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.PIDConstants;
import frc.robot.subsystems.drive;

public class DriveEncoderHardComand extends CommandBase {
private final PIDController drivePID;
drive driveSub;
double d;
private boolean done;



  public DriveEncoderHardComand(double distance, drive drive) {
    driveSub = drive;
    d = distance;

    drivePID = new PIDController(PIDConstants.hardKP, PIDConstants.hardKI, PIDConstants.hardKD);

    addRequirements(driveSub);


  }


  @Override
  public void initialize() 
  {
    drivePID.setTolerance(.05);
    drivePID.reset();
    driveSub.resetEncoders();
    System.out.println("Drive Hard Command Has Started");

  }


  @Override
  public void execute() 
  {
    done = drivePID.atSetpoint();
    double speed = drivePID.calculate(driveSub.LeftTicksToMeters(), d);
    driveSub.move(-speed, -speed);

SmartDashboard.putBoolean("Drive Hard Complete", done);
SmartDashboard.putNumber("Hard Tolerance", drivePID.getPositionError());
  }



  @Override
  public void end(boolean interrupted) 
  {
    driveSub.move(0, 0);
    System.out.println("Drive Hard Command Has Finished");
  }


  @Override
  public boolean isFinished() {
    return done;
  }
}
