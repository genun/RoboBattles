package Systems;

import java.util.ArrayList;

import Sensors.TouchSystem;

public class BackTouchSystem extends Thread{
	private TouchSystem touch;
	private ArrayList<BackTouchListener> listeners = new ArrayList<BackTouchListener>();
	public BackTouchSystem(TouchSystem touch){
		this.touch = touch;
	}
	
	public void run(){
		if(touch.DetectTouch()){
			notifyTouch();
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
