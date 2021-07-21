package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;

@Autonomous(name = "Potentiometer Test")
public class Lab06Solution_PotTest extends LinearOpMode {
    private AnalogInput pot; //Define AnalogInput, which we use to read the voltage from the potentiometer

    @Override
    public void runOpMode() throws InterruptedException {
        pot = hardwareMap.analogInput.get("testPot"); //Map a configured Analog Device (potentiometer) to an object in Java

        waitForStart();

        while(opModeIsActive()){ //Loop as long as the opmode has not ended
            telemetry.addData("Voltage", pot.getVoltage()); //pot.getVoltage() reads the voltage recieved from the potentiometer
            telemetry.update();

            sleep(50);
        }

        stop();
    }
}
