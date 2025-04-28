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
     * @param filePath
     *  File of rooms, with their description and neighbors
     * @throws IOException File is not found
     * 
     */
    public Map(String filePath) throws IOException {

        map_ = new HashMap<>();

        BufferedReader reader = new BufferedReader(
        		new InputStreamReader(
        				new FileInputStream(filePath), "UTF-8"));
        String line = "";
        String roomName = "", neighbors = "";
        ArrayList<String> description = new ArrayList<>();

        while(line != null) {

            line = reader.readLine();

            if("END".equals(line)) {
                //  create a room
            	System.out.println("Created " + roomName);
                Room room = new Room(roomName, neighbors, description, this);
                map_.put(roomName, room);
                
                roomName = "";
                neighbors = "";
                description = new ArrayList<>();
            }
            else if(roomName.isEmpty()) {
                roomName = line;
            }
            else if(neighbors.isEmpty()) {
                neighbors = line;
            }
            else {
                description.add(line);
            }
            
        } // while read txt file
        reader.close();

    }

    public boolean hasRoom(String name) {
    	return map_.containsKey(name);
    }
    
    public Room get(String roomName) {
        return map_.get(roomName);
    }

}
