package CupertinoRobotics.support.EasyCV;

public final class Rectangle {
    public final int xMin, yMin, xMax, yMax;

    public Rectangle(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    @Override
    public final String toString(){
        return "X Min: " + xMin + ", X Max: " + xMax + "Y Min: " + yMin + ", Y Max: " + yMax;
    }
}
