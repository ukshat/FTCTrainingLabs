package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Encoder Test Solution")
public class Lab14Solution_EncoderTest extends LinearOpMode {

    DcMotor motor; //Define DcMotor

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("testMotor"); //hardware map

        int start = motor.getCurrentPosition(); //in case for some reason the position does not start at 0, we will keep track of the starting position and print all positions relative to this one

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Encoder Count", motor.getCurrentPosition() - start); //Print all position relative to the starting position
            telemetry.update();

            sleep(50);
        }
    }
}
