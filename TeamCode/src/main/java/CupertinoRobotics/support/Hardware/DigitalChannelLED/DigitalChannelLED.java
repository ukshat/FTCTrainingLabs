package CupertinoRobotics.support.Hardware.DigitalChannelLED;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public final class DigitalChannelLED {
    private final DigitalChannel rLED, gLED;

    public DigitalChannelLED(HardwareMap hw, String redDeviceName, String greenDeviceName){
        rLED = hw.digitalChannel.get(redDeviceName);
        gLED = hw.digitalChannel.get(greenDeviceName);
        rLED.setMode(DigitalChannel.Mode.OUTPUT);
        gLED.setMode(DigitalChannel.Mode.OUTPUT);
    }

    public enum LEDState{
        RED,
        GREEN,
        AMBER,
        NONE
    }

    public final void setState(LEDState state){
        rLED.setState(state == LEDState.AMBER || state == LEDState.RED);
        gLED.setState(state == LEDState.AMBER || state == LEDState.GREEN);
    }

    public final void closeRed(){ rLED.close(); }

    public final void resetDeviceConfigurationForOpModeRed() { rLED.resetDeviceConfigurationForOpMode(); }

    public final int getVersionRed() { return rLED.getVersion(); }

    public final String getDeviceNameRed() { return rLED.getDeviceName(); }

    public final String getConnectionInfoRed() { return rLED.getConnectionInfo(); }

    public final HardwareDevice.Manufacturer getManufacturerRed() { return rLED.getManufacturer(); }

    public final void closeGreen(){ gLED.close(); }

    public final void resetDeviceConfigurationForOpModeGreen() { gLED.resetDeviceConfigurationForOpMode(); }

    public final int getVersionGreen() { return gLED.getVersion(); }

    public final String getDeviceNameGreen() { return gLED.getDeviceName(); }

    public final String getConnectionInfoGreen() { return gLED.getConnectionInfo(); }

    public final HardwareDevice.Manufacturer getManufacturerGreen() { return gLED.getManufacturer(); }
}