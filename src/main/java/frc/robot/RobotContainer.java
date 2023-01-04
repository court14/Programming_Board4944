// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Commands.PidTalon;
import frc.robot.Subsystem.NeoColor;
import frc.robot.Subsystem.TalonAndNeo;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static XboxController driver = new XboxController(0);
  public static XboxController operator = new XboxController(1);

  public static TalonAndNeo talonandneo = new TalonAndNeo();
  public static NeoColor neocolor = new NeoColor();
  //public static TurretSubsystem m_TurretSubsystem = new TurretSubsystem();


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    if (driver.getAButtonPressed()){
        talonandneo.setPowerTo(.5);
      }
    if (driver.getAButtonReleased()){
        talonandneo.setPowerTo(0);
    }
    if (driver.getBButtonPressed()){
        neocolor.NeoOn(.5);
      }
    if (driver.getBButtonReleased()){
        neocolor.NeoOn(0);
    }

}


    public Command getAutonomousCommand(){
        return new SequentialCommandGroup(new PidTalon(talonandneo, .5));
    }
    

          
    //new Button(m_controller::getAButton)
            // No requirements because we don't need to interrupt anything
            //.whileHeld(m_TurretSubsystem.TurretSubsystem());
    //new Button(m_controller::getBButton)
            // No requirements because we don't need to interrupt anything
            //.whileHeld(m_TurretSubsystem::MoveTurretRight);        
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
