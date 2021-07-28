package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCameraRotation;

import CupertinoRobotics.support.wrapper.EasyCV.EasyCV;

@TeleOp(name = "CV")
public class EasyCVTest1 extends LinearOpMode {

    private EasyCV easyCV;
    private final int CAM_WIDTH = 1280;
    private final int CAM_HEIGHT = 720;
    private final String TEST_TAGLINE = "Test Tagline";

    @Override
    public void runOpMode() throws InterruptedException {
        easyCV = new EasyCV(this, "webcam 1");

        waitForStart();

        easyCV.start(CAM_WIDTH, CAM_HEIGHT, OpenCvCameraRotation.UPRIGHT);

        sleep(100);

        singleFrameAverageColorTest();

        while(opModeIsActive()) sleep(100);

    }

    private final void println(String str){
        telemetry.addLine(str);
        telemetry.update();
    }

    private final void println(boolean str){
        telemetry.addLine(String.valueOf(str));
        telemetry.update();
    }

    private final void println(int str){
        telemetry.addLine(String.valueOf(str));
        telemetry.update();
    }

    private final void println(Object str){
        telemetry.addLine(str.toString());
        telemetry.update();
    }

    private final void singleFrameAverageColorTest(){

        println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        sleep(1000);
        easyCV.getAverageColor(TEST_TAGLINE, EasyCV.Configuration.SYNCHRONOUS_SINGLE_FRAME);
        println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }

    private final void streamAverageColorTest(){

        easyCV.getAverageColor(TEST_TAGLINE, EasyCV.Configuration.ASYNCHRONOUS_CONTINUOUS_STREAM);

        int i;
        for(i = 0; opModeIsActive() && i < 40; i++){
            println(easyCV.getData(TEST_TAGLINE));

            sleep(500);
        }

        println(i * 20);

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }

    private final void restrictSizeTest(){

        easyCV.getAverageColor(TEST_TAGLINE, EasyCV.Configuration.ASYNCHRONOUS_CONTINUOUS_STREAM);

        easyCV.restrictImageRange(0, CAM_WIDTH / 10, 0, CAM_HEIGHT / 10);

        int i;
        for(i = 0; opModeIsActive() && i < 40; i++){
            println(easyCV.getData(TEST_TAGLINE));

            sleep(500);
        }

        println(i * 20);

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }
}