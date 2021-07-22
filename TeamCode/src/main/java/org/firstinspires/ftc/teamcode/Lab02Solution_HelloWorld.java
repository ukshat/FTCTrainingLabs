package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Hello World")
public class Lab02Solution_HelloWorld extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(); //Self explanatory

        // If you don't surround the telemetry with a while loop, it will immediately go away after you print it because
        // the OpMode would have ended.
        while(opModeIsActive()) {
            telemetry.addLine("Hello World"); //Add line to telemetry queue
            telemetry.update(); //Update telemetry queue, pushes "Hello World" to the driver station
        }

        stop(); //Safely terminates the opMode
    }
}
