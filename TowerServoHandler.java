
//why not
import java.util.*;
//motors
import com.qualcomm.robotcore.hardware.DcMotor;
//map thingy
import com.qualcomm.robotcore.hardware.HardwareMap;
//game pad
import com.qualcomm.robotcore.hardware.Gamepad;

// driving handler
public class TowerServoHandler {
    DcMotor towerMotor;
    // initialization
    public TowerServoHandler(HardwareMap hardwareMap) {
        towerMotor  = hardwareMap.get(DcMotor.class, "towerMotor");
    }
    
    // gameplay loop
    public void loop(Gamepad gamepad1) {
        boolean bumperLeft = gamepad1.left_bumper;
        boolean bumperRight = gamepad1.right_bumper;
        double dive = 1;
        float leftTrigger = gamepad1.right_trigger;
        if(bumperLeft){
            towerMotor.setPower(dive);
        }
        else if(bumperRight){
            towerMotor.setPower(-dive);
        }
        else{
            towerMotor.setPower(0);
            towerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        if(leftTrigger>=0.5){
            // Initialize motor
            towerMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset position
            towerMotor.setTargetPosition(1000); // Move to position 1000 (encoder ticks)
            towerMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION); // Auto moves to target
            towerMotor.setPower(0.5); // Set speed

            // Wait until it reaches the target
            while (towerMotor.isBusy()) {
                // Keep checking if motor reached the position
            }
            towerMotor.setPower(0); // Stop the motor
        }
    }
}
