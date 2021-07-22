package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "Gyro Test")
public class Lab11Template_GyroTest extends LinearOpMode {

    BNO055IMU imu; //The gyro is a IMU (inertial measurement unit), and the class we can use to interface it is BNO055IMU

    @Override
    public void runOpMode() throws InterruptedException {
        imu = (BNO055IMU) hardwareMap.get("imu1"); //Hardware mapping the IMU is slightly different than the other devices you have used

        BNO055IMU.Parameters params = new BNO055IMU.Parameters(); //The IMU can return data in a variety of ways, the parameters object can be used to change any of the defaults to a specified parameter
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Set angle unit to be in degrees
        imu.initialize(params); //Assign parameters

        //YOUR CODE HERE

    }
}
