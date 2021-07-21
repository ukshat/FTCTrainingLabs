package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Color Test")
public class Lab07Solution_ColorTest extends LinearOpMode {

    private ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("testColor"); //hardware map

        waitForStart();

        while(opModeIsActive()){
            //We will print the format of the data in the caption, and the data in the value.
            telemetry.addData("[r],[g],[b],[a]", colorSensor.red() + ", " + colorSensor.green() + ", " + colorSensor.blue() + ", " + colorSensor.alpha());

            sleep(50);
        }

        stop();
    }
}
