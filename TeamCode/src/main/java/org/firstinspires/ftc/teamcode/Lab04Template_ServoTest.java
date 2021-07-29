package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Servo Test")
public class Lab04Template_ServoTest extends LinearOpMode {
    private Servo servo; //Define Servo object to store data about and control the physical servo

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.servo.get("testServo"); //Map a hardware-configured Servo motor named "testServo" to an object in Java

        //YOUR CODE HERE

    }
}
