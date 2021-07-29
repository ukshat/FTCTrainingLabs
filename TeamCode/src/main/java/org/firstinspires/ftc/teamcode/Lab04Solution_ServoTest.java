package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Servo Test Solution")
public class Lab04Solution_ServoTest extends LinearOpMode {
    private Servo servo; //Define Servo object to store data about and control the physical servo

    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.servo.get("servo"); //Map a configured Servo motor to an object in Java

        // A servo can turn from 0 to 270°. To turn 135°, set the position to 135/270, which is 0.5.
        servo.setPosition(0.5);

        waitForStart();

        for(int i = 0; i < 6; i++){ //repeat x6
            servo.setPosition(0); // set to 0° (0/270 = 0)

            sleep(3000); // blocks the code from executing for 3000ms (3000ms = 3s)

            servo.setPosition(1); // set to 270° (270/270 = 1)

            sleep(5000); // sleep 5s
        }

    }
}
