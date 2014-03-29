package scripts.wildyyews.actions;

import org.tribot.api.General;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;

import scripts.wildyyews.utilities.Methods;
import scripts.wildyyews.utilities.Waiting;

public class Items {

	private static void clickPiles(){
		RSNPC[] piles = NPCs.findNearest("Piles");
		if(Methods.optionValid("Talk")){
			Methods.selectOption("Talk");
			Waiting.waitCondition(NPCChat.getName() != null, 1200, 2000);
		}else{
			if(General.random(1,5) == 1){
				Methods.clickNPC(piles, false);
				Waiting.waitCondition(NPCChat.getName() != null, 1200, 2000);
			}else{
				Methods.clickNPC(piles, true);
				Waiting.waitCondition(ChooseOption.isOpen(), 300, 500);
			}
		}
	}

	private static boolean chatOpen(){
		return NPCChat.getName() != null;
	}

	private static void handleChat(){
		if(chatOpen()){
			if(NPCChat.getClickContinueInterface() != null){
				NPCChat.clickContinue(true);
			}
			
			if(Interfaces.get(228, 0) != null){
				NPCChat.selectOption("Thanks", true);
			}
		}
	}

	public static void note(){
		if(!chatOpen()){
			clickPiles();
		}else{
			handleChat();
		}
	}
}
