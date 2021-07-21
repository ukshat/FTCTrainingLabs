package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Hello World")
public class Lab2Solution_HelloWorld extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        telemetry.addData("", "Hello World");
        telemetry.update();
    }
}
