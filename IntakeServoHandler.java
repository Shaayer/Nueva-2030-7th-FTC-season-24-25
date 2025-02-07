//FILE: IntakeServoHandler
//resources
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeServoHandler {
    // grab servo
    Servo intakeServo;
    int grabbing;

    // initialization
    public IntakeServoHandler(HardwareMap hardwareMap) {
        // set grab servo
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");

        // set initial state
        grabbing    = 0;
    }
    public void loop(Gamepad gamepad1) {
        //intake
        boolean buttonA = gamepad1.y;
        //Outake
        boolean buttonB = gamepad1.a;
        //running servo
        if(buttonA && !buttonB){
            grabbing = 1;
        }
        else if(!buttonA && buttonB){
            grabbing = 2;
        }
        else{
            //useless
            grabbing = 0;
        }
        
        if(grabbing == 1){
           intakeServo.setPosition(0);
        }
        if(grabbing == 2){
           intakeServo.setPosition(1);
        }
    }
}
