package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "Switch Led Test Solution")
public class Lab10Solution_SwitchLedTest extends LinearOpMode {
    SwitchLedStateMachine stateMachine;

    @Override
    public void runOpMode() throws InterruptedException {
        stateMachine = new SwitchLedStateMachine("testswitch", "testRedLED", "testGreenLED"); //Initialize state machine

        waitForStart();

        while(opModeIsActive()){ //Loop until termination
            stateMachine.run(); //Execute State Machine
            sleep(1000);
        }
    }

    private class SwitchLedStateMachine{ //We will create our state machines as separate classes
        private DigitalChannel ledGreen, ledRed; //The LED Indicator contains 2 LEDs, a green, and red LED, both of these will be mapped onto 2 different ports, so they must be defined seperately
        private TouchSensor digitalSwitch; //The digital switch being used is a touch sensor
        private boolean state; //Variable to keep track of the state of the touch sensor during the most recent run of the state machine

        public boolean getCurrentState(){ //Returns the absolute current state of the touch sensor (not the last checked state)
            return digitalSwitch.isPressed();
        }

        public boolean getLastCheckedState(){ //Returns the state of the touch sensor during the last run of the state machine (not necessarily the current state of the touch sensor)
            return state;
        }

        public SwitchLedStateMachine(String switchName, String redLedName, String greenLedName){
            digitalSwitch = hardwareMap.touchSensor.get(switchName); //Map the hardware to the object
            state = getCurrentState(); //initialize the state variable to something

            ledGreen = hardwareMap.digitalChannel.get(greenLedName); //Map the hardware to the object
            ledGreen.setMode(DigitalChannel.Mode.OUTPUT); //The LED objects are output devices, which output a current to run the LEDs, so we must define this in the code
            ledRed = hardwareMap.digitalChannel.get(redLedName); //Map the hardware to the object
            ledRed.setMode(DigitalChannel.Mode.OUTPUT);

            ledGreen.setState(true); //set one of the LEDs to be on (the selection of which led to be on was completely arbitrary)
        }

        public void run(){ //What to execute every 1 second
            if(getCurrentState() != getLastCheckedState()){ //if the state of the switch changed...
                state = getCurrentState(); //update state
                ledGreen.setState(!ledGreen.getState()); //switch the state of each LED, effectively toggles between on and off for both, such that one is on and one is off
                ledRed.setState(!ledRed.getState());
            }
        }
    }
}
