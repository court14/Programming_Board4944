// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NeoColor extends SubsystemBase {
  /** Creates a new NeoColor. */
  public CANSparkMax neocolorsensor;


  public NeoColor() {
    neocolorsensor = new CANSparkMax(4, MotorType.kBrushless);
  }

  public void NeoOn(double power){
    neocolorsensor.set(power);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }



}
