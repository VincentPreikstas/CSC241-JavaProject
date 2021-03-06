public class Item{
    private String name, description;
    private boolean brokenStatus = false;
    String[] stringActions;
    
    public enum ItemActions {POSSESS, SHAKE, THROW};
    ItemActions[] itemActionsArray = new ItemActions[3];

    public boolean checkPossibleAction(String action){
	boolean check = false;
	for (String s : stringActions){
	    if (s != null){
		if(action.equals(s.toLowerCase())){
		    check = true;
		    return check;
		}
	    }
	}
	return check;
	
    }

    public void setStringActions(String[] actions){
	stringActions = actions;
    }

    public void itemHaunting(String action){
	
	
	ItemActions enumObject = null;
	if (action.equals("possess")){
	    enumObject = ItemActions.POSSESS;
	} else if (action.equals("shake")){
	    enumObject = ItemActions.SHAKE;
	} else {
	    enumObject = ItemActions.THROW;
	}
	switch(enumObject)
	    {
	    case POSSESS:
		System.out.println("You Possess the " + this.name + "! Causing it to vibrate in the air! OOOOHHOOHOHOHO!");
		break;
	    case SHAKE:
		System.out.println("The " + this.name + " shakes violently! Like bad ragdoll physics if this were a real game!");
		break;
	    case THROW:
		System.out.println("You throw the " + this.name + " across the room! Making a massive BANG and breaking the item!");
		brokenStatus = true;
		break;
		}
    }
    
    public boolean checkBroken(){
	return brokenStatus;
    }

    

    public String getName(){
	return name;
    }

    public void displayActions(){
        for (ItemActions i : itemActionsArray){
	    if (i != null){
	    System.out.println(i.name());
	    }
	}
    }

    public void addAction(String s){
	String ss = s;
	ss = ss.toUpperCase();
	ItemActions theAction = ItemActions.POSSESS;
	if (ss.equals("POSSESS")){
	    theAction = ItemActions.POSSESS;
	}
	if (ss.equals("SHAKE")){
	    theAction = ItemActions.SHAKE;
	}
	if (ss.equals("THROW")){
	    theAction = ItemActions.THROW;
	}
	   
	
	for (int i = 0; i < itemActionsArray.length; i++){
	    if (itemActionsArray[i] == null){
		itemActionsArray[i] = theAction;
		return;
	    }
	}
    }

    public String status(){
	if (checkBroken()){
	    return "This is broken.";
	}else{
	    return "This is not broken.";
	}
    }

    public String possibleActions(){
	String theString = "";
	for (int i = 0; i <itemActionsArray.length; i++){
	    if (itemActionsArray[i] != null){
		theString = theString + " " + itemActionsArray[i];
	    }
	}
	return theString;
    }
    
    public Item (String name, String description){
	this.name = name;
	this.description = description;
    }

    @Override public String toString(){
	return name + ". " + description + ". " + status()+ " Actions that can be taken: " + possibleActions() + ".";
    }

    
    //public void Haunt()
    //Change state and manage itemActionsArray
    
}
