package scripts.wildyyews.actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;

public class Items {

	private static void clickPiles(){
		RSNPC[] piles = NPCs.findNearest("Piles");
		if(piles.length > 0){
			if(piles[0].isOnScreen()){
				if(General.random(1,5) == 1){
					piles[0].click();
					General.sleep(100,200);
						while(Player.isMoving()){
							General.sleep(100,200);
						}
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								return NPCChat.getName() != null;
							}
						}, General.random(1200, 2000));
				}else{
					if(ChooseOption.isOpen()){
						if(ChooseOption.isOptionValid("Talk")){
							if(ChooseOption.select("Talk")){
								General.sleep(100,200);
								while(Player.isMoving()){
									General.sleep(100,200);
								}
								Timing.waitCondition(new Condition() {
									@Override
									public boolean active() {
										return NPCChat.getName() != null;
									}
								}, General.random(1200, 2000));
							}
						}else{
							ChooseOption.close();
						}
					}
					piles[0].hover();
					Mouse.click(3);
				}
			}else{
				if(!Player.isMoving()){
					Walking.walkTo(piles[0].getPosition());
				}
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
