package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Hello World")
public class Lab02Solution_HelloWorld extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(); //Self explanatory

        telemetry.addData("", "Hello World"); //Add data to telemetry queue
        telemetry.update(); //Update telemetry queue, pushes "Hello World" to the driver station

        //This is to allow the operator to actually see the Hello World message, without this line, the program would immediately terminate, and therefore the operator would never see the telemetry data in time
        sleep(10000); //blocks the code from executing for 10000ms (10000ms = 10s)

        stop(); //Safely terminates the opMode
    }
}
