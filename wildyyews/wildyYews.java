package scripts.wildyyews;

import java.awt.*;
import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Walking;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.MessageListening07;
import org.tribot.script.interfaces.Painting;

import scripts.wildyyews.actions.Gate;
import scripts.wildyyews.actions.Items;
import scripts.wildyyews.actions.Pker;
import scripts.wildyyews.actions.Yew;
import scripts.wildyyews.utilities.State;
import scripts.wildyyews.utilities.WorldHopper;

@ScriptManifest(authors = {"Flexx"}, version = 1.0, category = "Woodcutting", name = "Resource Area Yews", description = "Cuts yews in the Wilderness Resource Area")
public class wildyYews extends Script implements Painting, MessageListening07 {

	private boolean onStart(){
		Mouse.setSpeed(General.random(127, 134));
		Camera.setCameraAngle(100);
		Walking.setWalkingTimeout(General.random(2000,4000));
		return true;
	}

	@Override
	public void run() {
		if(onStart()){
			State.setState(State.SLEEP);
			while(!State.getState().equals(State.TERMINATE)){
				switch(State.getState()) {

				case WORLD_HOP :
					WorldHopper.hop();
					break;
					
				case ESCAPE :
					Pker.logout();
					break;
					
				case NOTING :
					Items.note();
					break;
					
				case OUT_OF_AREA :
					Gate.openGate();
					break;
					
				case CLICK_TREE :
					Yew.cut();
					break;
					
				default : break;
				}

				if(!State.getState().equals(State.TERMINATE)) {
					State.setState(State.findCurrentState());
					General.sleep(150, 250);
				}
			}
		}
	}

	public void onPaint(Graphics g1) {

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

	}

	@Override
	public void tradeRequestReceived(String arg0) {
		// TODO Auto-generated method stub

	}


}