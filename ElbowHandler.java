
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
    double direction = 0;
    // initialization
    public ElbowHandler(HardwareMap hardwareMap) {
        // set front motors
        motorJoint  = hardwareMap.get(DcMotor.class, "motorJoint");
    }

    // gameplay loop
    public void loop(Gamepad gamepad1) {
        direction = 0;
        if(gamepad1.dpad_up){
            direction = -0.8;
        }else if(gamepad1.dpad_down){
            direction = 0.8;
        }else{
            direction = 0;
            motorJoint.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        
        motorJoint.setPower(direction);
    }
    
}
