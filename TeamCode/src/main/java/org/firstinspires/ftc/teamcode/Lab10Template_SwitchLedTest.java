package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Switch Led Test")
public class Lab10Template_SwitchLedTest extends LinearOpMode {
    SwitchLedStateMachine stateMachine;

    @Override
    public void runOpMode() throws InterruptedException {

        //YOUR OPMODE CODE HERE

    }

    private class SwitchLedStateMachine { //We will create our state machines as separate classes
        private DigitalChannel ledGreen, ledRed; //The LED Indicator contains 2 LEDs, a green, and red LED, both of these will be mapped onto 2 different ports, so they must be defined seperately
        private TouchSensor touchSensor; //The touch sensor will switch the LED between its states.

        //YOUR CLASS METHODS AND VARIABLES FOR THE STATE MACHINE HERE
        //THIS INCLUDES CONSTRUCTOR AND ANY HELPER METHODS TO MAKE THE STATE MACHINE SCALABLE

        public void run(){ //What to execute every 1 second
            //YOUR STATE MACHINE ITERATION CODE HERE
        }
    }
}
