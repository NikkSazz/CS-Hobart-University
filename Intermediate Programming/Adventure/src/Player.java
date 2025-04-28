import java.io.IOException;
import java.util.ArrayList;

public class Player {
    
    private Room currentRoom_;
    private ArrayList<Item> inventory_;
    private Map map_;
    
    public Player(String roomsFilePath, String startingRoom) {
    	System.out.println("PLAYER is created");
    	
    	try {
			map_ = new Map(roomsFilePath);
		} 
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	inventory_ = new ArrayList<Item>();
    	
    	currentRoom_ = map_.get(startingRoom);
    }
    
    String getLocation() {
        return currentRoom_.getName();
    }

    void look() {
    		currentRoom_.printName();
        currentRoom_.printDescription();
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