
import java.util.ArrayList;

public class Room {

    private String name_;
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
            return null;
        }

        if(neighbors_[direction].equals("-")) {
            // nothing in that direction
            return null;
        }
        return map_.get(neighbors_[direction]);
    }

}
