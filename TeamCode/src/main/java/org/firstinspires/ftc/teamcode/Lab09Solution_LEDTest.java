package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@Autonomous(name = "LED Test Solution")
public class Lab09Solution_LEDTest extends LinearOpMode {

    private DigitalChannel redLed, greenLed;

    @Override
    public void runOpMode() throws InterruptedException {
        greenLed = hardwareMap.digitalChannel.get("testGreenLED"); // Map the hardware to the object
        greenLed.setMode(DigitalChannel.Mode.OUTPUT); // The LED objects are output devices, which output a current to run the LEDs, so we must define this in the code
        redLed = hardwareMap.digitalChannel.get("testRedLED"); // Map the hardware to the object
        redLed.setMode(DigitalChannel.Mode.OUTPUT);

        waitForStart();

        for(int i = 0; opModeIsActive(); i++){
            greenLed.setState(i % 2 == 1); // flip green from on and off every second such that it starts as off
            redLed.setState(i % 4 >= 2); // flip red from on and off every 2 seconds such that the first 2 iterations start as off

            sleep(1000);
        }
    }
}
