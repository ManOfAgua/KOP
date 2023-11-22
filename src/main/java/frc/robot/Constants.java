// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public final static class DriverConstants
  {
    public static int 
    leftFront = 1, leftBack = 2, 
    rightFront = 3, rightBack = 4,

    jesse = 0, leftAxis = 1, rightAxis = 5;



    public static double driveSpeed = 0.6,
    
    kCountsPerRev = 2048, kGearRatio = 10.71, kWheelRadiusInches = 3;

  }
public static class PIDConstants 
{
  public static final double 
  
    kP = 0.28, kI = 0.079, kD = 0.0015, 

    hardKP = 0.33, hardKI = 0.082, hardKD = 0.015,

    turnKP = .6, turnKI = .77, turnKD = .042;

    // high kp mid ki mid kd  Aggressive
    // mid kp low ki low kd Mid Speed
}
}
