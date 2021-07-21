package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Motor Test")
public class Lab03Solution_MotorTest extends LinearOpMode {
    private DcMotor motor; //Define DcMotor object to store data about and control the physical motor

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("testServo"); //Map a configured DC motor to an object in Java

        waitForStart(); //Self-explanatory

        for(int i = 0; i < 6; i++){ //repeat x6
            motor.setPower(0.5); //send 50% of the full power that can be sent to the motor, in other words, make it travel at 50% of the max power

            sleep(3000); //blocks the code from executing for 3000ms (3000ms = 3s)

            motor.setPower(-0.25); //send 25% of the full power that can be sent to the motor, with the opposite polarity, which will make it turn in the opposite direction

            sleep(2000); //2000ms = 2s
        }

        stop(); //safely terminates opmode
    }
}
