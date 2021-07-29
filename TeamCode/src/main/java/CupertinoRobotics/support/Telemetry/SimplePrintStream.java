package CupertinoRobotics.support.Telemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public final class SimplePrintStream {
    private volatile String queue = "";
    private final Telemetry telemetry;
    private volatile PrintMode mode;

    public SimplePrintStream(Telemetry telemetry, PrintMode mode) {
        this.telemetry = telemetry;
        this.mode = mode;
    }

    public SimplePrintStream(Telemetry telemetry) {
        this.telemetry = telemetry;
        this.mode = PrintMode.REPLACE;
    }

    public final void speak(String  data){ telemetry.speak(data); }
    public final void speak(Object  data){ telemetry.speak(data.toString()); }
    public final void speak(int     data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(double  data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(float   data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(long    data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(short   data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(byte    data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(char    data){ telemetry.speak(String.valueOf(data)); }
    public final void speak(boolean data){ telemetry.speak(String.valueOf(data)); }

    public final void printData(String caption, int     data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, double  data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, float   data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, long    data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, short   data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, byte    data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, char    data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, boolean data){ println(caption + " : " + String.valueOf(data));}
    public final void printData(String caption, Object  data){ println(caption + " : " + data.toString());}
    public final void printData(String caption, String  data){ println(caption + " : " + data);}
    
    public final void println(int     data){ println(String.valueOf(data)); }
    public final void print  (int     data){ print  (String.valueOf(data)); }

    public final void println(double  data){ println(String.valueOf(data)); }
    public final void print  (double  data){ print  (String.valueOf(data)); }

    public final void println(float   data){ println(String.valueOf(data)); }
    public final void print  (float   data){ print  (String.valueOf(data)); }

    public final void println(short   data){ println(String.valueOf(data)); }
    public final void print  (short   data){ print  (String.valueOf(data)); }

    public final void println(long    data){ println(String.valueOf(data)); }
    public final void print  (long    data){ print  (String.valueOf(data)); }

    public final void println(byte    data){ println(String.valueOf(data)); }
    public final void print  (byte    data){ print  (String.valueOf(data)); }

    public final void println(boolean data){ println(String.valueOf(data)); }
    public final void print  (boolean data){ print  (String.valueOf(data)); }

    public final void println(char    data){ println(String.valueOf(data)); }
    public final void print  (char    data){ print  (String.valueOf(data)); }

    public final void println(Object  data){ println(String.valueOf(data.toString())); }
    public final void print  (Object  data){ print  (String.valueOf(data.toString())); }

    public final void println(String  data){ print(data + "\n"); }
    public final void print  (String  data){
        if(mode == PrintMode.REPLACE)
            queue = data ;
        else
            queue += data;

        updateTelemetry();
    }

    public final void setPrintMode(PrintMode mode){
        this.mode = mode;
    }

    private final void updateTelemetry(){
        telemetry.clearAll();
        telemetry.addLine(queue);
        telemetry.update();
    }
}
