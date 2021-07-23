package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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

import java.util.ArrayList;
import java.util.HashMap;

@TeleOp(name = "Hello World")
public class EasyCVTest1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

    }
}



final class EasyCV{
    public static class Color {
        private double[] HSVs;

        private Color(double H, double S, double V) {
            HSVs = new double[] {H, S, V};
        }

        public static Color fromHSV(double H, double S, double V){
            return new Color(H, S, V);
        }

        public static Color fromHSV(double[] HSVVals){
            if(HSVVals.length != 3)
                throw new IllegalArgumentException("Array Must contain exactly 3 values for Hue, Saturation, and Value");
            return new Color(HSVVals[0], HSVVals[1], HSVVals[2]);
        }

        public static Color fromRGB(double R, double G, double B){
            double[] HSV = RGBToHSV(new double[] {R, G, B});
            return new Color(HSV[0], HSV[1], HSV[2]);
        }

        private static double[] RGBToHSV(double[] RGBs) {
            // RGB to HSV algorithm adapted from https://stackoverflow.com/q/2399150
            if (!(RGBs[0] >= 0 && RGBs[0] <= 255 && RGBs[1] >= 0 && RGBs[1] <= 255 && RGBs[2] >= 0 && RGBs[2] <= 255))
                throw new IllegalArgumentException("Make sure R, G, and B are all in [0, 255]");
            if (RGBs[0] == RGBs[1] && RGBs[1] == RGBs[2])
                return new double[] {0, 0, RGBs[0] / 255};
            double h, s, r = RGBs[0], g = RGBs[1], b = RGBs[2], max, delta;
            max = Math.max(Math.max(r, g), b);
            delta = max - Math.min(Math.min(r, g), b);
            s = delta / max;
            if (r == max) h = (g - b) / delta;
            else if (g == max) h = 2 + (b - r) / delta;
            else h = 4 + (r - g) / delta;
            h *= 60;
            if (h < 0) h += 360;
            return new double[] {h, s * 255, max};

        }

        public double[] getHSV(){
            return HSVs;
        }

    }
    //THE FOLLOWING CODE HANDLES BASIC PIPELINE SETUP

    private OpenCvCamera webcam;
    private volatile Pipeline pipeline = new Pipeline();
    private volatile Mat lastMat;
    private LinearOpMode opMode;

    public <O extends LinearOpMode> EasyCV(O opMode, String webCamName){
        queue = new HashMap<>();

        this.opMode = opMode;

        initCam(opMode.hardwareMap, webCamName);
    }

    public void start(int w, int h, OpenCvCameraRotation r){
        xMin = 0;
        yMin = 0;
        xMax = w;
        yMax = h;
        webcam.startStreaming(w, h, r);
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






    private volatile ArrayList<Computation> streams = new ArrayList<>();
    private volatile boolean streamingComputations = true;

    public boolean resumeStreamComputations(){
        if(streamingComputations)
            return false;

        streamingComputations = true;
        return true;
    }

    public boolean pauseStreamComputations(){
        if(!streamingComputations)
            return false;

        streamingComputations = false;
        return true;
    }

    private class Pipeline extends OpenCvPipeline{

        @Override
        public Mat processFrame(Mat input) {
            lastMat = input.submat(yMin, yMax, xMin, xMax);

            try {
                if(streamingComputations)
                    for (Runnable r : streams)
                        r.run();

                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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

//    public boolean waitForData(String tagLine){
//        while(opMode.opModeIsActive() && !isDataReady(tagLine))
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                return false;
//            }
//
//        return true;
//    }

    public Object removeFromQueue(String tagLine){
        removeFromStreams(tagLine);

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

    public void removeFromStreams(String tagLine){
        for(Computation c : streams)
            if(c.tagLine.equals(tagLine)) {
                streams.remove(c);
                break;
            }
    }

    private void dataLoaded(String tagLine, Object data){
        queue.remove(tagLine);
        queue.put(tagLine, data);
    }








    //THE FOLLOWING CODE HANDLES BASIC IMAGE PROCESSING

    private final <C extends Computation> boolean imageComputationProcedure(String tagLine, C code, final Configuration config){
        try {
            recievedCommand(tagLine); //this adds the tagline to the hashmap, and the user will know that the command to start calculating the percent yellow is being processed

            switch (config) {
                case ASYNCHRONOUS_SINGLE_FRAME:
                    (new Thread(code)).start();

                case SYNCHRONOUS_SINGLE_FRAME:
                    (new Thread(code)).run();

                case ASYNCHRONOUS_CONTINUOUS_STREAM:
                    streams.add(code);
            }

            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean getPercentOfColor(final String tagLine, final Color lowerBound, final Color upperBound, Configuration config){
        return imageComputationProcedure(tagLine, new PercentOfColorComputation(tagLine, lowerBound, upperBound), config);
    }

    public boolean getAverageColor(final String tagLine, Configuration config){
        return imageComputationProcedure(tagLine, new AverageColorComputation(tagLine), config);
    }

    private abstract class Computation implements Runnable{
        public final String tagLine;

        public Computation(String tagLine){
            this.tagLine = tagLine;
        }
    }

    private class PercentOfColorComputation extends Computation {
        private final Color lowerBound, upperBound;

        public PercentOfColorComputation(String tagLine, Color lowerBound, Color upperBound){
            super(tagLine);
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        public void run() {
            Mat copy = new Mat();

            Imgproc.cvtColor(lastMat, copy, Imgproc.COLOR_RGB2HSV_FULL);
            Core.inRange(copy, new Scalar(lowerBound.getHSV()), new Scalar(upperBound.getHSV()), lastMat);

            dataLoaded(tagLine, Core.sumElems(lastMat).val[0] / (lastMat.width() * lastMat.height()) / 255);
        }
    }

    private class AverageColorComputation extends Computation{
        public AverageColorComputation(String tagLine) {
            super(tagLine);
        }

        @Override
        public void run() {
            dataLoaded(tagLine, Color.fromHSV(Core.mean(lastMat).val));
        }
    }

    public enum Configuration{
        ASYNCHRONOUS_SINGLE_FRAME,
        SYNCHRONOUS_SINGLE_FRAME,
        ASYNCHRONOUS_CONTINUOUS_STREAM
    }










    //MISCELLANEOUS CAMERA FEATURES
    private int xMin, xMax, yMin, yMax;

    public void restrictImageRange(int xMin, int xMax, int yMin, int yMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }









    //HARD FEATURES


}