package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

// grab servo handler
public class ClawServoHandler {
    // grab servo
    Servo grabServo;
    boolean grabbing;

    // initialization
    public ClawServoHandler(HardwareMap hardwareMap) {
        // set grab servo
        grabServo = hardwareMap.get(Servo.class, "grabServo");

        // set initial state
        grabbing    = false;
        
    }
    public void loop(Gamepad gamepad1) {
        float leftTrigger = gamepad1.left_trigger;
        float rightTrigger = gamepad1.right_trigger;

        if(rightTrigger>=0.5){
            grabbing = false;
        }
        if(leftTrigger>=0.5){
            grabbing = true;
        }
        
        // set servo position
        if(grabbing){
            grabServo.setPosition(0.65);
        }else{
            grabServo.setPosition(0.8);
        }
    }
}
