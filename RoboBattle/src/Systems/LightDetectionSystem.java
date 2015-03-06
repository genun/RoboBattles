package Systems;

import java.util.ArrayList;
import java.util.List;

import Sensors.LightSystem;

//TODO might remove LightDetection System, currently leaving in place
public class LightDetectionSystem extends Thread{
	private LightSystem light;
	private List<LightDetectionListener> listeners = new ArrayList<LightDetectionListener>();
	
	public LightDetectionSystem(LightSystem light){
		this.light = light;
	}
	
	public void addListener(LightDetectionListener listen){
		this.listeners.add(listen);
	}
	
	public void run(){
		while(true){
			if(light.InBounds()){
				foundLight();
			}
		}
	}
	
	public void foundLight(){
		for(LightDetectionListener listen : listeners){
			listen.NotifyLightFound();
		}
	}
	
	public interface LightDetectionListener{
		public void NotifyLightFound();
	}
}
