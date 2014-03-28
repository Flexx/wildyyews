package scripts.wildyyews.actions;

import org.tribot.api2007.GameTab;
import org.tribot.api2007.GameTab.TABS;
import org.tribot.api2007.Player;
import org.tribot.api2007.Players;
import org.tribot.api2007.types.RSPlayer;

public class Pker {

	public static boolean pkerIsNear(){
		RSPlayer[] player = Players.getAll();
		for (RSPlayer i : player) {
			return !i.getName().equals(Player.getRSPlayer().getName());
		}
		return false;
	}
	
	public static void logout(){
		if(GameTab.getOpen() != TABS.LOGOUT){
			GameTab.open(TABS.LOGOUT);
		}else{
			logout();
		}
	}
}
