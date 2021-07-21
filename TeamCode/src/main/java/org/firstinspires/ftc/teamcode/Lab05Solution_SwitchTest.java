package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Switch Test")
public class Lab05Solution_SwitchTest extends LinearOpMode {

    private TouchSensor digitalSwitch;

    @Override
    public void runOpMode() throws InterruptedException {
        digitalSwitch = hardwareMap.touchSensor.get("testswitch"); //Map the hardware to the object

        waitForStart();

        while(opModeIsActive()){ //Loop until termination
            telemetry.addData("Status", digitalSwitch.isPressed() ? "pressed" : "unpressed"); //if the digitalSwitch returns true for pressed, print "pressed", otherwise, "unpressed"
            telemetry.update();

            sleep(50); //interval
        }

        stop();
    }
}
