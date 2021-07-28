package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Switch Test")
public class Lab05Template_TouchTest extends LinearOpMode {

    private TouchSensor digitalSwitch;

    @Override
    public void runOpMode() throws InterruptedException {
        digitalSwitch = hardwareMap.touchSensor.get("touch"); // Map the hardware to the object

        //YOUR CODE HERE

    }
}
