package Drivers;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import SensorWrapperInterface.MovementLejosInterface;
import SensorWrappers.MyMovement;
import Sensors.MovementSystem;

public class driver {
	public static void main(String args[]){
		MovementLejosInterface m1 = new MyMovement(MotorPort.A);
		MovementLejosInterface m2 = new MyMovement(MotorPort.A);
		RoboBattle battle = new RoboBattle(new MovementSystem(m1, m2));
		battle.run();
	}
}
