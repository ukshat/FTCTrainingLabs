package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Motor Test")
public class Lab03Template_MotorTest extends LinearOpMode {
    private DcMotor motor; //Define DcMotor object to store data about and control the physical servo

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("testServo"); //Map a configured DC motor to an object in Java

        //YOUR CODE HERE

    }
}
