package CupertinoRobotics.support.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class Gyro {

    private final BNO055IMU imu;

    private Unit unit;

    public enum Unit {
        DEGREES,
        RADIANS
    }

    public Gyro(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        setUnit(Unit.RADIANS);
    }

    public Gyro(HardwareMap hardwareMap, Unit unit) {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        setUnit(unit);
    }

    public double getDirection() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle * (unit == Unit.DEGREES ? 180 / Math.PI : 1);
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

}
