package Drivers;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import SensorWrapperInterface.MovementLejosInterface;
import SensorWrappers.MyMovement;
import Sensors.MovementSystem;

public class driver {
	public static void main(String args[]){
		RoboBattle battle = new RoboBattle();
		battle.run();
	}
}
