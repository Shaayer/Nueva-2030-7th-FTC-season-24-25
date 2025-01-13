//
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
public class DrivingHandler {
    //class
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

        // set motor powers
        motorLeft.setPower(Math.min(leftStickX   - leftStickY, 1));
        motorRight.setPower(Math.min(leftStickX + leftStickY, 1));

        // dpad control(more precise)
        if (gamepad1.dpad_up) {
            motorLeft.setPower(0.25);
            motorRight.setPower(-0.25);
        }
        if (gamepad1.dpad_down) {
            motorLeft.setPower(-0.25);
            motorRight.setPower(0.25);
        }
        if (gamepad1.dpad_left) {
            motorLeft.setPower(-0.25);
            motorRight.setPower(-0.25);
        }
        if (gamepad1.dpad_right) {
            motorLeft.setPower(0.25);
            motorRight.setPower(0.25);
        }
    }
}
