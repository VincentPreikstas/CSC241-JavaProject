import java.util.*;
public abstract class Character {
    private String name, description;
    protected String roomName;
    protected int scaredLevel = 0;
    protected boolean changedRoomsOnce = false;


    public void changeRoomChangeCheck(){
	changedRoomsOnce = true;
    }
	
    public boolean checkRoomChange(){
	return changedRoomsOnce;
    }

    public void characterReacts(String action){

	Random rand = new Random();
	double tempInt = 0;
	if (action.equals("shake")){
	    tempInt = rand.nextInt(11) + 5;
	    scaredLevel += tempInt;
	} else if (action.equals("possess")){
	    tempInt = rand.nextInt(16) + 10;
	    scaredLevel += tempInt;
	} else {
	    tempInt = rand.nextInt(21) + 20;
	    scaredLevel += tempInt;
	}
	if (scaredLevel >= 50 && !this.checkRoomChange()){
	    System.out.println(this.getName() + " is overcome with terror! They screech like a demented coyote! They then screech Again, and then one more time!");
	} else if (scaredLevel >= 100){
	    System.out.println(this.getName() + " is having their pants scared off! Zoiks gang! Then they run right out of the house!");
	} else {
	    System.out.println(this.getName() + " shudders from the paranormal activity!");
	}
	
    }

    public int getScaredLevel(){
	return scaredLevel;
    }

    public Character(String name, String description){
	this.name = name;
	this.description = description;
    }

    public String getDescription(){
	return description;
    }

    public String getName(){
	return name;
    }

    public String getCurrentRoom(){
	return roomName;
    }

    public String roomCheck(){
	return roomName;
    }

    public void setRoom(String name){
	roomName = name;

    }

    

    public void changeRoom(Room room) {
	roomName = room.getName();
    }

    
    @Override public String toString(){
	return name + ". " + description + ". Their scared level is: " + scaredLevel;
    }
    
}
