package Sensors;

import Interfaces.VisionInterface;
import SensorWrapperInterface.UltraSonicInterface;

//TODO test ultrasonic system
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
