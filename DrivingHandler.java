/*
FTC To Do:
1. Get Chasis group to go to I-lab to finish.
2. Install the Control Hub & figure out where it is
3. Test and Debug on the actual mat
Coding To Do:
1. Complete main function (make the thing actually do stuff)
2. Upload code to robot, test
3. Debug
4. ...
Goals:
1. Make bot move from Gamepad
2. Add relatively complex behaviour
3. Customize to driver's liking
*/
package org.firstinspires.ftc.teamcode;
//why not
import java.util.*;
//moters
import com.qualcomm.robotcore.hardware.DcMotor;
//map thingy
import com.qualcomm.robotcore.hardware.HardwareMap;
//game pad
import com.qualcomm.robotcore.hardware.Gamepad;

// driving handler
public class DrivingHandler {
    
    // front motors
    //DcMotor is a class
    DcMotor motorLeft;
    DcMotor motorRight;

    // initialization
    public DrivingHandler(HardwareMap hardwareMap) {
        // set front motors
        motorLeft  = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
    }

    // gameplay loop
    public void loop(Gamepad gamepad1) {
        float leftStickX  = gamepad1.left_stick_x;
        float leftStickY  = gamepad1.left_stick_y;
        float rightStickX = gamepad1.right_stick_x;

        // set motor powers
        motorLeft.setPower(Math.min(leftStickX   - leftStickY + rightStickX, 1) * 0.5);
        motorRight.setPower(Math.min(-leftStickX - leftStickY - rightStickX, 1) * 0.5);

        // dpad control(more precise)
        if (gamepad1.dpad_up) {
            motorLeft.setPower(0.25);
            motorRight.setPower(0.25);
        }
        if (gamepad1.dpad_down) {
            motorLeft.setPower(-0.25);
            motorRight.setPower(-0.25);
        }
        if (gamepad1.dpad_left) {
            motorLeft.setPower(-0.25);
            motorRight.setPower(0.25);
        }
        if (gamepad1.dpad_right) {
            motorLeft.setPower(0.25);
            motorRight.setPower(-0.25);
        }
    }
}
