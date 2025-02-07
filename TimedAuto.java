//Even better chatGPT code:
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
    private final int[] steps = {5,1,6,7,2,3,1,3,1};
    private final double[] durations = {5.0, 3.0, 5.0, 2.0, 2.0, 3.0, 3.0, 3.0, 2.0};
    private final double motorPowerLeft = 0.9, motorPowerRight = 1.0, towerPower = 1.0;

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
            case 5: moveTowerToPosition(1600, towerPower); break; // Move tower up
            case 6: moveTowerToPosition(1450, towerPower); break; // Move tower down slightly
            case 7: grabServo.setPosition(0.2); break; // Open claw
            case 8: grabServo.setPosition(0.45); break; // Close claw
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

// //Better chatGPT code:

// package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.robotcore.hardware.Servo;
// import com.qualcomm.robotcore.util.ElapsedTime;

// @Autonomous(name="TimedAuto")
// public class TimedAuto extends LinearOpMode {
//     // Motors and Servos
//     private DcMotor motorJoint, towerMotor, motorLeft, motorRight;
//     private Servo grabServo, intakeServo;

//     // Timer
//     private ElapsedTime elapsedTime = new ElapsedTime();
//     private int step = 0;

//     // Step execution sequence
//     private final int[] steps = {5,1,6,7,2,3,1,3,1};
//     private final float[] duration = {5.0f, 3.0f, 5.0f, 2.0f, 2.0f, 3.0f, 3.0f, 3.0f, 2.0f};
//     private final float[] motorPowers = {0.9f, -1.0f, 1.0f}; // Left, Right, Tower

//     @Override
//     public void runOpMode() {
//         // Hardware mapping
//         motorJoint = hardwareMap.get(DcMotor.class, "motorJoint");
//         towerMotor = hardwareMap.get(DcMotor.class, "towerMotor");
//         motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
//         motorRight = hardwareMap.get(DcMotor.class, "motorRight");
//         grabServo = hardwareMap.get(Servo.class, "grabServo");
//         intakeServo = hardwareMap.get(Servo.class, "intakeServo");

//         // Reset encoder for tower motor
//         towerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//         towerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//         waitForStart(); // Wait for start signal

//         elapsedTime.reset(); // Start timer

//         while (opModeIsActive() && step < duration.length) {
//             if (elapsedTime.seconds() >= duration[step]) {
//                 // Move to the next step
//                 step++;
//                 elapsedTime.reset(); // Reset timer for next step

//                 // Stop all motors when switching steps
//                 motorLeft.setPower(0);
//                 motorRight.setPower(0);
//                 towerMotor.setPower(0);
//             }

//             if (step < steps.length) {
//                 switch (steps[step]) {
//                     case 1: // Move forward
//                         motorLeft.setPower(motorPowers[0]);
//                         motorRight.setPower(motorPowers[1]);
//                         break;
//                     case 2: // Move backward
//                         motorLeft.setPower(-motorPowers[0]);
//                         motorRight.setPower(-motorPowers[1]);
//                         break;
//                     case 3: // Turn right
//                         motorLeft.setPower(motorPowers[0]);
//                         motorRight.setPower(-motorPowers[1]);
//                         break;
//                     case 4: // Turn left
//                         motorLeft.setPower(-motorPowers[0]);
//                         motorRight.setPower(motorPowers[1]);
//                         break;
//                     case 5: // Move tower up
//                         moveTowerToPosition(1600, motorPowers[2]);
//                         break;
//                     case 6: // Move tower down slightly
//                         moveTowerToPosition(1450, motorPowers[2]);
//                         break;
//                     case 7: // Open claw
//                         grabServo.setPosition(0.2);
//                         break;
//                     case 8: // Close claw
//                         grabServo.setPosition(0.45);
//                         break;
//                     case 9: // Lower tower to 0
//                         moveTowerToPosition(0, 1.0f);
//                         break;
//                 }
//             }

//             telemetry.addData("Step", step);
//             telemetry.update();
//         }
//     }

//     // Move tower motor to a set position
//     private void moveTowerToPosition(int position, double power) {
//         towerMotor.setTargetPosition(position);
//         towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//         towerMotor.setPower(power);
//     }
// }

// // package org.firstinspires.ftc.teamcode;

// // import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// // import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// // import com.qualcomm.robotcore.hardware.DcMotor;
// // import com.qualcomm.robotcore.hardware.Servo;

// // @Autonomous(name="TimedAuto")
// // public class TimedAuto extends LinearOpMode {
// //     // Motors and Servos
// //     private DcMotor motorJoint, towerMotor, motorLeft, motorRight;
// //     private Servo grabServo, intakeServo;

// //     // Timed step execution
// //     double timer = 0;
// //     int step = 0;

// //     private final int[] steps = {5,1,6,7,2,3,1,3,1};
// //     private final float[] duration = {5.0f, 3.0f, 5.0f, 2.0f, 2.0f, 3.0f, 3.0f, 3.0f, 2.0f};
// //     private final float[] motorPowers = {0.9f, 1.0f, 1.0f}; // Corrected float values

// //     @Override
// //     public void runOpMode() {
// //         // Hardware mapping
// //         motorJoint = hardwareMap.get(DcMotor.class, "motorJoint");
// //         towerMotor = hardwareMap.get(DcMotor.class, "towerMotor");
// //         motorLeft = hardwareMap.get(DcMotor.class, "motorLeft");
// //         motorRight = hardwareMap.get(DcMotor.class, "motorRight");
// //         grabServo = hardwareMap.get(Servo.class, "grabServo");
// //         intakeServo = hardwareMap.get(Servo.class, "intakeServo");

// //         // Reset encoder for tower motor
// //         towerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// //         towerMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

// //         waitForStart(); // Now recognized because of LinearOpMode
// // timer+=1.60;
   
// //     if (step < duration.length){ // Prevent out-of-bounds access
// //     if (timer>=duration[step]) {
// //         // Reset timer
// //         step++; // Move to the next ste
// //         // Check again to prevent crash
     
// //         // Stop all motors when switching steps
// //         motorLeft.setPower(0);
// //         motorRight.setPower(0);
// //         towerMotor.setPower(0);
// //         ///break;
// //     }

// //     switch (steps[step]) {
// //         case 1: // Move forward
// //             motorLeft.setPower(motorPowers[0]);
// //             motorRight.setPower(motorPowers[1]);
// //            // break;
// //         case 2: // Move backward
// //             motorLeft.setPower(-motorPowers[0]);
// //             motorRight.setPower(-motorPowers[1]);
// //            // break;
// //         case 3: // Turn right
// //             motorLeft.setPower(motorPowers[0]);
// //             motorRight.setPower(-motorPowers[1]);
// //             //break;
// //         case 4: // Turn left
// //             motorLeft.setPower(-motorPowers[0]);
// //             motorRight.setPower(motorPowers[1]);
// //             //break;
// //         case 5: // Move tower up
// //             moveTowerToPosition(1600, motorPowers[2]);
// //            // break;
// //         case 6: // Move tower down slightly
// //             moveTowerToPosition(1450, motorPowers[2]);
// //             //break;
// //         case 7: // Open claw
// //             grabServo.setPosition(0.2);
// //             //break;
// //         case 8: // Close claw
// //             grabServo.setPosition(0.45);
// //             //break;
// //         case 9: // Lower tower to 0
// //             moveTowerToPosition(0, 1.0f);
// //             //break;
// //     }

// // }
// //     }

// //     // Move tower motor to a set position
// //     private void moveTowerToPosition(int position, double power) {
// //         towerMotor.setTargetPosition(position);
// //         towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
// //         towerMotor.setPower(power);
// //     }
// // }
