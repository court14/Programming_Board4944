// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystem;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TalonAndNeo extends SubsystemBase {
  public TalonFX motor1 = new TalonFX(1);
  DutyCycleEncoder encoder = new DutyCycleEncoder(3);
  double kEncoderTick2Meter = 1.0 / 8192.0 * 0.1 * Math.PI;

  public double getEncoderMeter(){
    return encoder.get() * kEncoderTick2Meter;
  }

  
  /** Creates a new TalonAndNeo. */
  public TalonAndNeo() {
  
    

    this.motor1.setNeutralMode(NeutralMode.Brake);

  }

  public void setPowerTo(double speed) {
  
   motor1.set(ControlMode.PercentOutput, speed);
    
  }

  public double getEncoderPostion(){
    return this.motor1.getSensorCollection().getIntegratedSensorAbsolutePosition();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Talon Encoder Value", kEncoderTick2Meter);
    
  }

}
