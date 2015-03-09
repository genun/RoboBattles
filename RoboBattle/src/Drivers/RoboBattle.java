package Drivers;

import lejos.nxt.SensorPort;
import Interfaces.MovementInterface;
import SensorWrappers.MyLight;
import SensorWrappers.MyTouch;
import SensorWrappers.MyUltraSonic;
import Sensors.LightSystem;
import Sensors.TouchSystem;
import Sensors.VisionSystem;
import States.State;
import Systems.AttackSystem;
import Systems.AttackSystem.AttackSystemListener;
import Systems.BackTouchSystem;
import Systems.BackTouchSystem.BackTouchListener;
import Systems.SearchingSystem;
import Systems.SearchingSystem.SearchingListener;

public class RoboBattle extends Thread{
	
	MovementInterface move;
	private State currentState;
	private AttackSystem attack;
	private SearchingSystem search;
	private BackTouchSystem back;
	private LightSystem light;
	private VisionSystem vision;
	
	public RoboBattle(MovementInterface move){
		this.move = move;
		light = new LightSystem(new MyLight(SensorPort.S1));
		vision = new VisionSystem(new MyUltraSonic(SensorPort.S2));
		
		//Move, light
		attack = new AttackSystem(move, light);
		attack.pause();
		attack.start();
		
		//Vision, move, light
		search = new SearchingSystem(vision, move, light);
		search.start();
		
		back = new BackTouchSystem(new TouchSystem(new MyTouch(SensorPort.S3), new MyTouch(SensorPort.S4)), light);
		back.start();
		
		currentState = State.SEARCHING;
	}
	
	public void run() {
		while(true){
			switch(currentState){
			case START_SEARCH:
				currentState = State.SEARCHING;
				attack.pause();
				search.resumeMyThread();
				break;
			case SEARCHING:
				break;
			case FOUND_ENEMY:
				currentState = State.ATTACKING;
				search.pause();
				attack.resumeMyThread();
				break;
			case ATTACKING:
				break;
			}
		}
	}

	public AttackSystemListener attackListen = new AttackSystemListener(){

		@Override
		public void NotifyBoundsFound() {
			currentState = State.START_SEARCH;
		}
	};
	
	public BackTouchListener backListen = new BackTouchListener(){
		@Override
		public void NotifyBackTouchFound() {
			move.Backup();
		}
		
		@Override
		public void NotifyBackTouchReleased(){
			move.Stop();
			currentState = State.START_SEARCH;
		}
	};
	
	public SearchingListener searchListen = new SearchingListener(){
		@Override
		public void NotifyDetected() {
			currentState = State.FOUND_ENEMY;
		}
	};
}
