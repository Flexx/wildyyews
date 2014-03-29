package scripts.wildyyews;

import java.awt.*;
import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Login;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSArea;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

import scripts.wildyyews.actions.Gate;
import scripts.wildyyews.actions.Items;
import scripts.wildyyews.actions.Pker;
import scripts.wildyyews.actions.Yew;
import scripts.wildyyews.resources.Areas;
import scripts.wildyyews.resources.Data;
import scripts.wildyyews.utilities.WorldHopper;

@ScriptManifest(authors = {"Flexx"}, version = 1.0, category = "Woodcutting", name = "Resource Area Yews", description = "Cuts yews in the Wilderness Resource Area")
public class wildyYews extends Script implements Painting, MessageListening07 {

	private boolean onStart(){
		Mouse.setSpeed(General.random(127, 134));
		Camera.setCameraAngle(100);
		Walking.setWalkingTimeout(General.random(2000,4000));
		return true;
	}

	private boolean atArea(RSArea a){
		return a.contains(Player.getPosition());
	}

	@Override
	public void run() {
		if(onStart()){
			while(true){
				if(Login.getLoginState() == Login.STATE.LOGINSCREEN){
					WorldHopper.hop();
				}
				
				if(atArea(Areas.resources)){
					if(Pker.pkerIsNear()){
						Pker.logout();
					}else{
						if(Inventory.isFull()){
							Items.note();
						}else{
							Yew.cutTrees();
						}
					}
				}

				if(atArea(Areas.out)){
					Gate.openGate();
				}

				if(atArea(Areas.lumbridge)){
					println("We died. Deathwalking is not supported at this time.");
					stopScript();
				}
			}
		}
	}

	public void onPaint(Graphics g1) {
		//Painting.init();
	}


	@Override
	public void clanMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void personalMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playerMessageReceived(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void serverMessageReceived(String arg0) {
		if(arg0.contains("cut")){
			Data.logsCut++;
		}
	}

	@Override
	public void tradeRequestReceived(String arg0) {
		// TODO Auto-generated method stub

	}


}