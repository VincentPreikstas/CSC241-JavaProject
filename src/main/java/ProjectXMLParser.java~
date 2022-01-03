import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.*;
//import java.util.ArrayList;
//import java.util.Arrays;

public class ProjectXMLParser extends DefaultHandler{

    //int itemNumber = 0;
    // Item itemOne, itemTwo, itemThree, itemFour, itemFive;
    ArrayList<Item> theItems = new ArrayList<>();
    ArrayList<Character> theCharacters = new ArrayList<>();
    //Character charOne, charTwo, charThree, charFour, charFive;
    //int charNumber = 0;
    //String no, so, ea, we;
   
    String name, description, itemActions;
    
    int numberOfNPCs = 0;
    ArrayList<Room> theRooms = new ArrayList<>();
    String no;
    String so;
    String ea;
    String we;
    Player thePlayer;
    HashMap<String,Room> roomMap = new HashMap<String,Room>();
    
    @Override
    public void startDocument() throws SAXException{
	//System.out.println("Beginning test");

    }

    @Override
    public void startElement(String namespaceURI,
			     String localName,
			     String qName,
			     Attributes atts) throws SAXException{


	if (qName.equals("room")){
	        no = "";
		so = "";
		ea = "";
		we = "";
	    
	    
		name = atts.getValue("name");
		description = atts.getValue("description");
		if (atts.getIndex("north") != -1){
		    
		    no = atts.getValue("north");
		}
		if (atts.getIndex("south") != -1){
		    
		    so = atts.getValue("south");
		}
		if (atts.getIndex("east") != -1){
		    
		    ea = atts.getValue("east");
		}
		if (atts.getIndex("west") != -1){
		    
		    we = atts.getValue("west");
		}
	        
	}
	
	if (qName.equals("item")){

	    itemActions = atts.getValue("actions");
	    Item tempItem = new Item(atts.getValue("name"), atts.getValue("description"));
	    //Will need to parse the itemActions string and then use some method to assin which actions 
	    String[] tokens = itemActions.split(",");
	    tempItem.setStringActions(tokens);
	    for(int i = 0; i < tokens.length; i++){
		tempItem.addAction(tokens[i]);
		
	    }
	    theItems.add(tempItem);
	}
	
	if(qName.equals("adult")) {
	    Adult tempChar = new Adult(atts.getValue("name"), atts.getValue("description"));
	    theCharacters.add(tempChar);
	    numberOfNPCs ++;
	}
	
	if(qName.equals("child")){
	    Child tempChar = new Child(atts.getValue("name"), atts.getValue("description"));
	    theCharacters.add(tempChar);
	    numberOfNPCs++;
	}
	
	if(qName.equals("player")){
	    Player tempChar = new Player(atts.getValue("name"), atts.getValue("description"));
	    theCharacters.add(tempChar);
	    thePlayer = tempChar;
	}
	    
    }

    @Override
    public void endElement(String namespaceURI,
			   String localName,
			   String qName) throws SAXException {
	if (qName.equals("room")){
	    Room newRoom = new Room(name, description);
	    for (Item i : theItems){
		newRoom.addItem(i);
	    }
	    for(Character i : theCharacters){
		newRoom.addCharacter(i);
	    }
	    newRoom.setDirections(no, so, ea, we);

	    theRooms.add(newRoom);
	    /*
	     String no = "";
	     String so = "";
	     String ea = "";
	     String we = "";
	    */
	    theCharacters.clear();
	    theItems.clear();
		
		
	}

    }

    @Override
    public void endDocument() throws SAXException{
	//System.out.println("Ending test");
	for (Room r : theRooms){
	    for(Room p : theRooms){
		
		if (r.getNorth().equals(p.getName())){
		    r.setNorth(p);
		    
		}
		
		if (r.getSouth().equals(p.getName())){
		    r.setSouth(p);
		}
		if (r.getWest().equals(p.getName())){
		    r.setWest(p);
		}
		if (r.getEast().equals(p.getName())){
		    r.setEast(p);
		}
		    
	    }
	}

	for (Room r : theRooms){
	    r.setBorderingRooms();
	    r.setBorderingRoomsArrayList();
	    roomMap.put(r.getName(),r);
	}
    }

    @Override
    public void characters(char ch[], int start, int length)
	throws SAXException {

    }

    public ArrayList<Room> getRooms(){
	return theRooms;
    }
    public Player getPlayer(){
	return thePlayer;
    }
    public HashMap<String,Room> getMap(){
	return roomMap;
    }
    public int getNumNPCs(){
	return numberOfNPCs;
    }






}
