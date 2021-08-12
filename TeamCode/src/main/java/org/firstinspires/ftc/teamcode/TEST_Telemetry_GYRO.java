package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import CupertinoRobotics.support.Hardware.Gyro;
import CupertinoRobotics.support.Hardware.LEDIndicator;
import CupertinoRobotics.support.Hardware.Potentiometer;
import CupertinoRobotics.support.Telemetry.PrintMode;
import CupertinoRobotics.support.Telemetry.SimplePrintStream;

@TeleOp(name = "telem gyro test")
public class TEST_Telemetry_GYRO extends LinearOpMode {
    SimplePrintStream telem;
    Gyro gyro;

    @Override
    public void runOpMode() throws InterruptedException {
        telem = new SimplePrintStream(telemetry);
        gyro = new Gyro(hardwareMap);
        gyro.setUnit(AngleUnit.DEGREES);

        waitForStart();

        for(int i = 0; opModeIsActive(); i++){
            telem.println(gyro.getAngle());
            if(i % 5 == 4)
                telem.speak(Math.round(gyro.getAngle()*10)/10.0 + gyro.getUnit().toString());

            sleep(400);
        }
    }
}
