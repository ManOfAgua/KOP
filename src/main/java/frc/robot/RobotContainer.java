// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriverConstants;
import frc.robot.commands.DriveEncoderCommand;
import frc.robot.commands.DriveEncoderHardComand;
import frc.robot.commands.TurnPIDCommand;
import frc.robot.subsystems.drive;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */

 // test 
 
public class RobotContainer {

  private final drive driveSub = new drive();

  private final DriveEncoderCommand meterTravel = new DriveEncoderCommand(3, driveSub);
  private final TurnPIDCommand turnRight = new TurnPIDCommand(.53, driveSub);

  private final Joystick jesse = new Joystick(DriverConstants.jesse);

  private RunCommand jesseMove = new RunCommand(
      () -> driveSub.move(DriverConstants.driveSpeed * jesse.getRawAxis(DriverConstants.leftAxis),
          DriverConstants.driveSpeed * jesse.getRawAxis(DriverConstants.rightAxis)),
      driveSub);

  SendableChooser<Command> chooser = new SendableChooser<>();

  public RobotContainer() {
    driveSub.setDefaultCommand(jesseMove);

    JoystickButton stopButton = new JoystickButton(jesse, 2);
    JoystickButton turnButton = new JoystickButton(jesse, 4);
    turnButton.onTrue(turnRight.until(stopButton));

    chooser.addOption("No Auto", null);
    chooser.addOption("Autonomous", autofinal);

    SmartDashboard.putData(chooser);

  }

  Command stage1 = new SequentialCommandGroup(new DriveEncoderCommand(2.8, driveSub).andThen(
      () -> System.out.println("\n\nStage 1 Finished ")), new WaitCommand(.25));

  SequentialCommandGroup stage2 = new SequentialCommandGroup(new DriveEncoderCommand(2, driveSub).andThen(
      () -> System.out.println("Stage 2 Finished")), new WaitCommand(.25));

  SequentialCommandGroup stage3 = new SequentialCommandGroup(new DriveEncoderCommand(2.36, driveSub).andThen(
      () -> System.out.println("Stage 3 Finished")), new WaitCommand(.25));

  SequentialCommandGroup stage4 = new SequentialCommandGroup(new TurnPIDCommand(-0.58, driveSub).andThen(
      () -> System.out.println("Stage 4 Finished")), new WaitCommand(.25));

  SequentialCommandGroup stage5 = new SequentialCommandGroup(new DriveEncoderHardComand(1.4, driveSub).andThen(
      () -> System.out.println("Stage 5 Finished")), new WaitCommand(.25));

  SequentialCommandGroup stagestage1 = new SequentialCommandGroup(stage1, stage2, stage3, stage4, stage5);


Command returnstage1 = new SequentialCommandGroup(new TurnPIDCommand(-0.58, driveSub).andThen(
  () -> System.out.println("ReturnStage 1 Finished")), new WaitCommand(.5));

  SequentialCommandGroup returnstage2 = new SequentialCommandGroup(new TurnPIDCommand(-0.58, driveSub).andThen(
  () -> System.out.println("ReturnStage 2 Finished")), new WaitCommand(.5));

  SequentialCommandGroup returnstage3 = new SequentialCommandGroup(new DriveEncoderHardComand(1.4, driveSub).andThen(
  () -> System.out.println("ReturnStage 3 Finished")), new WaitCommand(.25));

  SequentialCommandGroup returnstage4 = new SequentialCommandGroup(new TurnPIDCommand(0.215, driveSub).andThen(
  () -> System.out.println("ReturnStage 4 Finished")), new WaitCommand(.5));

SequentialCommandGroup returnstage5 = new SequentialCommandGroup(new DriveEncoderCommand(2.36, driveSub).andThen(
  () -> System.out.println("\n\nReturnStage 5 Finished ")), new WaitCommand(.25));

SequentialCommandGroup returnstage6 = new SequentialCommandGroup(new DriveEncoderCommand(2.36, driveSub).andThen(
  () -> System.out.println("ReturnStage 6 Finished")), new WaitCommand(.25));

SequentialCommandGroup returnstage7 = new SequentialCommandGroup(new DriveEncoderCommand(2, driveSub).andThen(
  () -> System.out.println("ReturnStage 7 Finished")), new WaitCommand(.25));

SequentialCommandGroup stagestage2 = new SequentialCommandGroup(returnstage1, returnstage2, returnstage3, returnstage4, returnstage5, 
returnstage6, returnstage7);



SequentialCommandGroup autofinal = new SequentialCommandGroup(stagestage1, stagestage2);

  

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return chooser.getSelected();
    // return meterTravel;
  }
}
