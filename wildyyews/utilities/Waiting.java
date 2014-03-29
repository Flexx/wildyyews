package scripts.wildyyews.utilities;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;

public class Waiting {
	
	public static void waitCondition(final boolean a, int min, int max){
		waitMovement();
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				return a;
			}
		}, General.random(min, max));
	}
	
	public static void waitMovement(){
		General.sleep(100,200);
		while(Player.isMoving()){
			General.sleep(100,200);
		}
	}

}
