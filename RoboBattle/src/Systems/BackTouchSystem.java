package Systems;

import java.util.ArrayList;

import Sensors.LightSystem;
import Sensors.TouchSystem;

public class BackTouchSystem extends Thread{
	private boolean backTouched = false;
	private TouchSystem touch;
	private LightSystem light;
	private ArrayList<BackTouchListener> listeners = new ArrayList<BackTouchListener>();
	public BackTouchSystem(TouchSystem touch, LightSystem light){
		this.touch = touch;
		this.light = light;
	}
	
	public void run(){
		while(true){
			if(touch.DetectTouch() && ! backTouched){
				notifyTouch();
				backTouched = true;
			}
			else if(backTouched){
				notifyBackReleased();
				backTouched = false;
			}
			else if(backTouched && light.InBounds()){
				notifyBackReleased();
				backTouched = false;
			}
		}
	}
	
	private void notifyBackReleased() {
		for(BackTouchListener listen: listeners){
			listen.NotifyBackTouchReleased();
		}
	}

	public void addListener(BackTouchListener listen){
		this.listeners.add(listen);
	}
	
	public void notifyTouch(){
		for(BackTouchListener listen: listeners){
			listen.NotifyBackTouchFound();
		}
	}

	public interface BackTouchListener {
		public void NotifyBackTouchFound();
		public void NotifyBackTouchReleased();
	}
}
