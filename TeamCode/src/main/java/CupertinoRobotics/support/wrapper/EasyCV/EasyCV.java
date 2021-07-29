package CupertinoRobotics.support.wrapper.EasyCV;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.HashMap;

public final class EasyCV{
    //THE FOLLOWING CODE HANDLES BASIC PIPELINE SETUP

    private OpenCvCamera webcam;
    private volatile Pipeline pipeline = new Pipeline();
    private volatile Mat lastMat;
    private volatile LinearOpMode opMode;

    public <O extends LinearOpMode> EasyCV(O opMode, String webCamName){
        queue = new HashMap<>();

        this.opMode = opMode;

        initCam(opMode.hardwareMap, webCamName);
    }

    public void start(int w, int h, OpenCvCameraRotation r){
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

    private class Pipeline extends OpenCvPipeline {

        @Override
        public Mat processFrame(Mat input) {
            lastMat = input;

            try {
                if(streamingComputations)
                    for (Runnable r : streams)
                        r.run();

                Thread.sleep(20);
            } catch (InterruptedException e) {
                println(e.getMessage());
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
                    break;

                case SYNCHRONOUS_SINGLE_FRAME:
                    (new Thread(code)).run();
                    break;

                case ASYNCHRONOUS_CONTINUOUS_STREAM:
                    streams.add(code);
                    break;
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

    public boolean getPercentOfColor(final String tagLine, final Color lowerBound, final Color upperBound, Configuration config, Filters params){
        return imageComputationProcedure(tagLine, new PercentOfColorComputation(tagLine, params, lowerBound, upperBound), config);
    }

    public boolean getAverageColor(final String tagLine, Configuration config, Filters params){
        return imageComputationProcedure(tagLine, new AverageColorComputation(tagLine, params), config);
    }

    private abstract class Computation implements Runnable{
        private final String tagLine;
        private final Filters params;

        public Computation(String tagLine, Filters params){
            this.tagLine = tagLine;
            this.params = params;
        }

        @Override
        public void run(){
            Mat inp = params.imgBound == null ? lastMat.clone() : lastMat.submat(params.imgBound.yMin, params.imgBound.yMax, params.imgBound.xMin, params.imgBound.xMax);
            for(CustomFilter filter : params.filters)
                inp = filter.filter(inp);

            dataLoaded(tagLine, compute(inp));
        }

        protected abstract Object compute(Mat input);
    }

    private class PercentOfColorComputation extends Computation {
        private final Color lowerBound, upperBound;

        public PercentOfColorComputation(String tagLine, Color lowerBound, Color upperBound){
            super(tagLine, new Filters());
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public PercentOfColorComputation(String tagLine, Filters params, Color lowerBound, Color upperBound){
            super(tagLine, params);
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        @Override
        protected Object compute(Mat input) {
            Mat copy = new Mat();

            Imgproc.cvtColor(input, copy, Imgproc.COLOR_RGB2HSV_FULL);
            Core.inRange(copy, new Scalar(lowerBound.getHSV()), new Scalar(upperBound.getHSV()), input);

            return Core.sumElems(input).val[0] / (input.width() * input.height()) / 255;
        }
    }

    private class AverageColorComputation extends Computation{
        public AverageColorComputation(String tagLine) {
            super(tagLine, new Filters());
        }

        public AverageColorComputation(String tagLine, Filters params) {
            super(tagLine, params);
        }

        @Override
        protected Object compute(Mat input) {

            double[] hsv = Core.mean(input).val;

            return Color.fromHSV(hsv[0], hsv[1], hsv[2]);
        }
    }

    private void println(String str){
        opMode.telemetry.addLine(str);
        opMode.telemetry.update();
    }












    //HARD FEATURES


}