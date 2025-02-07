//Team Code
package org.firstinspires.ftc.teamcode;
//Import Hardware Map
import com.qualcomm.robotcore.hardware.HardwareMap;
//Import Gampad features
import com.qualcomm.robotcore.hardware.Gamepad;
//Import Servo Features
import com.qualcomm.robotcore.hardware.Servo;

//Claw Servo Class
public class ClawServoHandler {
    //Servo Initialization
    Servo grabServo;
    //If grabbing or not
    boolean grabbing;
    boolean preGrabbing;
    double angle = 0.2;
    //ClawServoHandler Class Constructor for Initialization
    public ClawServoHandler(HardwareMap hardwareMap) {
        //Servo class variable to use servos
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        //Set Initial State of claw
        grabbing    = false;
        preGrabbing    = false;
    }
    
    //Loop for commands
    public void loop(Gamepad gamepad1) {
       boolean buttonY = gamepad1.x;
            if(buttonY){
                if(preGrabbing){
                    grabbing = !grabbing;
                }
                preGrabbing = false;
            }else{
                preGrabbing = true;
            }
            if(grabbing){
                angle=0.42;
            }
            if(!grabbing){
                angle=0.25;
            }
        grabServo.setPosition(angle);
    }
}
