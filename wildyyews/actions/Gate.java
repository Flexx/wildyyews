package scripts.wildyyews.actions;

import org.tribot.api.General;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.wildyyews.resources.Tiles;
import scripts.wildyyews.utilities.Methods;
import scripts.wildyyews.utilities.Waiting;

public class Gate {

	public static boolean atGate(){
		return Player.getPosition().distanceTo(Tiles.outGateTile) <= General.random(0,1);
	}

	public static boolean chatOpen(){
		return Interfaces.get(228, 0) != null;
	}

	public static void clickGate(){
		RSObject[] gate = Objects.findNearest(20, "Gate");
		if(Methods.optionValid("Open")){
			Methods.selectOption("Open");
			Waiting.waitCondition(NPCChat.getName() != null, 500, 700);
		}else{
			if(General.random(1,5) == 1){
				Methods.clickObject(gate, false);
				Waiting.waitCondition(NPCChat.getName() != null, 500, 700);
			}else{
				Methods.clickObject(gate, true);	
				Waiting.waitCondition(ChooseOption.isOpen(), 500, 700);
			}
		}
	}

	public static void handleChat(){
		if(NPCChat.getOptions().length == 2){
			if(NPCChat.selectOption("Yes", true)){
				Waiting.waitCondition(Player.getPosition().distanceTo(Tiles.inGateTile) == 0, 2000, 3000);
			}
		}
	}

	public static void openGate(){
		if(atGate()){
			if(chatOpen()){
				handleChat();
			}else{
				clickGate();
			}
		}else{
			if(!Player.isMoving()){
				Walking.blindWalkTo(Tiles.outGateTile);
			}
		}
	}
}
