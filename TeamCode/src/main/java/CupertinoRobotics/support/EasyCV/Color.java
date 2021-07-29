package CupertinoRobotics.support.EasyCV;

public final class Color {
    private double[] HSVs;

    private Color(double H, double S, double V) {
        HSVs = new double[] {H, S, V};
    }

    public static Color fromHSV(double H, double S, double V){
        return new Color(H, S, V);
    }

    private static Color fromHSV(double[] HSVVals){
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

    @Override
    public String toString(){
        return "H: " + HSVs[0] + ", S: " + HSVs[1] + ", V: " + HSVs[2];
    }

}