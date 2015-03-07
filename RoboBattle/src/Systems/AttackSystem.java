package Systems;

import java.util.ArrayList;
import java.util.List;

import Interfaces.LightInterface;
import Interfaces.MovementInterface;

//TODO finish AttackSystem
public class AttackSystem extends Thread{
	private boolean paused;
	private MovementInterface move;
	private LightInterface light;
	private List<AttackSystemListener> listeners = new ArrayList<AttackSystemListener>();
	
	public AttackSystem(MovementInterface move, LightInterface light){
		this.move = move;
		this.light = light;
		paused = true;
	}
	
	//TODO might want to double check DemolishEnemy function
	public void DemolishEnemy(){
		move.MoveForward();
		while(light.InBounds()){
		}
		this.NotifyBoundsFound();
	}
	
	private void NotifyBoundsFound(){
		for(AttackSystemListener listen: listeners){
			listen.NotifyBoundsFound();
		}
	}
	

	@Override
	public void run() {
		while (true) {
			if (paused)
				Thread.yield();
			else {
				paused = true;
				DemolishEnemy();
			}
		}
	}

	public void pause() {
		paused = true;
	}

	public void resumeMyThread() {
		paused = false;
	}
	
	public void addListener(AttackSystemListener listen) {
		this.listeners.add(listen);
	}

	public interface AttackSystemListener {
//		public void NotifyBoundsFound();
	}
}
