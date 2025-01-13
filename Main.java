package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

//Main Op mode
@TeleOp
public class Main extends OpMode {
    // handlers declared
    DrivingHandler drivingHandler;
    TowerServoHandler towerServoHandler;
    ClawServoHandler clawServoHandler;
    IntakeServoHandler intakeServoHandler;
    ElbowHandler elbowHandler;

    // initialization
    public void init() {
        // setup handlers
        drivingHandler = new DrivingHandler(hardwareMap);
        towerServoHandler = new TowerServoHandler(hardwareMap);
        clawServoHandler = new ClawServoHandler(hardwareMap);
        intakeServoHandler = new IntakeServoHandler(hardwareMap);
        elbowHandler = new ElbowHandler(hardwareMap);

        telemetry.addData("status", "initialized");
        telemetry.update();
    }

    // gameplay loop
    public void loop() {
        // run handlers
        drivingHandler.loop(gamepad1);
        towerServoHandler.loop(gamepad1);
        clawServoHandler.loop(gamepad1);
        intakeServoHandler.loop(gamepad1);
        elbowHandler.loop(gamepad1);

        telemetry.addData("status", "running");
        telemetry.update();
    }
}
