package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Potentiometer Test")
public class Lab08Template_DistanceTest extends LinearOpMode {

    DistanceSensor distSensor;

    @Override
    public void runOpMode() throws InterruptedException {
        distSensor = hardwareMap.get(DistanceSensor.class, "Distance"); //This form of hardware mapping is slightly different than previous labs, so it is provided

        //YOUR CODE HERE

    }
}
