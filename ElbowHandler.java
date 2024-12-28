//FILE: ElbowHandler

package org.firstinspires.ftc.teamcode;
//why not
import java.util.*;
//motors
import com.qualcomm.robotcore.hardware.DcMotor;
//map thingy
import com.qualcomm.robotcore.hardware.HardwareMap;
//game pad
import com.qualcomm.robotcore.hardware.Gamepad;

// driving handler
public class ElbowHandler {
    //class
    // front motors
    //DcMotor is a class
    DcMotor motorJoint;
    int direction = 0;
    // initialization
    public ElbowHandler(HardwareMap hardwareMap) {
        // set front motors
        motorJoint  = hardwareMap.get(DcMotor.class, "motorJoint");
    }

    // gameplay loop
    public void loop(Gamepad gamepad1) {
        boolean buttonX = gamepad1.x;
        boolean buttonY = gamepad1.y;
        // set motor powers
        if(buttonX && !buttonY){
            direction = 1;
        }
        else if(!buttonX && buttonY){
            direction = -1;
        }
        else{
            direction = 0;
        }
        motorLeft.setPower(direction/4);
    }
}
