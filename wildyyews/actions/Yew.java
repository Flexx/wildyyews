package scripts.wildyyews.actions;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSObject;

import scripts.wildyyews.resources.Tiles;

public class Yew {
	
	public static RSObject getTree() {
		RSObject[] yew = Objects.findNearest(50, "Yew");
		return (yew.length > 0) ? yew[0] : null;
	}

	public static void clickTree(){
		RSObject yew = getTree();
		if(yew != null){
			if(ChooseOption.isOpen()){
				if(ChooseOption.isOptionValid("Chop")){
					if(ChooseOption.select("Chop")){
						General.sleep(100,200);
						while(Player.isMoving()){
							General.sleep(100,200);
						}
					}
				}else{
					ChooseOption.close();
				}
			}else{
				if(yew.isOnScreen()){
					if(General.random(1,5) == 1){
						if(yew.click("Chop down")){
							General.sleep(100,200);
							while(Player.isMoving()){
								General.sleep(100,200);
							}
						}
					}else{
						yew.hover();
						Mouse.click(3);
					}
				}else{
					Walking.walkTo(yew.getPosition());
				}
			}
		}
	}

	public static void cut(){
		RSObject[] yew = Objects.findNearest(50, "Yew");
		if(yew.length != 0){
			clickTree();
		}else{
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
}
