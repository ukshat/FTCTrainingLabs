package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Mecanum Forward Backward Test")
public class Lab21Template_MecanumForwardBackward extends LinearOpMode {

    //We describe the motors in terms of their position on the chassis from a birds-eye view, with the front of the robot corresponding to the front 2 wheels
    DcMotor tl_motor; //Top Left Motor
    DcMotor tr_motor; //Top Right Motor
    DcMotor bl_motor; //Bottom Left Motor
    DcMotor br_motor; //Bottom Right Motor

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
