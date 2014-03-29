package scripts.wildyyews.utilities;

import org.tribot.api.Clicking;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;

public class Methods {

	public static void clickObject(RSObject[] a, boolean right){
		if(a.length > 0){
			if(a[0].isOnScreen()){
				if(right){
					a[0].hover();
					Mouse.click(3);
				}else{
					Clicking.click(a[0]);
				}
			}else{
				Walking.blindWalkTo(a[0].getPosition());
			}
		}
	}

	public static void clickNPC(RSNPC[] a, boolean right){
		if(a.length > 0){
			if(a[0].isOnScreen()){
				if(right){
					a[0].hover();
					Mouse.click(3);
				}else{
					Clicking.click(a[0]);
				}
			}else{
				Walking.blindWalkTo(a[0].getPosition());
			}
		}
	}
	
	public static void selectOption(String option){
		if(Methods.optionValid(option)){
			ChooseOption.select(option);
		}else{
			ChooseOption.close();
		}
	}

	public static boolean optionValid(String option){
		return ChooseOption.isOpen() && ChooseOption.isOptionValid(option);
	}

}
