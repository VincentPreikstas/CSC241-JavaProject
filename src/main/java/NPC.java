public abstract class NPC extends Character {
    

    //public int getScaredLevel(){
    //return scaredLevel;
    //}

    
    public NPC (String name, String description){
	super(name, description);
    }

    
    /*
    public void cleanRoom();

    This will be implemented in child and adult classes in order to remove broken items from the room

    public void reaction()

will check to see what reaction if any is necessary when the player haunts an item. this will also handle leaving a given room if the reaction the haunting caused the NPC's fear level to warrant a room changle

    */
    
}
