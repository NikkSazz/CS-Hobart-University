
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private String name_;
    // private HashMap<String, Room> neighbors_;
    private String[] neighbors_;
    private ArrayList<String> description_;
    private Map map_;
    
    public Room(String name, String neighbors, 
        ArrayList<String> description, Map map) {

        name_ = name;
        neighbors_ = neighbors.split(",");
        description_ = description;
        map_ = map;

    }
    
    public boolean hasNeighborAt(int direction) {
    	
    	if(neighbors_[direction].trim().equals("-")) {
    		return false;
    	}
    	
    	return map_.hasRoom(neighbors_[direction].trim());
    	
    }

    public void printDescription() {
        for(String line : description_) {
            System.out.println(line);
        }
        // Print neighbors also? 
    }

    public String getName() {
        return name_;
    }

    public Room move(int direction) {

        if(direction >= neighbors_.length) {
            // throw? should never happen unless the txt file is wrong
        	System.out.println("**neighbors_.length <= directionIndex**");
            return null;
        }

        if(neighbors_[direction].equals("-")) {
            // nothing in that direction
        	System.out.println("**Nothing in this direction**");
            return null;
        }
        
        Room room = map_.get(neighbors_[direction]);
        
        if (room == null) {
            System.out.println("You can't go that way!");
            return this; // Stay in curr room
        }
        
        System.out.println(room.getName());
        return room;
    }

}
