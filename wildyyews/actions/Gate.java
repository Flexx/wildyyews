package scripts.wildyyews.actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.wildyyews.resources.Tiles;

public class Gate {

	public static boolean atGate(){
		return Player.getPosition().distanceTo(Tiles.outGateTile) <= General.random(0,1);
	}
	
	public static boolean chatOpen(){
		return Interfaces.get(228, 0) != null;
	}

	public static void clickGate(boolean right){
		RSObject[] gate = Objects.findNearest(20, "Gate");
		if(gate.length > 0){
			if(gate[0].isOnScreen()){
				if(ChooseOption.isOpen()){
					if(ChooseOption.isOptionValid("Open")){
						if(ChooseOption.select("Open")){
							Timing.waitCondition(new Condition() {
								@Override
								public boolean active() {
									return NPCChat.getName() != null;
								}
							}, General.random(500, 700));
						}
					}else{
						ChooseOption.close();
					}
				}else{
					if(right == true){
						gate[0].hover();
						Mouse.click(3);
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								return ChooseOption.isOpen();
							}
						}, General.random(500, 700));
					}else{
						gate[0].click("Open");
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								return chatOpen();
							}
						}, General.random(500, 700));
					}
				}
			}else{
				Walking.walkTo(gate[0].getPosition());
			}
		}
	}

	public static void handleChat(){
		if(NPCChat.getOptions().length == 2){
			if(NPCChat.selectOption("Yes", true)){
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						return Player.getPosition().distanceTo(Tiles.inGateTile) == 0;
					}
				}, General.random(2000, 3000));
			}
		}
	}

	public static void openGate(){
		if(atGate()){
			if(chatOpen()){
				handleChat();
			}else{
				if(General.random(1,5) == 1){
					clickGate(false);
				}else{
					clickGate(true);
				}
			}
		}else{
			if(!Player.isMoving()){
				Walking.blindWalkTo(Tiles.outGateTile);
			}
		}
	}
}
