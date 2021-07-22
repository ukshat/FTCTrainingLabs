package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.opencv.core.Scalar;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.util.HashMap;

@TeleOp(name = "Hello World")
public class EasyCVTest1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    }
}

class Color {
    public double[] val;

    public Color(double v0, double v1, double v2, String colorModel) {
        val = new double[] {v0, v1, v2};
        val = toHSV(colorModel);
    }

    public Color(double[] vals, String colorModel) {
        if (vals.length != 3)
            throw new ArrayIndexOutOfBoundsException("You must provide three values for a Color.");
        val = vals.clone();
        val = toHSV(colorModel);
    }

    private double[] toHSV(String colorModel) {
        if (colorModel.equalsIgnoreCase("HSV")) return val;
        else if (colorModel.equalsIgnoreCase("RGB")) {
            // RGB to HSV algorithm adapted from https://stackoverflow.com/q/2399150
            if (!(val[0] >= 0 && val[0] <= 255 && val[1] >= 0 && val[1] <= 255 && val[2] >= 0 && val[2] <= 255))
                throw new IllegalArgumentException("Make sure R, G, and B are all in [0, 255]");
            if (val[0] == val[1] && val[1] == val[2]) return new double[] {0, 0, val[0] / 255};
            double h, s, r = val[0], g = val[1], b = val[2], max, delta;
            max = Math.max(Math.max(r, g), b);
            delta = max - Math.min(Math.min(r, g), b);
            s = delta / max;
            if (r == max) h = (g - b) / delta;
            else if (g == max) h = 2 + (b - r) / delta;
            else h = 4 + (r - g) / delta;
            h *= 60;
            if (h < 0) h += 360;
            return new double[] {h, s * 255, max};
        } else throw new IllegalArgumentException(colorModel + " is not a valid color model");
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

    private final boolean imageComputationProcedure(String tagLine, Runnable code){
        try {
            recievedCommand(tagLine); //this adds the tagline to the hashmap, and the user will know that the command to start calculating the percent yellow is being processed

            new Thread(code).start();

            return true;
        }catch(Exception e){
            return false;
        }
    }

    //this is an example of how an input method would work. So if someone wanted to know the percent yellow, this is how the EasyCV would do it
    public boolean getPercentColor(final String tagLine, Color lowerBound, Color upperBound){
        return imageComputationProcedure(tagLine, new Runnable() {
            @Override
            public void run() {//this stuff is async
                //calculate

                //calculate
                //calculate

                dataLoaded(tagLine, 12.3456789); //after calculating, the data will be loaded into the hashmap, and the user can get the loaded data from the queue handling methods. This also allows the user to only pull data when they need it, and not demand that they store it immediately
            }
        });
    }

    public boolean averageColor(final String tagLine){
        return imageComputationProcedure(tagLine, new Runnable() {
            @Override
            public void run() {//this stuff is async
                dataLoaded(tagLine, 12.3456789);
            }
        });
    }
}