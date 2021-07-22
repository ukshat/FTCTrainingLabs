package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Servo Test")
public class Lab04Solution_ServoTest extends LinearOpMode {
    private Servo servo; //Define Servo object to store data about and control the physical servo

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.servo.get("testServo"); //Map a configured Servo motor to an object in Java
        servo.setDirection(Servo.Direction.FORWARD); //Ensure the servo object defaults to the same direction every time

        servo.setPosition(0.375); // setPosition only takes a number from 0 to 1, so we have to map the 135° to this range. 135/360 is 0.375.

        waitForStart();

        for(int i = 0; i < 6; i++){ //repeat x6
            servo.setPosition(0); // set to 0°

            sleep(3000); //blocks the code from executing for 3000ms (3000ms = 3s)

            servo.setPosition(0.75); // set to 270° (270/360 = 0.75)

            sleep(5000); //sleep 5s
        }

        stop();
    }
}
