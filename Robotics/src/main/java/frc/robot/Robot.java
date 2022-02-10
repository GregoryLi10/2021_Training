// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick.ButtonType;

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
  public CANSparkMax intake = new CANSparkMax(7, MotorType.kBrushless);
  public CANSparkMax left1=new CANSparkMax(1, MotorType.kBrushless);
  public CANSparkMax left2=new CANSparkMax(2, MotorType.kBrushless);
  public CANSparkMax left3=new CANSparkMax(3, MotorType.kBrushless);
  public CANSparkMax right1=new CANSparkMax(4, MotorType.kBrushless);
  public CANSparkMax right2=new CANSparkMax(5, MotorType.kBrushless);
  public CANSparkMax right3=new CANSparkMax(6, MotorType.kBrushless);
  public Joystick JS=new Joystick(0);
  public JoystickButton intakeB=new JoystickButton(JS, 13);
  private boolean on=false, pressed=false;

  public void intakeHi(){
    if (intakeB.get()&&!pressed){
      on=!on; 
      pressed=true;
    } 
    else pressed=false;
    if (on) intake.set(0.8);
    else intake.set(0);
  }

  public void drive(){
    double x=JS.getRawAxis(0); 
    double y=JS.getRawAxis(1);
    // System.out.println(y);
    // double z=

    left1.set(y-x); 
    left2.set(y-x); 
    left3.set(y-x);
    right1.set(y+x); 
    right2.set(y+x); 
    right3.set(y+x);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

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
    left1.setInverted(true);
    left2.setInverted(true);
    left3.setInverted(true);
    right1.setInverted(false); 
    right2.setInverted(false); 
    right3.setInverted(false);
    intake.setInverted(true);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    intakeHi();
    drive();
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
