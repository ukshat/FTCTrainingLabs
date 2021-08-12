package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import CupertinoRobotics.support.Hardware.Gyro;

@TeleOp(name = "Gyro Test Solution")
public class Lab11Solution_GyroTest extends LinearOpMode {

    Gyro gyro;

    @Override
    public void runOpMode() throws InterruptedException {

        gyro = new Gyro(hardwareMap);

        waitForStart();

        while (opModeIsActive()) { // Loop until termination, while counting the number of iterations
            double angle = gyro.getAngle();

            telemetry.addData("Angle", Math.round(angle));
            telemetry.update();

            sleep(50);
        }
    }
}
