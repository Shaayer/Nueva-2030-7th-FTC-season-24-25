package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="TimedAuto")
public class TimedAuto extends LinearOpMode {
    // Motors and Servos
    private DcMotor motorJoint, towerMotor, motorLeft, motorRight;
    private Servo grabServo, intakeServo;

    // Timed step execution
    double timer = 0;
    int step = 0;

    int[] steps = {1};
    float[] duration = {10};
    float[] motorPowers = {0.9f, -1.0f, 1.0f}; // Corrected float values

    @Override
    public void runOpMode() {
        // Hardware mapping
        motorJoint = hardwareMap.get(DcMotor.class, "motorJoint");
        towerMotor = hardwareMap.get(DcMotor.class, "towerMotor");
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");

        // Reset encoder for tower motor
        towerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart(); // Now recognized because of LinearOpMode
timer+=0.1;
   
    if (step < duration.length){ // Prevent out-of-bounds access
    if (timer>=10) {
        // Reset timer
        step++; // Move to the next ste
        // Check again to prevent crash
     
        // Stop all motors when switching steps
        motorLeft.setPower(0);
        motorRight.setPower(0);
        towerMotor.setPower(0);
        ///break;
    }

    switch (steps[step]) {
        case 1: // Move forward
            motorLeft.setPower(motorPowers[0]);
            motorRight.setPower(motorPowers[1]);
           // break;
        case 2: // Move backward
            motorLeft.setPower(-motorPowers[0]);
            motorRight.setPower(-motorPowers[1]);
           // break;
        case 3: // Turn right
            motorLeft.setPower(motorPowers[0]);
            motorRight.setPower(-motorPowers[1]);
            //break;
        case 4: // Turn left
            motorLeft.setPower(-motorPowers[0]);
            motorRight.setPower(motorPowers[1]);
            //break;
        case 5: // Move tower up
            moveTowerToPosition(1600, motorPowers[2]);
           // break;
        case 6: // Move tower down slightly
            moveTowerToPosition(1450, motorPowers[2]);
            //break;
        case 7: // Open claw
            grabServo.setPosition(0.2);
            //break;
        case 8: // Close claw
            grabServo.setPosition(0.45);
            //break;
        case 9: // Lower tower to 0
            moveTowerToPosition(0, 1.0f);
            //break;
    }

}
    }

    // Move tower motor to a set position
    private void moveTowerToPosition(int position, double power) {
        towerMotor.setTargetPosition(position);
        towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        towerMotor.setPower(power);
    }
}
