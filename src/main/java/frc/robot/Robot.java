// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Subsystem.NeoColor;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  public static XboxController driver;
  public static XboxController operator;

  public static NeoColor neocolor;
  public static Compressor compressor;
  public static Solenoid bigsolenoid;

  public static RobotContainer robotContainer;
  
  private static final int PH_CAN_ID = 5;
  PneumaticHub m_ph = new PneumaticHub(PH_CAN_ID);
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

     // Add buttons to enable/disable the compressor
     SmartDashboard.setDefaultBoolean("Enable Compressor Hybrid", false);
     SmartDashboard.setDefaultBoolean("Disable Compressor", false);
 
     // Add number inputs for minimum and maximum pressure
     SmartDashboard.setDefaultNumber("Minimum Pressure (PSI)", 100.0);
     SmartDashboard.setDefaultNumber("Maximum Pressure (PSI)", 120.0);

    neocolor = new NeoColor();

    compressor = new Compressor(8, PneumaticsModuleType.CTREPCM);
    bigsolenoid = new Solenoid(5, PneumaticsModuleType.REVPH, 7);
    
    robotContainer = new RobotContainer();
  
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    /**
     * Get digital pressure switch state and display on Shuffleboard.
     */
    SmartDashboard.putBoolean("Digital Pressure Switch",
    m_ph.getPressureSwitch());

    /**
    * Get pressure from analog channel 0 and display on Shuffleboard.
    */
    SmartDashboard.putNumber("Pressure", m_ph.getPressure(0));


    /**
    * Get compressor running status and display on Shuffleboard.
    */
    SmartDashboard.putBoolean("Compressor Running", m_ph.getCompressor());

    // Enable Compressor Hybrid button
    if (SmartDashboard.getBoolean("Enable Compressor Hybrid", false)) {
      SmartDashboard.putBoolean("Enable Compressor Hybrid", false);

      // Get values from Shuffleboard
      double minPressure =
      SmartDashboard.getNumber("Minimum Pressure (PSI)", 0.0);
      double maxPressure =
      SmartDashboard.getNumber("Maximum Pressure (PSI)", 0.0);

      /**
      * Enable the compressor with hybrid sensor control, meaning it uses both
      * the analog and digital pressure sensors.
      *
      * This uses hysteresis between a minimum and maximum pressure value,
      * the compressor will run when the sensor reads below the minimum pressure
      * value, and the compressor will shut off once it reaches the maximum.
      *
      * If at any point the digital pressure switch is open, the compressor will
      * shut off.
      */
      m_ph.enableCompressorHybrid(minPressure, maxPressure);
      
    }


  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    compressor.enableDigital();

    bigsolenoid.set(false);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();

  }


  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}