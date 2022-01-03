import java.util.*;
public class Child extends NPC{
    public Child (String name, String description){
	super (name, description);
    }

    @Override
    public void characterReacts(String action){
	
	Random rand = new Random();
	double tempInt = 0;
	if (action.equals("shake")){
	    tempInt = rand.nextInt(11) + 5;
	    tempInt = tempInt * 1.5;
	    scaredLevel += tempInt;
	} else if (action.equals("possess")){
	    tempInt = rand.nextInt(16) + 10;
	    tempInt = tempInt * 1.5;
	    scaredLevel += tempInt;
	} else {
	    tempInt = rand.nextInt(21) + 20;
	    tempInt = tempInt * 1.5;
	    scaredLevel += tempInt;
	}
	if (scaredLevel >= 50 && !this.checkRoomChange()){
	    System.out.println(this.getName() + " is overcome with terror! They screech like a scared child! They screech again, and then again!");
	} else if (scaredLevel >= 100){
	    System.out.println(this.getName() + " is like super scared man! Jinkeys guys! Then they run right out of the house!");
	} else {
	    System.out.println(this.getName() + " shudders from the paranormal activity!");
	}
	
    }

 
}
