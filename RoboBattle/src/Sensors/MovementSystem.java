package Sensors;

import Interfaces.MovementInterface;
import SensorWrapperInterface.MovementLejosInterface;

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
	public MovementSystem(MovementLejosInterface move1, MovementLejosInterface move2){
		motor1 = move1;
		motor2 = move2;
	}
	public void Rotate(){
//		System.out.println("Rotate");
		motor1.controlMotor(ROTATE_SPEED, FORWARD_MODE);
		motor2.controlMotor(ROTATE_SPEED, BACKWARD_MODE);
	}
	
	public void Stop(){
//		System.out.println("Stop");
		motor1.controlMotor(STOP_SPEED, STOP_MODE);
		motor2.controlMotor(STOP_SPEED, STOP_MODE);
	}
	
	public void MoveForward(){
//		System.out.println("Forward");
		motor1.controlMotor(FORWARD_SPEED, FORWARD_MODE);
		motor2.controlMotor(FORWARD_SPEED, FORWARD_MODE);
	}
	
	public void Backup(){
//		System.out.println("Backup");
		motor1.controlMotor(BACKUP_SPEED, BACKWARD_MODE);
		motor2.controlMotor(BACKUP_SPEED, BACKWARD_MODE);
	}
}
