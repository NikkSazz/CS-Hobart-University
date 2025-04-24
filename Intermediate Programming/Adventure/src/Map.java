import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    HashMap<String, Room> map_;

    public Map(String filePath) throws IOException {
        // filePath = Adventure/data/rooms.txt;

        map_ = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        String roomName = "", neighbors = "";
        ArrayList<String> description = new ArrayList<>();

        while(line != null) {
            line = reader.readLine();

            if(line == "END") {
                // TODO create a room
                Room room = new Room(roomName, neighbors, description);
                map_.put(roomName, room);
                
                roomName = "";
                neighbors = "";
                description.clear();
            }
            else if(roomName == "") {

            }
            
        }
        reader.close();

    }

}
