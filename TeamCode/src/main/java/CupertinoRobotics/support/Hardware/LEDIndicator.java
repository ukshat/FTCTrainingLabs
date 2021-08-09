package CupertinoRobotics.support.Hardware;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public final class LEDIndicator {
    private final DigitalChannel rLED, gLED;
    private State state;

    public enum State {
        RED,
        GREEN,
        AMBER,
        NONE
    }

    public LEDIndicator(HardwareMap hw, String redDeviceName, String greenDeviceName){
        rLED = hw.digitalChannel.get(redDeviceName);
        gLED = hw.digitalChannel.get(greenDeviceName);
        rLED.setMode(DigitalChannel.Mode.OUTPUT);
        gLED.setMode(DigitalChannel.Mode.OUTPUT);
        setState(State.NONE);
    }

    public final void setState(State state){
        rLED.setState(state == State.AMBER || state == State.RED);
        gLED.setState(state == State.AMBER || state == State.GREEN);
        this.state = state;
    }

    public final State getState(){
        return state;
    }
}