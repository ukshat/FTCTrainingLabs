package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import CupertinoRobotics.support.Hardware.LEDIndicator;
import CupertinoRobotics.support.Hardware.Potentiometer;
import CupertinoRobotics.support.Telemetry.PrintMode;
import CupertinoRobotics.support.Telemetry.SimplePrintStream;

@TeleOp(name = "telem pot test")
public class TEST_Telemetry_POT_LED extends LinearOpMode {
    SimplePrintStream telem;
    Potentiometer pot;
    LEDIndicator led;

    @Override
    public void runOpMode() throws InterruptedException {
        telem = new SimplePrintStream(telemetry);
        pot = new Potentiometer(hardwareMap, "pot");
        led = new LEDIndicator(hardwareMap, "rLED", "gLED");

        waitForStart();

        for(int i = 0; opModeIsActive(); i++){
            if(i % 40 == 0)
                telem.setPrintMode(PrintMode.SCROLL);
            else if(i % 20 == 0)
                telem.setPrintMode(PrintMode.REPLACE);

            if(pot.getPosition() < 67.5)
                led.setState(LEDIndicator.State.NONE);
            else if(pot.getPosition() < 135)
                led.setState(LEDIndicator.State.RED);
            else if(pot.getPosition() < 202.5)
                led.setState(LEDIndicator.State.GREEN);
            else
                led.setState(LEDIndicator.State.AMBER);

            telem.println(led.getState().name());

            if(i % 50 == 0)
                telem.speak(led.getState().name());

            sleep(100);
        }
    }
}
