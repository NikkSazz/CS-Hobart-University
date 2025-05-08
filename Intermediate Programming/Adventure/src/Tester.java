import java.io.IOException;
import java.util.Map.Entry;

public class Tester {

	/*
	 * Note: using System.err.println() is just the same, except red color
	 */
    public static void main(String[] args) {
        System.out.println("=== TESTER START ===");

        String roomsFilePath = "data/rooms.txt";
        String itemsFilePath = "data/items.txt";

        Map map;
		try {
			map = new Map(roomsFilePath, itemsFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
        System.out.println("map created successfully.");

        testRoomCorrectness(map);
        
        System.out.println("\n\n=== TESTING COMPLETE ===");
        

    }
    
    private static void testRoomCorrectness(Map map) {
    	
    	int roomCount = 0;
        int errors = 0;

        // for each of a hashmap
        for (Entry<String, Room> entry : map.getRoomHashMap().entrySet()) {
        	
            Room room = entry.getValue();
            System.out.println("\n\nTesting Room: " + room.getName());

            
            if (room.getName() == null || room.getName().isEmpty()) {
                System.err.println("FAIL: Room name is null or empty.");
                errors++;
            }

            if (room.getName().contains(",")) {
                System.err.println("FAIL: Room name contains commas, which could break parsing.");
                errors++;
            }

            // Neighbor checks
            for (int i = 0; i < 4; i++) {
                if (i >= room.getNeighbors().length) break;

                String neighborName = room.getNeighbors()[i].trim();
                if (neighborName.equals("-")) continue;

                if (!map.hasRoom(neighborName)) {
                    System.err.println("FAIL: Neighbor '" + neighborName + "' from room '" + room.getName() + "' does not exist in map.");
                    errors++;
                }
            }

            roomCount++;
        }
    	
    	System.out.println("\nRooms tested: " + roomCount);
        System.out.println("Errors found: " + errors);

        if (errors == 0) {
            System.out.println("ALL TESTS PASSED.");
        } else {
            System.err.println("THERE WERE FAILURES.");
        }
    }
}
