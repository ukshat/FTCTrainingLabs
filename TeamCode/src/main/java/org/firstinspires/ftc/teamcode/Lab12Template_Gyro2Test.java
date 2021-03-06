package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "Gyro Test 2")
public class Lab12Template_Gyro2Test extends LinearOpMode {

    BNO055IMU imu; //The gyro is a IMU (inertial measurement unit), and the class we can use to interface it is BNO055IMU

    @Override
    public void runOpMode() throws InterruptedException {
        //Remember the imu is not hardware mapped like other devices, reference Lab 11 if you forget how to map it

        //YOUR CODE HERE

    }
}
