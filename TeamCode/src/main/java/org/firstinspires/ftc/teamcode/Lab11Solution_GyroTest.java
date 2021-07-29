package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "Gyro Test Solution")
public class Lab11Solution_GyroTest extends LinearOpMode {

    BNO055IMU imu; //The gyro is a IMU (inertial measurement unit), and the class we can use to interface it is BNO055IMU

    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(BNO055IMU.class, "imu"); //Hardware map IMU

        BNO055IMU.Parameters params = new BNO055IMU.Parameters(); //The IMU can return data in a variety of ways, the parameters object can be used to change any of the defaults to a specified parameter
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Set angle unit to be in degrees
        imu.initialize(params); //Assign parameters

        waitForStart();

        for(int i = 0; opModeIsActive(); i++){ //Loop until termination, while counting the number of iterations
            /* This line is a little hefty...

            First, imu.getAngularOrientation returns an Orientation object that specifies the orientation of the gyro in reference to the starting position (or wherever it is zeroed);
            This orientation object can be with respect to many different ways of tracking orientation, but here is a quick summary of the way we have chosen.

            The first parameter indicates whether we have intrinsic rotations, where the axes move with the object that is rotating, or extrinsic rotations, where they remain fixed in the world around the object.
            The axes order parameter simply defines the order in which the angles are presented in the object (we want to know the rotation around the z-axis, so for simplicity we can put that first)
            Last parameter merely specifies degrees again. Although we specified this in params, this way of calling the method to specify intrinsic rotation requires we specify units again.
             */
            float angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            if(i % 20 == 0)//we do not want to print 20 times a second, so by only printing every 20 iterations, it will only print once a second
                telemetry.addData("Angle", Math.round(angle));

            sleep(50);
        }
    }
}
