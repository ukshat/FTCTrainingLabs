package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Potentiometer Test Solution")
public class Lab08Solution_DistanceTest extends LinearOpMode {

    DistanceSensor distSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        distSensor = hardwareMap.get(DistanceSensor.class, "Distance"); //This form of hardware mapping is slightly different than previous labs, so it is provided

        waitForStart();

        while(opModeIsActive()){
            telemetry.addData("Distance", distSensor.getDistance(DistanceUnit.MM) + "mm"); //Gets the distance in units of mm and queues it in the telemetry

            sleep(50);
        }

        stop();
    }
}
