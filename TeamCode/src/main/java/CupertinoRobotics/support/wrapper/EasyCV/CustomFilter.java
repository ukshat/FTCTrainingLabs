package CupertinoRobotics.support.wrapper.EasyCV;

import org.opencv.core.Mat;

public interface CustomFilter {
    public abstract Mat filter(Mat input);
}
