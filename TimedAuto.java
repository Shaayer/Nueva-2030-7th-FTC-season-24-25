
//FILE: Auto (time-based)

package org.firstinspires.ftc.teamcode;
//why not
import java.util.*;
//motors
import com.qualcomm.robotcore.hardware.DcMotor;
//map thingy
import com.qualcomm.robotcore.hardware.HardwareMap;
//game pad
import com.qualcomm.robotcore.hardware.Gamepad;
//servo
import com.qualcomm.robotcore.hardware.Servo;

// driving handler
public class TimedAuto {
    //class
    // front motors
    //DcMotor is a class
    DcMotor motorJoint;
    DcMotor towerMotor;
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor grabServo;
    DcMotor intakeServo;
    float timer = 0;
    int step = 0;
           int[] steps = new int[]{5,1,6,7,2,3,1,3,1};
    float[] duration = new float[]{5,3,5,2,2,3,3,3,2};
    /*
1. forward
2. backward
3. turn towards right
4. turn towards left
5. tower up(to a point)
6. tower down a bit
7. open claw
8. close claw
9. lower down tower(closer to 0)

    */
    // initialization
    @Autonomous
    public TimedAuto(HardwareMap hardwareMap) {
        // set front motors
        motorJoint  = hardwareMap.get(DcMotor.class, "motorJoint");
        towerMotor  = hardwareMap.get(DcMotor.class, "towerMotor");
        motorLeft  = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");
    }

    // gameplay loop
    public void runOpMode() {
        waitForStart();

while (opModeIsActive() && !isStopRequested()) {
       timer+=0.1;
       if(timer>=duration[step]){
            timer = 0;
            step++;
       }
        if(steps[step]==1){
            motorLeft.setPower(0.9);
            motorRight.setPower(1);
        }
         if(steps[step]==2){
            motorLeft.setPower(-0.9);
            motorRight.setPower(-1);
        }
         if(steps[step]==3){
            motorLeft.setPower(0.9);
            motorRight.setPower(-1);
        }
        if(steps[step]==4){
            motorLeft.setPower(-0.9);
            motorRight.setPower(1);
        }
        if(steps[step]==5){
            towerMotor.setTargetPosition(1600); // Move to position 1000 (encoder ticks)
            towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Auto moves to target
            towerMotor.setPower(1); // Set speed

            // Wait until it reaches the target
            while (towerMotor.isBusy()) {
                // Keep checking if motor reached the position
            }
            towerMotor.setPower(0); // Stop the motor
        }
        if(steps[step]==6){
            towerMotor.setTargetPosition(1450); // Move to position 1000 (encoder ticks)
            towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Auto moves to target
            towerMotor.setPower(1); // Set speed

            // Wait until it reaches the target
            while (towerMotor.isBusy()) {
                // Keep checking if motor reached the position
            }
            towerMotor.setPower(0); // Stop the motor
        }
        if(steps[step]==7){
            grabServo.setPosition(0.2);
        }
        if(steps[step]==8){
            grabServo.setPosition(0.45);
        }
        f(steps[step]==9){
            towerMotor.setTargetPosition(0); // Move to position 1000 (encoder ticks)
            towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Auto moves to target
            towerMotor.setPower(1); // Set speed

            // Wait until it reaches the target
            while (towerMotor.isBusy()) {
                // Keep checking if motor reached the position
            }
            towerMotor.setPower(0); // Stop the motor
        }
    }
    }
}
