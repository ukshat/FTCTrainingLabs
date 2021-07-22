package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.util.HashMap;

@TeleOp(name = "Hello World")
public class EasyCVTest1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    }
}

class EasyCV{
    //THE FOLLOWING CODE HANDLES BASIC PIPELINE SETUP

    private OpenCvCamera webcam;
    private Pipeline pipeline = new Pipeline();
    private volatile Mat lastMat;
    private LinearOpMode opMode;

    public EasyCV(LinearOpMode opMode, String webCamName){
        queue = new HashMap<>();

        this.opMode = opMode;

        initCam(opMode.hardwareMap, webCamName);
    }

    public void start(int w, int h){
        webcam.startStreaming(w, h, OpenCvCameraRotation.UPRIGHT);
    }

    public void pause(){
        webcam.stopStreaming();
    }

    public void closeAsync(){
        webcam.closeCameraDeviceAsync(new OpenCvCamera.AsyncCameraCloseListener() {
            @Override
            public void onClose() {}
        });
    }

    public void close(){
        webcam.closeCameraDevice();
    }

    private void initCam(HardwareMap hw, String webCamName) {
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hw.get(WebcamName.class, webCamName));
        webcam.setPipeline(pipeline);
        webcam.openCameraDevice();
    }

    private class Pipeline extends OpenCvPipeline{

        @Override
        public Mat processFrame(Mat input) {
            lastMat = input;
            return input;
        }

        @Override
        public void onViewportTapped() {}

    }








    //THE FOLLOWING CODE HANDLES THE DATA QUEUE

    private volatile HashMap<String, Object> queue;

    public boolean isDataReady(String tagLine){
        return queue.containsKey(tagLine) && queue.get(tagLine) != null;
    }

    public boolean waitForData(String tagLine){
        while(opMode.opModeIsActive() && !isDataReady(tagLine))
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                return false;
            }

        return true;
    }

    public Object getDataAndFreeQueue(String tagLine){
        if(hasReceivedCommand(tagLine))
            return queue.remove(tagLine);

        return null;
    }

    public Object getData(String tagLine){
        if(hasReceivedCommand(tagLine))
            return queue.get(tagLine);

        return null;
    }

    public boolean hasReceivedCommand(String tagLine){
        return queue.containsKey(tagLine);
    }

    private void recievedCommand(String tagLine){
        queue.put(tagLine, null);
    }

    private void dataLoaded(String tagLine, Object data){
        queue.remove(tagLine);
        queue.put(tagLine, data);
    }








    //THE FOLLOWING CODE HANDLES BASIC IMAGE PROCESSING

    //this is an example of how an input method would work. So if someone wanted to know the percent yellow, this is how the EasyCV would do it
    public boolean getPercentColor(final String tagLine, Color lowerBound, Color upperBound){
        try {
            recievedCommand(tagLine); //this adds the tagline to the hashmap, and the user will know that the command to start calculating the percent yellow is being processed

            new Thread(new Runnable() {
                @Override
                public void run() {//this stuff is async
                    //calculate

                    //calculate
                    //calculate

                    dataLoaded(tagLine, 12.3456789); //after calculating, the data will be loaded into the hashmap, and the user can get the loaded data from the queue handling methods. This also allows the user to only pull data when they need it, and not demand that they store it immediately
                }
            }).start();

            return true;
        }catch(Exception e){
            return false;
        }
    }
}