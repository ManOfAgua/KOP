// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drive;
import frc.robot.Constants.PIDConstants;

public class TurnPIDCommand extends CommandBase {

  private drive driveSub;
  private PIDController turnPID;
  private boolean done;
  private double turn;

  /** Creates a new TurnPIDCommand. */
  public TurnPIDCommand(double t, drive drive) {
    driveSub = drive;
    turn = t;
    turnPID = new PIDController(PIDConstants.turnKP, PIDConstants.turnKI, PIDConstants.turnKD);

    addRequirements(driveSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("\n\nTurn Command Has Started\n\n");
    driveSub.resetEncoders();
    turnPID.reset();
    turnPID.setTolerance(0.001);



    int number = 0;
    if(number == 0)
    {
        System.out.println("\n\nNumber Equal To 0\n");
    }

    else if(number == 1)
    {
      System.out.println("\n\nNumber Equal To 1\n");

    }
    else
    {
      System.out.println("\n\nNumber Is Not Equal To 0 or 1\n");
    }

  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    done = turnPID.atSetpoint();

    

    if (turn > 0) {
      double speed = turnPID.calculate(driveSub.LeftTicksToMeters(), turn);

      driveSub.move(-speed, speed);
      SmartDashboard.putBoolean("Turn Check", done);
      SmartDashboard.putNumber("Turn Error", speed);

    }

    else {
      
      double speed = turnPID.calculate(driveSub.LeftTicksToMeters(), turn);

      driveSub.move(-speed, speed);
      SmartDashboard.putBoolean("Turn Check", done);
      SmartDashboard.putNumber("Turn Error", speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSub.move(0, 0);
    System.out.println("\n\nTurn Command Has Finished\n\n");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
