package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "Pot Gyro Test Solution")
public class Lab13Solution_PotGyroTest extends LinearOpMode {

    BNO055IMU imu;
    AnalogInput pot;

    @Override
    public void runOpMode() throws InterruptedException {
        imu = (BNO055IMU) hardwareMap.get("imu1"); //Hardware map IMU

        BNO055IMU.Parameters params = new BNO055IMU.Parameters(); //The IMU can return data in a variety of ways, the parameters object can be used to change any of the defaults to a specified parameter
        params.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Set angle unit to be in degrees
        imu.initialize(params); //Assign parameters

        pot = hardwareMap.analogInput.get("testPot"); //Hardware map pot

        int timeSpentCCW = 0; //This variable will count how long the pot has been near fully CCW

        waitForStart();

        for(int i = 0; opModeIsActive(); i++){ //Loop until termination, while counting the number of iterations

            float zAxisAngle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle; //Get the angle of the robot
            double potAngle = pot.getVoltage() * -270 / 3.3 + 135; //Get the voltage of the pot, and use a basic range mapping formula to map it to an angle corresponding with the IMU in degrees

            if(Math.abs(potAngle - zAxisAngle) < 2) //If the angles almost match
                telemetry.addData("Instruction", "steady");
            else if(potAngle > zAxisAngle) //If pot angle is further CCW
                telemetry.addData("Instruction", "rotate CCW");
            else if(potAngle < zAxisAngle) //If pot angle is further CW
                telemetry.addData("Instruction", "rotate CW");

            telemetry.update();

            if(potAngle >= 133) //If the pot is almost fully CCW, increment the variable counting the time spent CCW
                timeSpentCCW++;
            else
                timeSpentCCW = 0;

            if(timeSpentCCW == 200) //If the pot has been near fully CCW for 50 * 200ms (10000ms = 10s)
                stop();

            sleep(50);
        }
    }
}
