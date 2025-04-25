import java.util.ArrayList;

@SuppressWarnings("unused") // ;_:
public class Player {
    
    private Room currentRoom_;
    private ArrayList<Item> inventory_;
    
    String getLocation() {
        return currentRoom_.getName();
    }

    void look() {
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


}