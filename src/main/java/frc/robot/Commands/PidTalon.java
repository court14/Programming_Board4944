// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystem.TalonAndNeo;

public class PidTalon extends CommandBase {
  /** Creates a new PidTalon. */

  TalonAndNeo talonandneo;
  PIDController pidcontroller;
  public PidTalon(TalonAndNeo talonandneo , Double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.talonandneo = talonandneo;
    this.pidcontroller = new PIDController(.1, 0, 0);
    pidcontroller.setSetpoint(setpoint);
    addRequirements(talonandneo);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidcontroller.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = pidcontroller.calculate(talonandneo.getEncoderMeter());
    talonandneo.setPowerTo(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    talonandneo.setPowerTo(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
