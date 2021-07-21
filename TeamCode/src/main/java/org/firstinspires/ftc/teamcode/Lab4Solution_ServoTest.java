package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Servo Test")
public class Lab4Solution_ServoTest extends LinearOpMode {
    private Servo servo; //Define Servo object to store data about and control the physical servo

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.servo.get("testServo"); //Map a configured Servo motor to an object in Java
        servo.setDirection(Servo.Direction.FORWARD); //Ensure the servo object defaults to the same direction every time

        servo.setPosition(135); //setPosition

        waitForStart();

        for(int i = 0; i < 6; i++){ //repeat x6
            servo.setPosition(0); //setPosition

            sleep(3000); //blocks the code from executing for 3000ms (3000ms = 3s)

            servo.setPosition(270); //setPosition

            sleep(5000); //sleep 5s
        }
    }
}
