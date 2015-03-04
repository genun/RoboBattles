package Sensors;

import Interfaces.VisionInterface;
import SensorWrapperInterface.UltraSonicInterface;

/*
 * DetectCan:
 * if Ultrasonic sensor detects a can notify that a can was found,
 * otherwise if Ultrasonic sensor does not detect a can notify that a can was not found
 */
public class VisionSystem implements VisionInterface{
	UltraSonicInterface sense;
	public VisionSystem(UltraSonicInterface sonicSense){
		sense = sonicSense;
	}

	public boolean DetectRobot() {
		boolean detect = false;
		sense.ping();
		if(sense.GetDistance()){
			detect = true;
		}
		return detect;
	}

}
