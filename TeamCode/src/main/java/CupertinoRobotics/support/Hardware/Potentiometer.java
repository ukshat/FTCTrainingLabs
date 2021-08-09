package CupertinoRobotics.support.Hardware;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public final class Potentiometer {
    private final AnalogInput pot;

    public Potentiometer(HardwareMap hardwareMap, String deviceName){
        pot = hardwareMap.analogInput.get(deviceName);
    }

    public final double getPosition() {
        return pot.getVoltage() * 270 / pot.getMaxVoltage();
    }
}