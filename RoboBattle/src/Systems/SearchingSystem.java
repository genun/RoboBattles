package Systems;

import java.util.ArrayList;
import java.util.List;

import Interfaces.MovementInterface;
import Interfaces.VisionInterface;

//TODO put light sensor in searching system and have it search for light.
//TODO finish Searching System
public class SearchingSystem extends Thread{
	private VisionInterface vision;
	private MovementInterface move;
	private List<SearchingListener> listeners = new ArrayList<SearchingListener>();
	boolean paused = false;
	public SearchingSystem(VisionInterface vision, MovementInterface move) throws NullPointerException{
		this.vision = vision;
		this.move = move;
	}
	
	public void SearchForEnemy(){
		if(vision.DetectRobot()){
			myNotifyDetected();
			this.pause();
		}
	}

	private void pause() {
		paused = true;
	}
	
	public void resumeMyThread(){
		paused = false;
	}

	private void myNotifyDetected() {
		for(SearchingListener listen: listeners){
			listen.NotifyDetected();
		}
	}
	
	public void run(){
		move.Rotate();
		while(true){
			if(paused) Thread.yield();
			else SearchForEnemy();
		}
	}
	
	public interface SearchingListener{
		public void NotifyDetected();
	}
}