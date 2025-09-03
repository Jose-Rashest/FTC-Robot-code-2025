package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;

@TeleOp(name = " Telop")
public class robot extends OpMode
{

  private  Limelight3A limelight;
  public GamepadEx ps4;
  private powertrain drivetrain;
  private  elevator elevator;
  private  arm_Gripper Arm;


  //rutines


  ElapsedTime timer = new ElapsedTime();



        @Override
        public void init() {
            //subsystem and control
            drivetrain = new powertrain(hardwareMap);
            ps4 = new GamepadEx(gamepad1);
            elevator = new elevator(hardwareMap);
            Arm = new arm_Gripper(hardwareMap);

            limelight = hardwareMap.get(Limelight3A.class, "limelight");
            telemetry.setMsTransmissionInterval(11);
            limelight.pipelineSwitch(0);
            /*
             * Starts polling for data.  If you neglect to call start(), getLatestResult() will return null.
             */
            limelight.start();
            telemetry.addData(">", "Robot Ready.  Press Play.");
            telemetry.update();
        }



        @Override
        public void init_loop() {


        }



        @Override
        public void start() {


        }

        @Override
        public void loop() {

           telemetry.addData("angle", "angle (%.2f) ", Arm.getangleArm());
           telemetry.addData("elevator encode", "elevator (%.2f)", elevator.elevatorPos());
           telemetry.addData("right2", "rightElevator (%.2f)", elevator.elevator_leftMotor());
            ps4.readButtons();
            drivetrain.inputcontrol(ps4);
            drivetrain.arcade();
            elevator.loop();
            Arm.getangleArm();


            //controls
            // climber boton A, right bumper Take piece,
            if(ps4.wasJustPressed(GamepadKeys.Button.A)){
                elevator.climber();

            }
                else if(ps4.wasJustReleased(GamepadKeys.Button.A)){
                elevator.offsetPOs();
            }
            if(ps4.wasJustPressed(GamepadKeys.Button.RIGHT_BUMPER)){
                Arm.TakePiece();
                Arm.OpenGripper();

            }
            else if (ps4.wasJustReleased(GamepadKeys.Button.RIGHT_BUMPER)) {

                Arm.Gripperoffset();
                Arm.armOfseTake();

            }


            if(ps4.wasJustPressed(GamepadKeys.Button.X)){
                Arm.OutPieceBasket();
                elevator.lowBasquet();

            }
            else if( ps4.wasJustReleased(GamepadKeys.Button.X)){
                Arm.OpenGripper();
                Arm.ArmOfsetBasket();
                elevator.offsetPOs();
            }
            if (ps4.wasJustPressed(GamepadKeys.Button.LEFT_BUMPER)){
                Arm.OpenGripper();

            }


            if(ps4.wasJustPressed(GamepadKeys.Button.Y)){

                elevator.higChamber();
                Arm.OutPiece();
                Arm.Gripperoffset();

            }
            else if(ps4.wasJustReleased(GamepadKeys.Button.Y)){
                elevator.Change();
                Arm.Gripperoffset();
            }
            if(ps4.wasJustPressed(GamepadKeys.Button.B)){

                Arm.OpenGripper();
                Arm.ArmOfsetChamber();
                elevator.offsetPOs();
            }


            }
        }



