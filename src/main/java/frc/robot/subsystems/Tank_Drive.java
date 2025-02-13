package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Tank_Drive extends SubsystemBase{
    private DifferentialDrive m_robotDrive;
    private final SparkMax m_leftFrontMotor = new SparkMax(6, MotorType.kBrushless);
    private final SparkMax m_rightFrontMotor = new SparkMax(7, MotorType.kBrushless);
    private final SparkMax m_leftRearMotor = new SparkMax(8, MotorType.kBrushless);
    private final SparkMax m_rightRearMotor = new SparkMax(9, MotorType.kBrushless);
    SparkMaxConfig leftConfig = new SparkMaxConfig();
    SparkMaxConfig rightConfig = new SparkMaxConfig();


    public Tank_Drive() {
        leftConfig.follow(m_leftRearMotor);
        rightConfig.inverted(true);
        rightConfig.follow(m_rightRearMotor);

        m_leftFrontMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightFrontMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        m_robotDrive = new DifferentialDrive(m_leftFrontMotor, m_rightFrontMotor);
    }

    public Command forward(){
        return runOnce(
            () -> {
                m_robotDrive.tankDrive(1, 1);
            });
    }

    public Command backward(){
        return runOnce(
            () -> {
                m_robotDrive.tankDrive(-1, -1);
            });
    }

    public Command left(){
        return runOnce(
            () -> {
                m_robotDrive.tankDrive(-1, 1);
            });
    }

    public Command right(){
        return runOnce(
            () -> {
                m_robotDrive.tankDrive(1, -1);
            });
    }

    public Command stop(){
        return runOnce(
            () -> {
                m_robotDrive.tankDrive(0, 0);
            });
    }
}
