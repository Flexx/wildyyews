package scripts.wildyyews.actions;

import org.tribot.api.General;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.wildyyews.resources.Data;
import scripts.wildyyews.resources.Tiles;
import scripts.wildyyews.utilities.Methods;
import scripts.wildyyews.utilities.Waiting;

public class Yew {

	public static void clickTree(){
		RSObject[] yew = Objects.findNearest(50, "Yew");
		Methods.selectOption("Chop");
		if(General.random(1,5) == 1){
			Methods.clickObject(yew, false);
			Waiting.waitCondition(Player.getAnimation() == Data.chopAnimation, 1000,2000);
		}else{
			Methods.clickObject(yew, true);
		}
	}

	public static void cutTrees(){
		RSObject[] yew = Objects.findNearest(50, "Yew");
		if(yew.length != 0){
			clickTree();
		}else{
			idle();
		}
	}
	
	public static void idle(){
		if(Player.getPosition().distanceTo(Tiles.waitTile) != 0){
			if(!Player.isMoving()){
				Walking.walkTo(Tiles.waitTile);
			}
		}else{
			if(Camera.getCameraAngle() != 0){
				Camera.setCameraAngle(0);
			}
			if(General.random(1,512) == 1){
				Camera.setCameraRotation(General.random(1,360));
			}
		}
	}
}
