import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    private HashMap<String, Room> map_;
    private HashMap<String, Item> items_;

    /**
     *  Constructor, reads txt file and creates a hashmap of rooms with Room name as key
     * 
     * @param roomsFilePath
     *  Path to File of rooms, with their description and neighbors
     * @param itemsFilePath
     * 	Path to File of items, with their description and location
     * @throws IOException File is not found
     * 
     */
    public Map(String roomsFilePath, String itemsFilePath) throws IOException {

        initMap(roomsFilePath);
        initItems(itemsFilePath);
        
    }
    
    private void initItems(String filePath) throws IOException {
        items_ = new HashMap<>();

        BufferedReader r = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filePath), "UTF-8"));

        String name, origin, destination;
        ArrayList<String> descr;
        String line = r.readLine();

        while (line != null) {

            name = line;
            origin = r.readLine();
            destination = r.readLine();
            
            // Make sure these rooms exists
            /*
             * note: I have no idea why I thought this was required.
            if(!map_.containsKey(origin)) {
            	System.err.println("***origin does not exist*** @ initItems()");
            }
            
            if(!map_.containsKey(destination)) {
            	System.err.println("destination does not exist! destination = " + destination);
            }
            */
            descr = new ArrayList<>();
            
            line = r.readLine();
            while (line != null && !"END".equals(line)) {
                descr.add(line);
                line = r.readLine();
            }

            items_.put(name, new Item(name, descr, origin, destination));
            // System.out.println("Added " + name + "\nfrom " + origin + " to " + destination);
            
            line = r.readLine();
        }

        System.out.println("All Items initialized successfully");
        r.close();
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

    public HashMap<String, Room> getRoomHashMap() {
    	return map_;
    }
}