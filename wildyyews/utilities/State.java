package scripts.wildyyews.utilities;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;
import org.tribot.api2007.Player;

import scripts.wildyyews.actions.Pker;
import scripts.wildyyews.resources.Areas;

public enum State {
	
	OUT_OF_AREA, CLICK_TREE, CUTTING, NOTING, TERMINATE, WORLD_HOP, ESCAPE, SLEEP;

	public static State state;

	public static State getState() {
		return state;
	}

	public static void setState(State s) {
		state = s;
	}

	public static State findCurrentState() {
		if(Areas.lumbridge.contains(Player.getPosition()) || Inventory.getCount(995) < 2000) {
			if(Inventory.getCount(new String[] { "Coins" }) < 2000){
				General.println("Out of coins!");
			}
			
			if(Areas.lumbridge.contains(Player.getPosition())){
				General.println("We died.");
			}
			return TERMINATE;
		}
		
		if(Areas.resources.contains(Player.getPosition()) && Player.getAnimation() == 875 && !Inventory.isFull()) {
			return CUTTING;
		}
		
		if(Pker.pkerIsNear()) {
			return ESCAPE;
		}
		
		if(Login.getLoginState().equals(Login.STATE.LOGINSCREEN)) {
			return WORLD_HOP;
		}
		
		if(Areas.out.contains(Player.getPosition())) {
			return OUT_OF_AREA;
		}
		
		if(Areas.resources.contains(Player.getPosition()) && Inventory.isFull()) {
			return NOTING;
		}
		
		if(Areas.resources.contains(Player.getPosition()) && !Inventory.isFull() || Player.getAnimation() == -1) {
			return CLICK_TREE;
		}
		return state;
	}
}