package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Mecanum Left Right Test Solution")
public class Lab22Solution_MecanumLeftRight extends LinearOpMode {

    //We describe the motors in terms of their position on the chassis from a birds-eye view, with the front of the robot corresponding to the front 2 wheels
    DcMotor tl_motor; //Top Left Motor
    DcMotor tr_motor; //Top Right Motor
    DcMotor bl_motor; //Bottom Left Motor
    DcMotor br_motor; //Bottom Right Motor

    @Override
    public void runOpMode() throws InterruptedException {

        tl_motor = hardwareMap.dcMotor.get("topleft");
        tr_motor = hardwareMap.dcMotor.get("topright");
        bl_motor = hardwareMap.dcMotor.get("bottomleft");
        br_motor = hardwareMap.dcMotor.get("bottomright");

        waitForStart();

        tl_motor.setPower(-0.5);
        tr_motor.setPower(0.5);
        bl_motor.setPower(0.5);
        br_motor.setPower(-0.5);

        sleep(2000);

        tl_motor.setPower(0.5);
        tr_motor.setPower(-0.5);
        bl_motor.setPower(-0.5);
        br_motor.setPower(0.5);

        sleep(2000);

    }
}
