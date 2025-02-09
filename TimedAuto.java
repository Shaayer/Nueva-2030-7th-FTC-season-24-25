package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="TimedAuto")
public class TimedAuto extends LinearOpMode {
    // Motors and Servos
    private DcMotor motorJoint, towerMotor, motorLeft, motorRight;
    private Servo grabServo, intakeServo;

    // Timer and Step Variables
    private final ElapsedTime elapsedTime = new ElapsedTime();
    private int step = 0;

    // Step Execution Sequence
    private final int[] steps = {8,5,1,8,6,7,2,3,1,3,1};
    private final double[] durations = {2.0,4.0, 1.0, 2.0,4.0, 2.0, 1.0, 1.0, 3.0, 3.0, 2.0};
    private final double motorPowerLeft = 0.60, motorPowerRight = -0.65, towerPower = 1.0;

    @Override
    public void runOpMode() {
        // Hardware mapping
        motorJoint = hardwareMap.get(DcMotor.class, "motorJoint");
        towerMotor = hardwareMap.get(DcMotor.class, "towerMotor");
        motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");
        grabServo = hardwareMap.get(Servo.class, "grabServo");
        intakeServo = hardwareMap.get(Servo.class, "intakeServo");

        // Reset encoders for tower motor
        towerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        towerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart(); // Wait for start signal
        elapsedTime.reset(); // Start timer

        while (opModeIsActive() && step < steps.length) {
            // Check if step time has elapsed
            if (elapsedTime.seconds() >= durations[step]) {
                nextStep();
            }

            executeStep(steps[step]); // Perform current step action

            telemetry.addData("Step", step);
            telemetry.addData("Time", elapsedTime.seconds());
            telemetry.update();
        }
        
        stopAllMotors(); // Ensure motors are stopped at the end
    }

    // Move to the next step
    private void nextStep() {
        step++;
        elapsedTime.reset();
        stopAllMotors();
    }

    // Execute a specific step
    private void executeStep(int action) {
        switch (action) {
            case 1: moveRobot(motorPowerLeft, motorPowerRight); break;  // Move forward
            case 2: moveRobot(-motorPowerLeft, -motorPowerRight); break; // Move backward
            case 3: moveRobot(motorPowerLeft, -motorPowerRight); break; // Turn right
            case 4: moveRobot(-motorPowerLeft, motorPowerRight); break; // Turn left
            case 5: moveTowerToPosition(2500, towerPower); break; // Move tower up
            case 6: moveTowerToPosition(1700, towerPower); break; // Move tower down slightly
            case 7: grabServo.setPosition(0.2); break; // Open claw
            case 8: grabServo.setPosition(0.42); break;
            case 9: moveTowerToPosition(0, 1.0); break; // Lower tower to 0
        }
    }

    // Move the robot
    private void moveRobot(double leftPower, double rightPower) {
        motorLeft.setPower(leftPower);
        motorRight.setPower(rightPower);
    }

    // Move tower motor to a set position
    private void moveTowerToPosition(int position, double power) {
        towerMotor.setTargetPosition(position);
        towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        towerMotor.setPower(power);
    }

    // Stop all motors
    private void stopAllMotors() {
        motorLeft.setPower(0);
        motorRight.setPower(0);
        towerMotor.setPower(0);
    }
}
