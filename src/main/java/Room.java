import java.util.*;
public class Room{
    // will contain a reference to Items in the room
    String north;
    String south;
    String east;
    String west;
    //String north, south, east, west;
    Room northRoom, southRoom, eastRoom, westRoom;
    singlyLinkedList<Character> characters = new singlyLinkedList<>();
    String roomName, roomDescription;
    Item[] items = new Item[5];
    ArrayList<Room> borderingRoomArrayList = new ArrayList<>();
    int borderingRooms;

    public boolean itemHasAction(String action, String itemName){
	boolean check = false;
	for (Item i : items){
	    if (i != null){
		if (itemName.equals(i.getName().toLowerCase())){
		    check = i.checkPossibleAction(action);
		    return check;
		}
	    }
	}
	return check;
    }

    public void cleanRoom(String name){
	for (int i = 0; i < items.length; i++){
	    if (items[i] != null){
		if (items[i].checkBroken()){
		    System.out.println(name + " cleaned up the " + items[i].getName() + " in the " + this.getName() + ".");
		    items[i] = null;
		}
	    }
	}
    }

	
    public void setBorderingRoomsArrayList(){
	if (northRoom != null){
	    borderingRoomArrayList.add(northRoom);
	}
	if (southRoom != null){
	    borderingRoomArrayList.add(southRoom);
	}
	if (westRoom != null){
	    borderingRoomArrayList.add(westRoom);
	}
	if (eastRoom != null){
	    borderingRoomArrayList.add(eastRoom);
	}
    }

    public ArrayList<Room> getBorderingRoomsArrayList(){
	return borderingRoomArrayList;
    }
    
    public void setBorderingRooms(){
	if (northRoom != null){
	    borderingRooms += 1;
	}
	if (southRoom != null){
	    borderingRooms += 1;
	}
	if (westRoom != null){
	    borderingRooms += 1;
	}
	if (eastRoom != null){
	    borderingRooms += 1;
	}
	
    }

    public int getBorderingRooms(){
	return borderingRooms;
    }
    
    public void updateCharacterArray(singlyLinkedList<Character> sll){
	characters = sll;
    }

    

    public Item getItemByName(String name){
	for (Item i : items){
	    if (i != null){
		if (name.equals(i.getName().toLowerCase())){
		    return i;
		}
	    }
	}
	return null;
    }

    public void displayItemActions(String name){
	for (Item i : items){
	    if (i != null){
		if (name.equals(i.getName().toLowerCase())){
		    i.displayActions();
		}
	    }
	}
    }
    
    public boolean validItemName(String name){
	boolean check = false;
	for (Item i : items){
	    if (i != null){
		if (!i.checkBroken()){
		    if(name.equals(i.getName().toLowerCase())){
			check = true;
			return check;
		    }
		}
	    }
	}
	return check;
    }

    public boolean hauntPossible(){
	boolean check = false;
	for (Item i : items){
	    if (i != null){
		if (!i.checkBroken()){
		    check = true;
		    return check;
		}
	    }
	}
	return check;
    }
    public void displayHauntableItems(){
	for (Item i : items){
	    if (i != null){
		if (!i.checkBroken()){
		    System.out.println(i);
		}
	    }
	}
    }
    
    public Item getSpecificItem(String name){
	for (Item i : items){
	    if (i != null){
		if (i.getName().equals(name)){
		    return i;
		}
	    }
	}
	return null;
    }
				
   

    public boolean canHauntCheck(){
	boolean canHaunt = false;
	for (Item i : items){
	    if (i != null){
		if (!i.checkBroken()){
		    canHaunt = true;
		    return canHaunt;
		}
	    }
	}
	return canHaunt;
    }
    
    public String hauntableItems(){
	String listOfItems = "";
	for (Item i : items){
	    if (i != null){
		if (!i.checkBroken()){
		    listOfItems += i;
		}
	    }
	}
	return listOfItems;
    }

    public boolean validMove(String move){
	boolean test = false;
	if (move.equals("north")){
	    if (northRoom != null){
		test = true;
	    }
	}
	if (move.equals("south")){
	    if (southRoom != null){
		test = true;
	    }
	}
	if (move.equals("west")){
	    if (westRoom != null){
		test = true;
	    }
	}
	if (move.equals("east")){
	    if (eastRoom != null){
		test = true;
	    }
	}

	return test;
    }

    public String possibleMovement(){
	String roomString = "";
	if (northRoom != null){
	    roomString += "'north' for " + northRoom.getName() + ". ";
	}
        if (southRoom != null){
	    roomString += "'south' for " + southRoom.getName() + ". ";
	   
	}
	if (eastRoom != null){
	    roomString += "'east' for " + eastRoom.getName() + ". ";
	   
	}
	if (westRoom != null){
	    roomString += "'west' for " + westRoom.getName() + ". ";
	    
	}
	return roomString;
    }
    
    
    
    public singlyLinkedList<Character> getCharacters(){
	return characters;
    }

    public void setDirections(String n, String s, String e, String w){
	north = n;
	south = s;
	east = e;
	west = w;
    }


    //This method is more for testing than anything
    public String displayAdjacentRooms(){
	String roomString = "";
	if (northRoom != null){
	    roomString += "The Room to the North is the: " + northRoom.getName() + ". ";
	}
        if (southRoom != null){
	    roomString += "The Room to the South is the: " + southRoom.getName() + ". ";
	   
	}
	if (eastRoom != null){
	    roomString += "The Room to the East is the: " + eastRoom.getName() + ". ";
	   
	}
	if (westRoom != null){
	    roomString += "The Room to the West is the: " + westRoom.getName() + ". ";
	    
	}
	return roomString;
	    
    }



    

    public void setSouth (Room sr){
	
	southRoom = sr;
    }
    public void setNorth (Room nr){
	northRoom = nr;
    }
    public void setEast (Room er){
	eastRoom = er;
    }
    public void setWest (Room wr){
	westRoom = wr;
    }




    
    public Room getNorthRoom(){
	return northRoom;
    }
    public Room getSouthRoom(){
	return southRoom;
    }
    public Room getEastRoom(){
	return eastRoom;
    }
    public Room getWestRoom(){
	return westRoom;
    }



    

    public String getNorth(){
	return north;
    }
    public String getSouth(){
	return south;
    }
    public String getEast(){
	return east;
    }
    public String getWest(){
	return west;
    }




    
    public String getName(){
	return roomName;
    }

    public boolean itemFullCheck(){
	for (int i = 0; i < items.length; i++){
	    if (items[i] == null){
		return false;
	    }

	}
	return true;
    }
    
    public void addItem(Item item){
        if (this.itemFullCheck()){
	    return;
	}
	else {
	    for (int i = 0; i < items.length; i++){
		if (items[i] == null){
		    items[i] = item;
		    return;
		}
	    }
	}
    }
    
    
    public Room (String name, String description){
	roomName = name;
	roomDescription = description;
    }

    /* 
    public void haunt()
    
will probably call beHaunted from item class, as well as check state of items and call reaction from NPC class. Will also probably be called by some method in the player class although that might not be necessary and just redundent.

    */

    /*
    public void setBorderingRooms(will take some rooms probably)

This will be used to set the various north south east west rooms
    */

    public boolean isFull(){
	/*
	for (int i = 0; i < characters.length; i++){
	    if (characters[i] == null){
		return false;
	    }
	}
	return true;
	*/
	return false;
    }

    public void removeCharacter(Character a){
	characters.remove(a);
    }
    
    public void addCharacter(Character a){
	characters.prepend(a);
    }

    public String itemsInRoom(){
	String bigLongString = "";
	for (int i = 0; i < items.length; i++){
	    if (items[i] != null){
		bigLongString = bigLongString + items[i] + " ";
	    }
	}
	return bigLongString;
    }
    public String peopleInRoom(){
	String bigLongStringList = "";
	if (!characters.isEmpty()){
	    for (Character c : characters){
		bigLongStringList += c;
	    }
	}
	
	return bigLongStringList;
    }

    @Override public String toString(){
	return "The " + roomName + ". " + roomDescription  + ". List of guests in the room: " + peopleInRoom()+ " Items in the room: " +  itemsInRoom()+ " " + displayAdjacentRooms();
    }

}




