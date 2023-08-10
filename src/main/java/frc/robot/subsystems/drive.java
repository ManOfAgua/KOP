// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriverConstants;

public class drive extends SubsystemBase {

  WPI_TalonFX leftFront = new WPI_TalonFX(DriverConstants.leftFront);
  WPI_TalonFX leftBack = new WPI_TalonFX(DriverConstants.leftBack);

  WPI_TalonFX rightFront = new WPI_TalonFX(DriverConstants.rightFront);
  WPI_TalonFX rightBack = new WPI_TalonFX(DriverConstants.rightBack);

  MotorControllerGroup leftSide = new MotorControllerGroup(leftBack, leftFront);
  MotorControllerGroup rightSide = new MotorControllerGroup(rightFront, rightBack);

  DifferentialDrive drive = new DifferentialDrive(leftSide, rightSide);

  PowerDistribution power = new PowerDistribution(0, ModuleType.kCTRE);


  /** Creates a new drive. */
  public drive() 
  {
followSides();
resetEncoders();
System.out.println("Sub works!!!");
  }
public void followSides()
{
  leftBack.follow(leftFront);
  rightBack.follow(rightFront);
}
  public double LeftTicksToMeters()
  {
    double motorRotations = leftFront.getSelectedSensorPosition() / DriverConstants.kCountsPerRev;
    double wheelRotation = motorRotations / DriverConstants.kGearRatio;

    double positionMeters = wheelRotation * (2 * Math.PI * Units.inchesToMeters(DriverConstants.kWheelRadiusInches));
    return positionMeters;
  }

  public double RightTicksToMeters()
  {
    double motorRotations = rightFront.getSelectedSensorPosition() / DriverConstants.kCountsPerRev;
    double wheelRotation = motorRotations / DriverConstants.kGearRatio;

    double positionMeters = wheelRotation * (2 * Math.PI * Units.inchesToMeters(DriverConstants.kWheelRadiusInches));
    return -positionMeters;
  }
public void move(double leftSpeed, double rightSpeed) 
{
  leftSide.setInverted(true);
  drive.tankDrive(leftSpeed, rightSpeed);
}

public void resetEncoders()
{
  leftFront.setSelectedSensorPosition(0);
  leftBack.setSelectedSensorPosition(0);

  rightFront.setSelectedSensorPosition(0);
  rightBack.setSelectedSensorPosition(0);

}

  @Override
  public void periodic() 
  {
SmartDashboard.putNumber("Left Side Meters", LeftTicksToMeters());
SmartDashboard.putNumber("Right Side Meters", RightTicksToMeters());

SmartDashboard.putNumber("Amperage Left Front", power.getCurrent(4));


  }
}