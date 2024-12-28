//FILE: TowerServoHandler

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
public class TowerMotorHandler {
    DcMotor towerMotor;
    // initialization
    public TowerMotorHandler(HardwareMap hardwareMap) {
        towerMotor  = hardwareMap.get(DcMotor.class, "towerMotor");
    }
    
    // gameplay loop
    public void loop(Gamepad gamepad1) {
        boolean bumperLeft = gamepad1.left_bumper;
        boolean bumperRight = gamepad1.right_bumper;
        if(bumperLeft && !bumperRight){
            towerMotor.setPower(0.25);
        }
        if(!bumperLeft && bumperRight){
            towerMoter.setPower(-0.25);
        }
    }
}
