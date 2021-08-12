package CupertinoRobotics.support.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Gyro {

    private final BNO055IMU imu;

    private AngleUnit unit;

    public Gyro(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());
        setUnit(AngleUnit.RADIANS);
    }

    public Gyro(HardwareMap hardwareMap, AngleUnit unit) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(new BNO055IMU.Parameters());
        setUnit(unit);
    }

    public double getAngle() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, unit).firstAngle;
    }

    public AngleUnit getUnit() {
        return unit;
    }

    public void setUnit(AngleUnit unit) {
        this.unit = unit;
    }

}
