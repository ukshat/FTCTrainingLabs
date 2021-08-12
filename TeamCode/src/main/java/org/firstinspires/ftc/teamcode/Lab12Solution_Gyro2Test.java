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

@TeleOp(name = "Gyro Test 2 Solution")
public class Lab12Solution_Gyro2Test extends LinearOpMode {

    Gyro gyro;

    @Override
    public void runOpMode() throws InterruptedException {
        gyro = new Gyro(hardwareMap, AngleUnit.DEGREES);

        double lastAngle = gyro.getAngle();
        double currentAngle, netAngle = 0;

        waitForStart();

        while (opModeIsActive()) {
            currentAngle = gyro.getAngle();
            double diff = currentAngle - lastAngle;

            if(lastAngle != 0 && currentAngle / lastAngle < 0 && Math.abs(currentAngle) > 90 && Math.abs(lastAngle) > 90) {
                if (lastAngle < 0) diff = 360 - diff;
                else diff = 360 + diff;
            }
            netAngle += diff;

            lastAngle = currentAngle; //update last angle

            telemetry.addData("Angle", netAngle + " Degrees");
            telemetry.update();

            sleep(50);
        }
    }
}
