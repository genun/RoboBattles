package Systems;

import java.util.ArrayList;
import java.util.List;

import Interfaces.LightInterface;
import Interfaces.MovementInterface;
import Interfaces.VisionInterface;

public class SearchingSystem extends Thread{
	private VisionInterface vision;
	private MovementInterface move;
	private List<SearchingListener> listeners = new ArrayList<SearchingListener>();
	boolean paused = false;
	private LightInterface light;
	public SearchingSystem(VisionInterface vision, MovementInterface move, LightInterface light) throws NullPointerException{
		this.vision = vision;
		this.move = move;
		this.light = light;
	}
	
	public void SearchForEnemy(){
		if(light.InBounds()){
			move.ReveseDirection();
		}
		if(vision.DetectRobot()){
			myNotifyDetected();
			this.pause();
		}
	}

	public void pause() {
		paused = true;
	}
	
	public void resumeMyThread(){
		move.RotateLeft();
		paused = false;
	}

	private void myNotifyDetected() {
		for(SearchingListener listen: listeners){
			listen.NotifyDetected();
		}
	}
	
	public void run(){
		move.RotateLeft();
		while(true){
			if(paused) Thread.yield();
			else SearchForEnemy();
		}
	}
	
	public interface SearchingListener{
		public void NotifyDetected();
	}
}