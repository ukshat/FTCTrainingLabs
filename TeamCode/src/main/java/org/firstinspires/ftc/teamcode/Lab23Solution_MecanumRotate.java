package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Mecanum Rotate Test Solution")
public class Lab23Solution_MecanumRotate extends LinearOpMode {

    DcMotor tl_motor;
    DcMotor tr_motor;
    DcMotor bl_motor;
    DcMotor br_motor;

    @Override
    public void runOpMode() throws InterruptedException {

        tl_motor = hardwareMap.dcMotor.get("topleft");
        tr_motor = hardwareMap.dcMotor.get("topright");
        bl_motor = hardwareMap.dcMotor.get("bottomleft");
        br_motor = hardwareMap.dcMotor.get("bottomright");

        waitForStart();

        tl_motor.setPower(0.5);
        tr_motor.setPower(-0.5);
        bl_motor.setPower(0.5);
        br_motor.setPower(-0.5);

        sleep(2000);

        tl_motor.setPower(-0.5);
        tr_motor.setPower(0.5);
        bl_motor.setPower(-0.5);
        br_motor.setPower(0.5);

        sleep(2000);

    }
}
