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
    private final SparkMax m_leftFrontMotor = new SparkMax(11, MotorType.kBrushless);
    private final SparkMax m_rightFrontMotor = new SparkMax(12, MotorType.kBrushless);
    private final SparkMax m_leftRearMotor = new SparkMax(13, MotorType.kBrushless);
    private final SparkMax m_rightRearMotor = new SparkMax(14, MotorType.kBrushless);
    SparkMaxConfig leftConfig = new SparkMaxConfig();
    SparkMaxConfig rightConfig = new SparkMaxConfig();


    public Tank_Drive() {
        leftConfig.follow(m_leftRearMotor);
        rightConfig
            .inverted(true)
            .follow(m_rightRearMotor);

        m_leftFrontMotor.configure(leftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        m_rightFrontMotor.configure(rightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        m_robotDrive = new DifferentialDrive(m_leftFrontMotor, m_rightFrontMotor);
    }

    public Command drive(double speed, double rotation){
        return run(
            () -> {
                m_robotDrive.arcadeDrive(speed, rotation);
            });
    }

    public Command stop(){
        return runOnce(
            () -> {
                m_robotDrive.arcadeDrive(0, 0);
            });
    }
}
