package CupertinoRobotics.support.Hardware.DigitalChannelLED;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public final class DigitalChannelLED {
    private final DigitalChannel rLED, gLED;
    private LEDState state;

    public DigitalChannelLED(HardwareMap hw, String redDeviceName, String greenDeviceName){
        rLED = hw.digitalChannel.get(redDeviceName);
        gLED = hw.digitalChannel.get(greenDeviceName);
        rLED.setMode(DigitalChannel.Mode.OUTPUT);
        gLED.setMode(DigitalChannel.Mode.OUTPUT);
        setState(LEDState.NONE);
    }

    public final void setState(LEDState state){
        rLED.setState(state == LEDState.AMBER || state == LEDState.RED);
        gLED.setState(state == LEDState.AMBER || state == LEDState.GREEN);
        this.state = state;
    }

    public final LEDState getState(){
        return state;
    }

    public final void close(){ rLED.close(); gLED.close(); }

    public final void resetDeviceConfigurationForOpMode() { rLED.resetDeviceConfigurationForOpMode();  gLED.resetDeviceConfigurationForOpMode(); }

    public final int getVersionRed() { return rLED.getVersion(); }

    public final String getRedDeviceName() { return rLED.getDeviceName(); }

    public final String getConnectionInfoRed() { return rLED.getConnectionInfo(); }

    public final HardwareDevice.Manufacturer getManufacturerRed() { return rLED.getManufacturer(); }


    public final int getVersionGreen() { return gLED.getVersion(); }

    public final String getGreenDeviceName() { return gLED.getDeviceName(); }

    public final String getConnectionInfoGreen() { return gLED.getConnectionInfo(); }

    public final HardwareDevice.Manufacturer getManufacturerGreen() { return gLED.getManufacturer(); }
}