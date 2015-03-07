package Drivers;

import Interfaces.MovementInterface;
import States.State;
import Systems.AttackSystem;
import Systems.AttackSystem.AttackSystemListener;
import Systems.BackTouchSystem.BackTouchListener;
import Systems.LightDetectionSystem.LightDetectionListener;
import Systems.SearchingSystem;
import Systems.SearchingSystem.SearchingListener;

public class RoboBattle extends Thread{
	//TODO Manage the states and listseners
	//TODO implement listeners
	
	MovementInterface move;
	private Boolean isBackTouched;
	private State currentState;
	private AttackSystem attack;
	private SearchingSystem search;
	public RoboBattle(MovementInterface move){
		this.move = move;
		isBackTouched = false;
		currentState = State.SEARCHING;
		
		//Move, light
		attack = new AttackSystem(move, null);
		
		//Vision, move, light
		search = new SearchingSystem(null, move, null);
	}
	
	public void run() {
		while(true){
			switch(currentState){
			case START_SEARCH:
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
			case LIGHT_FOUND:
				move.ReveseDirection();
				break;
			case BACK_TOUCHED:
				break;
			}
		}
	}

	public AttackSystemListener attackListen = new AttackSystemListener(){
//		@Override
//		public void NotifyBoundsFound() {
//			currentState = State.START_SEARCH;
//		}
	};
	
	public BackTouchListener backListen = new BackTouchListener(){
		@Override
		public void NotifyBackTouchFound() {
			isBackTouched = true;
		}
		@Override
		public void NotifyBackTouchReleased(){
			isBackTouched = false;
		}
	};
	
	public LightDetectionListener lightListen = new LightDetectionListener(){
		@Override
		public void NotifyLightFound() {
			move.ReveseDirection();
			if()
		}
	};
	
	public SearchingListener searchListen = new SearchingListener(){
		@Override
		public void NotifyDetected() {
			currentState = State.FOUND_ENEMY;
		}
	};
}
