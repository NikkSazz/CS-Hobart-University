import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    HashMap<String, Room> map_;
    HashMap<String, Item> items_;

    /**
     *  Constructor, reads txt file and creates a hashmap of rooms with Room name as key
     * 
     * @param roomsFilePath
     *  File of rooms, with their description and neighbors
     * @param itemsFilePath
     * 	File of items, with their description and location
     * @throws IOException File is not found
     * 
     */
    public Map(String roomsFilePath, String itemsFilePath) throws IOException {

        initMap(roomsFilePath);
        initItems(itemsFilePath);
        
    }
    
    private void initItems(String filePath) throws IOException {
    	items_ = new HashMap<>();

        BufferedReader reader = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(filePath), "UTF-8"));
        
        
        
        reader.close();
    }
    
    private void initMap(String filePath) throws IOException {
    	map_ = new HashMap<>();

        BufferedReader reader = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(filePath), "UTF-8"));
        
        String roomName = "", neighbors = "";
        ArrayList<String> description = new ArrayList<>();
        String line = "";
        
        while(line != null) {

        	if(roomName.isEmpty()) {
        		roomName = reader.readLine();
        		neighbors = reader.readLine();
        	}
            
            line = reader.readLine();

            if("END".equals(line)) {
                //  create a room
            	// System.out.println("Created " + roomName);
                Room room = new Room(roomName, neighbors, description, this);
                map_.put(roomName, room);
                
                roomName = "";
                neighbors = "";
                description = new ArrayList<>();
                continue;
            }
            
            description.add(line);
            
            // line = reader.readLine();
            
        } // while read txt file
        reader.close();

    }
    
    public void printKeyValues() {
    	for(var e : map_.entrySet()) {
    		System.out.println(e.getKey());
    	}
    }

    public boolean hasRoom(String name) {
    	return map_.containsKey(name);
    }
    
    public Room get(String roomName) {
        return map_.get(roomName);
    }

}