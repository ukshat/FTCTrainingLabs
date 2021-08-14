package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp(name = "Motor Characterization Solution")
public class Lab17Solution_MotorCharacterization extends LinearOpMode {

    private DcMotor m;
    private int powerInc = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        m = hardwareMap.dcMotor.get("testMotor");
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        m.setPower(0);

        boolean buttonAState = false, buttonBState = false, buttonXState = false;

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a && !buttonAState && powerInc < 10)
                powerInc++;

            if(gamepad1.b && !buttonBState && powerInc > 0)
                powerInc--;

            if(gamepad1.x && !buttonXState)
                break;

            buttonAState = gamepad1.a;
            buttonBState = gamepad1.b;

            if(powerInc / 10.0 != m.getPower()){
                telemetry.addData("Power", powerInc / 10.0);
                telemetry.update();
            }

            m.setPower(powerInc / 10.0);

            sleep(50);
        }

        m.setPower(0);

    }

}
