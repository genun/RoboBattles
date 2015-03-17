package Drivers;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import Interfaces.MovementInterface;
import SensorWrapperInterface.MovementLejosInterface;
import SensorWrappers.MyLight;
import SensorWrappers.MyMovement;
import SensorWrappers.MyTouch;
import SensorWrappers.MyUltraSonic;
import Sensors.LightSystem;
import Sensors.MovementSystem;
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

	public RoboBattle(){
		MovementLejosInterface m1 = new MyMovement(MotorPort.A);
		MovementLejosInterface m2 = new MyMovement(MotorPort.B);

		this.move = new MovementSystem(m1, m2);
		light = new LightSystem(new MyLight(SensorPort.S4));
		vision = new VisionSystem(new MyUltraSonic(SensorPort.S3));

		//Move, light
		attack = new AttackSystem(move, light);
		attack.pause();
		attack.start();
		attack.addListener(attackListen);

		//Vision, move, light
		search = new SearchingSystem(vision, move, light);
		search.start();
		search.addListener(searchListen);

		back = new BackTouchSystem(new TouchSystem(new MyTouch(SensorPort.S1), new MyTouch(SensorPort.S2)), light);
		back.start();
		back.addListener(backListen);

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
				move.MoveForward();
				break;
			case ATTACKING:
				break;
			case BOUNDS_FOUND:
				move.Backup();
				double timeAttackBounds = System.currentTimeMillis();
				while((timeAttackBounds + 650) > System.currentTimeMillis()){
					
				}
				timeAttackBounds = System.currentTimeMillis();
				boolean white = true;
				while((timeAttackBounds + 300) > System.currentTimeMillis()){
					if(light.InBounds()){
						white = false;
					}
					else if (!light.InBounds() && !white){
						move.ReveseDirection();
//						double timeForward = System.currentTimeMillis();
//						while((timeForward + 100) > System.currentTimeMillis()){ }
					}
				}
				currentState = State.START_SEARCH;
				break;
			case BACK_BOUNDS_FOUND:
				System.out.println("Back Bounds");
				move.MoveForward();
				double timeBackBounds = System.currentTimeMillis();
				while((timeBackBounds + 700) < System.currentTimeMillis()){ }
				currentState = State.START_SEARCH;
				break;
			case BACK_TOUCH:
				break;
			}
		}
	}

	public AttackSystemListener attackListen = new AttackSystemListener(){
		@Override
		public void NotifyBoundsFound() {
			currentState = State.BOUNDS_FOUND;
		}
	};

	public BackTouchListener backListen = new BackTouchListener(){
		@Override
		public void NotifyBackTouchFound() {
			move.Backup();
			currentState = State.BACK_TOUCH;
			move.Backup();
		}

		@Override
		public void NotifyBackTouchReleased(){
			move.Stop();
			currentState = State.START_SEARCH;
		}

		@Override
		public void NotifyBoundsFound(){
			currentState = State.BACK_BOUNDS_FOUND;
		}
	};

	public SearchingListener searchListen = new SearchingListener(){
		@Override
		public void NotifyDetected() {
			currentState = State.FOUND_ENEMY;
		}
	};
}
