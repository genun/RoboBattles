package Sensors;

import Interfaces.TouchInterface;
import SensorWrapperInterface.TouchSensorInterface;

//TODO test touch system
public class TouchSystem implements TouchInterface{
	private TouchSensorInterface touchSensor1;
	private TouchSensorInterface touchSensor2;
	public TouchSystem(TouchSensorInterface touch1, TouchSensorInterface touch2){
		touchSensor1 = touch1;
		touchSensor2 = touch2;
	}
	
	public boolean DetectFrontTouch() {
		return touchSensor1.isTouched();
	}
	public boolean DetectBackTouch() {
		// TODO Auto-generated method stub
		return touchSensor2.isTouched();
	}
}
