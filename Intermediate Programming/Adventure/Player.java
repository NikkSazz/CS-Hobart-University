import java.io.IOException;
import java.util.ArrayList;

public class Player {
    
    private Room currentRoom_;
    private ArrayList<Item> inventory_;
    private Map map_;
    
    public Player(String roomsFilePath, String startingRoom, String itemsFilePath) {
    	// System.out.println("PLAYER is created");
    	
    	try {
			map_ = new Map(roomsFilePath, itemsFilePath);
		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	inventory_ = new ArrayList<Item>();
    	
    	currentRoom_ = map_.get(startingRoom);
    	if(currentRoom_ == null) {
    		System.out.println("currentRoom_ is null");
    	}
    }
    
    String getLocation() {
        return currentRoom_.getName();
    }

    public void look() {
    	
    		currentRoom_.printName();
        currentRoom_.printDescription();
        for(Item i : inventory_) {
        	i.look();
        }
        
    }
    
    public void take() {
    	System.out.println("player has taken an item");
    }
    
    void moveNorth() {
        currentRoom_ = currentRoom_.move(0);
    }

    void moveSouth() {
        currentRoom_ = currentRoom_.move(1);
    }

    void moveEast() {
        currentRoom_ = currentRoom_.move(2);
    }
    void moveWest() {
        currentRoom_ = currentRoom_.move(3);
    }

    public void printMap() {
    	map_.printKeyValues();
    }

}