package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Hello World Solution")
public class Lab02Solution_HelloWorld extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(); // Wait for the ▶ button to be pressed

        // If you don't surround the telemetry with a while loop, it will immediately go away after you print it because
        // the OpMode would have ended.
        while (opModeIsActive()) {
            telemetry.addLine("Hello World"); // Add line to telemetry queue
            telemetry.update(); // Update telemetry queue, prints "Hello World" to the driver station
            sleep(1000);
        }

    }
}
