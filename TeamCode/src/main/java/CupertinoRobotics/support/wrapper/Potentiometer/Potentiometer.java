package CupertinoRobotics.support.wrapper.Potentiometer;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public final class Potentiometer {
    private final AnalogInput pot;

    public Potentiometer(HardwareMap hw, String deviceName){
        pot = hw.analogInput.get(deviceName);
    }

    public final double getPosition(){ return pot.getVoltage() * 270 / pot.getMaxVoltage(); }

    public final double getVoltage(){ return pot.getVoltage(); }

    public final double getMaxVoltage(){ return pot.getMaxVoltage(); }











    public final void close(){ pot.close(); }

    public final void resetDeviceConfigurationForOpMode() { pot.resetDeviceConfigurationForOpMode(); }

    public final int getVersion() { return pot.getVersion(); }

    public final String getDeviceName() { return pot.getDeviceName(); }

    public final String getConnectionInfo() { return pot.getConnectionInfo(); }

    public final HardwareDevice.Manufacturer getManufacturer() { return pot.getManufacturer(); }
}