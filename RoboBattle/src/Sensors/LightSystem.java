package Sensors;

import Interfaces.LightInterface;
import SensorWrapperInterface.LightSensorInterface;


//TODO fiddle with light system numbers
public class LightSystem extends Thread implements LightInterface{
	private static final int WHITE_LINE = 100;
	private LightSensorInterface lightSensor;
	public LightSystem(LightSensorInterface light){
		lightSensor = light;
		
	}
	public boolean InBounds(){
		int value = lightSensor.readValue();
		return value < WHITE_LINE;
	}
}
