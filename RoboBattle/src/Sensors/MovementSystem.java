package Sensors;

import Interfaces.MovementInterface;
import SensorWrapperInterface.MovementLejosInterface;
import States.MoveState;

//TODO put a switch direction function
//TODO fiddle with movement system numbers
public class MovementSystem implements MovementInterface{
	private MovementLejosInterface motor1;
	private MovementLejosInterface motor2;
	private static final int STOP_SPEED = 0;
	private static final int ROTATE_SPEED = 70;
	private static final int FORWARD_SPEED = 85;
	private static final int BACKUP_SPEED = 90;
	
	private static final int FORWARD_MODE = 1;
	private static final int BACKWARD_MODE = 2;
	private static final int STOP_MODE = 3;
	private MoveState currentMove;
	public MovementSystem(MovementLejosInterface move1, MovementLejosInterface move2){
		motor1 = move1;
		motor2 = move2;
		currentMove = MoveState.ROTATE_LEFT;
	}
	public void RotateLeft(){
//		System.out.println("Rotate");
		currentMove = MoveState.ROTATE_LEFT;
		motor1.controlMotor(ROTATE_SPEED, FORWARD_MODE);
		motor2.controlMotor(ROTATE_SPEED, BACKWARD_MODE);
	}
	
	public void RotateRight(){
//		System.out.println("Rotate");
		currentMove = MoveState.ROTATE_RIGHT;
		motor1.controlMotor(ROTATE_SPEED, BACKWARD_MODE);
		motor2.controlMotor(ROTATE_SPEED, FORWARD_MODE);
	}
	
	public void Stop(){
//		System.out.println("Stop");
		currentMove = MoveState.ROTATE_LEFT;
		motor1.controlMotor(STOP_SPEED, STOP_MODE);
		motor2.controlMotor(STOP_SPEED, STOP_MODE);
	}
	
	public void MoveForward(){
//		System.out.println("Forward");
		currentMove = MoveState.FORWARD;
		motor1.controlMotor(FORWARD_SPEED, FORWARD_MODE);
		motor2.controlMotor(FORWARD_SPEED, FORWARD_MODE);
	}
	
	public void Backup(){
//		System.out.println("Backup");
		currentMove = MoveState.BACKWARD;
		motor1.controlMotor(BACKUP_SPEED, BACKWARD_MODE);
		motor2.controlMotor(BACKUP_SPEED, BACKWARD_MODE);
	}
	@Override
	public void ReveseDirection() {
		switch(currentMove){
		case ROTATE_RIGHT:
			RotateLeft();
			break;
		case ROTATE_LEFT:
			RotateRight();
			break;
		case FORWARD:
			Backup();
			break;
		case BACKWARD:
			MoveForward();
			break;
		
		}
	}
}
