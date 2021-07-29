package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Touch Test Solution")
public class Lab05Solution_TouchTest extends LinearOpMode {

    private TouchSensor touchSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        touchSensor = hardwareMap.touchSensor.get("touch"); // Map the hardware device to the object

        waitForStart();

        while (opModeIsActive()) { // Loop until termination
            // if the digitalSwitch returns true for pressed, print "pressed", otherwise, "unpressed"
            telemetry.addData("Status", touchSensor.isPressed() ? "pressed" : "unpressed");
            telemetry.update();

            sleep(50); // interval
        }
    }
}
