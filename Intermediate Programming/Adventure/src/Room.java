
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

    private String name_;
    private String neighborString_;
    private String[] neighbors_;
    private ArrayList<String> description_;
    private Map map_;
    private boolean visited_;
    
    public Room(String name, String neighbors, 
        ArrayList<String> description, Map map) {

        name_ = name;
        
        // System.out.println("Neighbors of " + name + ": " + neighbors);
        neighbors_ = neighbors.split(",");
        neighborString_ = neighbors;
        
        /*
        for(var v : neighbors_) {
        	System.out.print("\t" + v);
        }
        System.out.println();
        */
        
        description_ = description;
        map_ = map;
        visited_ = false;

    }
    
    public boolean hasNeighborAt(int direction) {
    	
    	if(neighbors_[direction].trim().equals("-")) {
    		return false;
    	}
    	
    	return map_.hasRoom(neighbors_[direction].trim());
    	
    }

    public void printName() {
    	System.out.println("Location: " + name_);
    }
    
    public void printDescription() {
    	// System.out.println(name_);
        for(String line : description_) {
        	
        	if(line.trim().equals("END")) {
        		break;
        	}
        	
            System.out.println(line);
        }
        // Print neighbors also? 
        System.out.println(neighborString_);
    }

    public String getName() {
        return name_;
    }
    
    public void visit() {
    	if(!visited_) {
    		printDescription();
    		visited_ = true;
    	}
    }

    public Room move(int direction) {

    	/*
    	for(var s : neighbors_) {
    		System.out.println("S -> " + s);
    	}
    	*/
    	
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
        
        Room room = map_.get(neighbors_[direction].trim());
        
        if (room == null) {
            System.out.println("You can't go that way!");
            return this; // Stay in curr room
        }
        
        System.out.println("Moving from " + name_ + " to " + room.getName());
        room.visit();
        return room;
    }

}