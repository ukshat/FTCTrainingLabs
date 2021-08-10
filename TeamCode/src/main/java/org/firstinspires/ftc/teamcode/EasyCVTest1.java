package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import CupertinoRobotics.support.EasyCV.OpenCvCameraManagment.OpenCvCameraRotation;

import CupertinoRobotics.support.EasyCV.Configuration;
import CupertinoRobotics.support.EasyCV.EasyCV;
import CupertinoRobotics.support.Telemetry.PrintMode;
import CupertinoRobotics.support.Telemetry.SimplePrintStream;

@TeleOp(name = "CV")
public class EasyCVTest1 extends LinearOpMode {

    private EasyCV easyCV;
    private final int CAM_WIDTH = 1280;
    private final int CAM_HEIGHT = 720;
    private final String TEST_TAGLINE = "Test Tagline";
    private final SimplePrintStream telem = new SimplePrintStream(telemetry);

    @Override
    public void runOpMode() throws InterruptedException {
        easyCV = new EasyCV(this, "Webcam 1");

        telem.setPrintMode(PrintMode.REPLACE);

        waitForStart();

        easyCV.start(CAM_WIDTH, CAM_HEIGHT, OpenCvCameraRotation.UPRIGHT);

        sleep(2000);

        contourBlobTest();

        while(opModeIsActive()) sleep(100);

    }

    private final void synchronousTest(){

        telem.println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        sleep(1000);
        easyCV.getAverageColor(TEST_TAGLINE, Configuration.SYNCHRONOUS_SINGLE_FRAME);
        telem.println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        telem.println(easyCV.removeFromQueue(TEST_TAGLINE));
    }

    private final void contourBlobTest(){
        easyCV.getBlobByContours(TEST_TAGLINE, Configuration.ASYNCHRONOUS_CONTINUOUS_STREAM, -300, 3, 3, false);

        while(opModeIsActive()){
            telem.println(easyCV.getData(TEST_TAGLINE));

            sleep(300);
        }
        telem.println(easyCV.removeFromQueue(TEST_TAGLINE));

    }
}