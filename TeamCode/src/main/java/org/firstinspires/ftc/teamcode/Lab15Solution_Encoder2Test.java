package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Encoder Test 2 Solution")
public class Lab15Solution_Encoder2Test extends LinearOpMode {

    DcMotor motor; //Define DcMotor

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.dcMotor.get("testMotor"); //hardware map

        int start = motor.getCurrentPosition(); //in case for some reason the position does not start at 0, we will keep track of the starting position and print all positions relative to this one

        waitForStart();

        while(opModeIsActive()){
            motor.setPower(0.25); //Set motor to 25% power
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //When the motor is told to stop, allow it's inertia to keep spinning the wheel

            while(opModeIsActive() && motor.getCurrentPosition() - start <= 1000) //wait until the motor passes 1000 ticks beyond the starting position
                sleep(10);

            motor.setPower(0); //stop the motor

            for(int i = 0; opModeIsActive() && i < 100; i++){ //this loops 100 times, with a 100ms delay, so it pauses for a total of 10000ms (10s) while printing the encoder position every 100ms
                telemetry.addData("Encoder Pos", motor.getCurrentPosition()); //print motor position
                telemetry.update();

                sleep(100);
            }

            motor.setPower(-0.5); //Set motor to 25% power in the opposite direction
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //When the motor is told to stop, it will actively apply power to the motor to bring it to a full stop as soon as possible, without letting it freely spin

            while(opModeIsActive() && motor.getCurrentPosition() - start >= -500) //wait until the motor passes 500 ticks in the opposite direction of the starting position
                sleep(10);

            motor.setPower(0); //stop the motor

            for(int i = 0; opModeIsActive() && i < 100; i++){ //this loops 100 times, with a 100ms delay, so it pauses for a total of 10000ms (10s) while printing the encoder position every 100ms
                telemetry.addData("Encoder Pos", motor.getCurrentPosition());
                telemetry.update();

                sleep(100);
            }

            sleep(50);
        }
    }
}
