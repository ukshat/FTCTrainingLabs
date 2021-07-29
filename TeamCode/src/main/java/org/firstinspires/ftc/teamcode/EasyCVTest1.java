package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraRotation;

import CupertinoRobotics.support.wrapper.EasyCV.Configuration;
import CupertinoRobotics.support.wrapper.EasyCV.CustomFilter;
import CupertinoRobotics.support.wrapper.EasyCV.EasyCV;
import CupertinoRobotics.support.wrapper.EasyCV.Filters;
import CupertinoRobotics.support.wrapper.EasyCV.Rectangle;

@TeleOp(name = "CV")
public class EasyCVTest1 extends LinearOpMode {

    private EasyCV easyCV;
    private final int CAM_WIDTH = 1280;
    private final int CAM_HEIGHT = 720;
    private final String TEST_TAGLINE = "Test Tagline";

    @Override
    public void runOpMode() throws InterruptedException {
        easyCV = new EasyCV(this, "Webcam 1");

        waitForStart();

        easyCV.start(CAM_WIDTH, CAM_HEIGHT, OpenCvCameraRotation.UPRIGHT);

        sleep(100);

        synchronousTest();

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

    private final void synchronousTest(){

        println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        sleep(1000);
        easyCV.getAverageColor(TEST_TAGLINE, Configuration.SYNCHRONOUS_SINGLE_FRAME);
        println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }

    private final void asyncTest(){

        println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        sleep(1000);
        easyCV.getAverageColor(TEST_TAGLINE, Configuration.ASYNCHRONOUS_SINGLE_FRAME);
        println(easyCV.hasReceivedCommand(TEST_TAGLINE));

        int i;

        for(i = 0; opModeIsActive() && !easyCV.isDataReady(TEST_TAGLINE); i++){
            sleep(20);
        }

        println(i + "    " + easyCV.removeFromQueue(TEST_TAGLINE).toString());
    }

    private final void streamTest(){

        easyCV.getAverageColor(TEST_TAGLINE, Configuration.ASYNCHRONOUS_CONTINUOUS_STREAM);

        sleep(1000);
        int i;
        for(i = 0; opModeIsActive() && i < 40; i++){
            println(easyCV.getData(TEST_TAGLINE));

            sleep(500);
        }

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }

    private final void restrictSizeTest(){

        Filters filters = new Filters();
        filters.setImageBound(new Rectangle(0, 0, CAM_WIDTH / 2, CAM_HEIGHT));

        easyCV.getAverageColor(TEST_TAGLINE, Configuration.ASYNCHRONOUS_CONTINUOUS_STREAM, filters);

        int i;
        for(i = 0; opModeIsActive() && i < 40; i++){
            println(easyCV.getData(TEST_TAGLINE));

            sleep(500);
        }

        println(i * 20);

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }

    private final void customFilterTest(){

        Filters filters = new Filters();
        filters.addCustomFilters(new CustomFilter(){
            @Override
            public Mat filter(Mat input) {
                Mat ret = input.clone();
                Imgproc.cvtColor(input, ret, Imgproc.COLOR_RGB2HSV_FULL);
                Core.inRange(ret, new Scalar(0, 0, 0), new Scalar(255, 255, 255), ret);
                return ret;
            }
        });

        easyCV.getAverageColor(TEST_TAGLINE, Configuration.SYNCHRONOUS_SINGLE_FRAME, filters);

        println(easyCV.getData(TEST_TAGLINE));

        println(easyCV.removeFromQueue(TEST_TAGLINE));
    }
}