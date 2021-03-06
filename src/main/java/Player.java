import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
//import java.util.ArrayList;

public class Player extends Character{
    int numberOfNPCs;

    public Player(String name, String description){
	super (name, description);
    }

    public void setNPCNumber(int i){
	numberOfNPCs = i;
    }

    @Override
    public String toString(){
	return this.getName() + " (you). " + this.getDescription();
    }



    public void playTheGame(HashMap<String,Room> roomMap){
	Scanner kb = new Scanner(System.in);
	String cmd = null;


	System.out.println("Welcome to the Game! The goal is to haunt this house. Possible actions are 'move', 'haunt', and 'look'. If you get stuck type 'help' or enter 'quit' to exit.");
	Main.initTimer(600);
	System.out.println("You have " + Main.getTime() + " seconds to beat this game!" );
	

 
	
	
	System.out.println("What would you like to do: ");
	cmd = kb.nextLine();
	cmd = cmd.toLowerCase();
	
	while (!cmd.equals("quit")){



	    /////////////////////////////////////////
	    if (cmd.equals("help")){
		System.out.println("List of Possible Actions: 'move' -will ask the player which adjacent room the player wishes to move to. 'haunt' -will ask the player which item in the room they would like to haunt provided there are items to haunt. 'look' -will provide the player with information about the room they are currently in. 'help' -will display this help information again as well as current room information. 'quit' -will terminate the program.");
		System.out.println("Current room information: " + roomMap.get(this.roomCheck()));
		////////////////////////////////////



		
	    } else if (cmd.equals("look")){
		System.out.println("Current room information: " + roomMap.get(this.roomCheck()));


		/////////////////////////////////////

	    } else if (cmd.equals("haunt")){
		if (roomMap.get(this.roomCheck()).hauntPossible()){
		    String itemName = "";
		    String actionCmd = "";
		    System.out.println("Please enter one of the following items to Haunt them: ");
		    Room tempRoom = roomMap.get(this.roomCheck());
		    tempRoom.displayHauntableItems();
		    itemName = kb.nextLine();
		    itemName = itemName.toLowerCase();
		    if (tempRoom.validItemName(itemName)){
			System.out.println("What action would you like to do: ");
			tempRoom.displayItemActions(itemName);
			actionCmd = kb.nextLine();
			actionCmd = actionCmd.toLowerCase();
			if (actionCmd.equals("possess") || actionCmd.equals("shake") || actionCmd.equals("throw")){
			    if(tempRoom.itemHasAction(actionCmd, itemName)){
				tempRoom.getItemByName(itemName).itemHaunting(actionCmd);

				singlyLinkedList<Character> tempArray = tempRoom.getCharacters();
				// need a temporary character here to then remove after the for loop or it will crash. 
				for (Character c : tempArray){
				
				    if (!c.getName().equals(this.getName())){
					c.characterReacts(actionCmd);
					int debugInt = c.getScaredLevel();
					System.out.println("Their scared Level is: " + debugInt);
					if (c.getScaredLevel() >= 100){
					    
					    //System.out.println("Time before was: " + Main.getTime());
					    Main.incrementTime();
					    //System.out.println("Time after is: " + Main.getTime());
					    System.out.println("Thirty seconds added to your timer");
					    tempArray.remove(c);

					    // *** I think something is not working here
					    
					    tempRoom.updateCharacterArray(tempArray);
					    roomMap.put(tempRoom.getName(), tempRoom);
					    numberOfNPCs--;
					    System.out.println(numberOfNPCs + " remain in this house");
					    if (numberOfNPCs == 0){
						System.out.println("Everyone has left the house! You win!");
						System.exit(0);
					    }
					} else if (c.getScaredLevel() >= 50 && !c.checkRoomChange()){
					    c.changeRoomChangeCheck();
					    Random rand = new Random();
					    int tempInt = rand.nextInt(tempRoom.getBorderingRooms());
					    ArrayList<Room> tempArrayList = tempRoom.getBorderingRoomsArrayList();
					    Room tempThisIsBadDesignRoom = tempArrayList.get(tempInt);
					    String runToRoomName = tempThisIsBadDesignRoom.getName();
					    System.out.println("They run to the " + runToRoomName + "!");
					    Room newRoom = roomMap.get(runToRoomName);
					    c.changeRoom(newRoom);
					   
					
					    tempArray.remove(c);

					    // *** and here
					    
					    tempRoom.updateCharacterArray(tempArray);
					    roomMap.put(tempRoom.getName(), tempRoom);


					    
					    newRoom.addCharacter(c);
					
					    newRoom.cleanRoom(c.getName());
					
					    roomMap.put(newRoom.getName(), newRoom);
					
					}
				    }
				}
				/*
				tempRoom.updateCharacterArray(tempArray);
				roomMap.put(tempRoom.getName(), tempRoom);
				*/
			    } else {
				System.out.println("That item does not support that action");
				}
			} else {
			    System.out.println("That is not a valid item action");
			}
			
		    } else {
			System.out.println("That is not a valid item name.");
		    }
		    
		} else {
		    System.out.println("There are no items to haunt in this room");
		}
		



		/////////////////////////////////////
	    } else if (cmd.equals("move")){
	       
		String direction = null;
		System.out.println("Which directions you can go: " + roomMap.get(this.roomCheck()).possibleMovement());
		System.out.println("Please enter a direction: ");
		direction = kb.nextLine();
		direction = direction.toLowerCase();
		if (roomMap.get(this.roomCheck()).validMove(direction)){
		    Room tempCurrent = roomMap.get(this.roomCheck());
		    Room tempNew = null;
		    switch (direction)
			{
			case "north":
			    tempNew = tempCurrent.getNorthRoom();
			    tempNew.addCharacter(this);
			    tempCurrent.removeCharacter(this);
			    roomMap.put(tempCurrent.getName(),tempCurrent);
			    roomMap.put(tempNew.getName(),tempNew);
			    this.changeRoom(tempNew);
			    //tempCurrent = tempNew;

			    System.out.println("You move to the: " + tempNew);
			    break;
			case "south":
			    tempNew = tempCurrent.getSouthRoom();
			    tempNew.addCharacter(this);
			    tempCurrent.removeCharacter(this);
			    roomMap.put(tempCurrent.getName(),tempCurrent);
			    roomMap.put(tempNew.getName(),tempNew);
			    this.changeRoom(tempNew);
			    //tempCurrent = tempNew;

			    System.out.println("You move to the: " + tempNew);
			    break;
			case "west":
			    tempNew = tempCurrent.getWestRoom();
			    tempNew.addCharacter(this);
			    tempCurrent.removeCharacter(this);
			    roomMap.put(tempCurrent.getName(),tempCurrent);
			    roomMap.put(tempNew.getName(),tempNew);
			    this.changeRoom(tempNew);
			    //tempCurrent = tempNew;

			    System.out.println("You move to the: " + tempNew);
			    break;
			case "east":
			    tempNew = tempCurrent.getEastRoom();
			    tempNew.addCharacter(this);
			    tempCurrent.removeCharacter(this);
			    roomMap.put(tempCurrent.getName(),tempCurrent);
			    roomMap.put(tempNew.getName(),tempNew);
			    this.changeRoom(tempNew);
			    //tempCurrent = tempNew;

			    System.out.println("You move to the: " + tempNew);
			    break;
			}
		    
		} else {
		    System.out.println("That is not a valid direction");
		}


		////////////////////////////////////////////////
		
	    } else {
		System.out.println("That is not a valid command. For the list of valid commands enter 'help'.");
	    }

	    

	    System.out.println("You have " + Main.getTime() + " seconds left.");
	    System.out.println("what would you like to do: ");
	    cmd = kb.nextLine();
	    cmd = cmd.toLowerCase();
	}
	System.out.println("Goodbye!");
	System.exit(0);
    }

}
    /*
    public void play(Scanner s, ArrayList<Room> roomArrayList){
	
	Character playerReference = this;
	
	for (Room r : roomArrayList){
	    Character[] tempArray = r.getCharacters();
	    for (int i = 0; i < tempArray.length; i++){
		
		if (tempArray[i] != null){
		    tempArray[i].changeRoom(r);
		    if (playerReference.getName().equals(tempArray[i].getName())){
			playerReference = tempArray[i];
		    }
		}
		
	    
		
	    }
	}
	
        
	Scanner kb2 = new Scanner(System.in);
	String cmd;
	System.out.println("Welcome to the game. Lets haunt this house!");
	System.out.println("You are: " + playerReference);
	System.out.println("the Room you are in is the : " + playerReference.roomCheck());
	System.out.println("What would you like to do? Enter 'look', 'move', 'haunt' or 'quit'");
	cmd = kb2.nextLine();
	/*
	while (!cmd.equals("quit")){
	    if (cmd.equals("look")){
		System.out.println("the Room you are in is the : " + playerReference.roomCheck());
        
	    } else if (cmd.equals("move")){
		String toWhatRoom = "";
		
		System.out.println("What room would you like to move? " + playerReference.displayMoveRooms());
		toWhatRoom = kb2.nextLine();
		
		if (playerReference.getCurrentRoom().canMove(toWhatRoom)){
		    playerReference.getCurrentRoom().removeCharacter(playerReference);
		    playerReference.changeRoom(playerReference.getCurrentRoom().moveWhere(toWhatRoom));
		    playerReference.getCurrentRoom().addCharacter(playerReference);
		} else {
		    System.out.println("That is not a valid Room name");
		}
		
	    } else if (cmd.equals("haunt")){
		if (!playerReference.getCurrentRoom().canHauntCheck()){
		    System.out.println("There are no items available for haunting in this room");
		    
		} else {
		String hauntCmd = "";
		System.out.println("Which item would you like to haunt: " + playerReference.getCurrentRoom().hauntableItems());
		hauntCmd = kb2.nextLine();
		
		if (playerReference.getCurrentRoom().okayHauntTarget(hauntCmd)){
		    String typeHauntCmd = "";
		    System.out.println("Would you Like to: ");
		    playerReference.getCurrentRoom().getSpecificItem(hauntCmd).displayActions();
		    typeHauntCmd = kb2.nextline();
		    
		  
		    
		    
		}

	    
	    } else {
		System.out.println("This is not a valid command.");
				   		   
	    }
	    System.out.println("Please enter:  'look', 'move', 'haunt' or 'quit'");
	    cmd = kb2.nextLine();
		    
		    
	}
	
	
	//System.out.println("Please
	*/
	
    //}

    /*
    public void hauntItem()
call some class from item that causes some haunting to happen and calls a NPC rection method maybe
    */
    
//}
