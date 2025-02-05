package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import android.util.Size;
import java.util.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class Vision extends LinearOpMode {

    DcMotor motorLeft;
    DcMotor motorRight;
    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft  = hardwareMap.get(DcMotor.class, "motorLeft");
        motorRight = hardwareMap.get(DcMotor.class, "motorRight");

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
            .setDrawAxes(true)
            .setDrawCubeProjection(true)
            .setDrawTagID(true)
            .setDrawTagOutline(true)
            .build();

        // Initialize the Vision Portal
        VisionPortal visionPortal = new VisionPortal.Builder()
            .addProcessor(tagProcessor)
            .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
            .setCameraResolution(new Size(1280, 960))
            .build();

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            if (!tagProcessor.getDetections().isEmpty()) {
                AprilTagDetection tag = tagProcessor.getDetections().get(0);

                // Add telemetry data for pose
                telemetry.addData("x", tag.ftcPose.x);
                telemetry.addData("y", tag.ftcPose.y);
                telemetry.addData("z", tag.ftcPose.z);
                telemetry.addData("pitch", tag.ftcPose.pitch);
                telemetry.addData("roll", tag.ftcPose.roll);
                telemetry.addData("yaw", tag.ftcPose.yaw);
                motorLeft.setPower(tag.ftcPose.yaw / 30);
                motorRight.setPower(tag.ftcPose.yaw / 30);
            } else {
                telemetry.addData("Status", "No AprilTags detected");
                motorLeft.setPower(0);
                motorRight.setPower(0);
            }

            telemetry.update();
        }
        //visionPortal.close();
    }
}
