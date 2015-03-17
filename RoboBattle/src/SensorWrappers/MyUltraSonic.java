package SensorWrappers;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import SensorWrapperInterface.UltraSonicInterface;

//TODO test ultrasonic min distance number
//TODO test ultrasonic max distance number
public class MyUltraSonic implements UltraSonicInterface{
	public UltrasonicSensor sense;
	private static final int MIN_DISTANCE = 0;
	private static final int MAX_DISTANCE = 85;
	public MyUltraSonic(SensorPort port) {
		sense = new UltrasonicSensor(port);
		sense.continuous();
	}
	
	public void ping(){
		sense.ping();
	}
	
	@Override
	public boolean GetDistance() {
		boolean inRange = false;
		this.ping();
		if(sense.getDistance() < MAX_DISTANCE && sense.getDistance() > MIN_DISTANCE){
			inRange = true;
		}
		return inRange;
	}
}
