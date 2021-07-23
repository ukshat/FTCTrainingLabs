package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "Gyro Test 2 Solution")
public class Lab12Solution_Gyro2Test extends LinearOpMode {

    BNO055IMU imu; //The gyro is a IMU (inertial measurement unit), and the class we can use to interface it is BNO055IMU

    @Override
    public void runOpMode() throws InterruptedException {
        imu = (BNO055IMU) hardwareMap.get("imu1"); //Hardware map IMU

        BNO055IMU.Parameters params = new BNO055IMU.Parameters(); //The IMU can return data in a variety of ways, the parameters object can be used to change any of the defaults to a specified parameter
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Set angle unit to be in degrees
        imu.initialize(params); //Assign parameters

        /* This line is a little hefty...

        First, imu.getAngularOrientation returns an Orientation object that specifies the orientation of the gyro in reference to the starting position (or wherever it is zeroed);
        This orientation object can be with respect to many different ways of tracking orientation, but here is a quick summary of the way we have chosen.

        The first parameter indicates whether we have intrinsic rotations, where the axes move with the object that is rotating, or extrinsic rotations, where they remain fixed in the world around the object.
        The axes order parameter simply defines the order in which the angles are presented in the object (we want to know the rotation around the z-axis, so for simplicity we can put that first)
        Last parameter merely specifies degrees again. Although we specified this in params, this way of calling the method to specify intrinsic rotation requires we specify units again.
         */
        float lastAngle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        float currentAngle, netAngle = 0;

        //The very nature of using this algorithm for tracking rotation forces the program to 0 whenever the netAngle variable becomes 0, so no extra steps are required to zero the IMU, and zeroing it in the future is as simple as setting this variable to zero, as it is not reliant on any other external variables

        waitForStart();

        //There are many ways to achieve the desired behaviour where a 270 degree rotation does not return -90, but rather 270.
        //The method I have chosen is mathematically elegant and retains very high precision, consistency, and extremely minimal calculations and code, but is somewhat more complex than other methods
        for(int i = 1; opModeIsActive(); i++){ //loop while the opmode has not ended, while simultaneously counting the number of iterations that have passed
            currentAngle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle; //update current angle
            netAngle += (180 - ((lastAngle - currentAngle + 180) % 360)); //calculate the minimum angular distance between the current position and the last position regardless of a transition from an angle like 179 to -169, in such a way that adding it to the net angle will increment it in the way specified in the instructions
            lastAngle = currentAngle; //update last angle

            if(i % 20 == 0) //we do not want to print 20 times a second, so by only printing every 20 iterations, it will only print once a second
                telemetry.addData("Angle", netAngle + " Degrees");

            sleep(50);
        }
    }
}
