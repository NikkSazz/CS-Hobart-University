
enum Direction {
	NORTH, N, UP, U,
	SOUTH, S, DOWN, D,
	WEST, W, LEFT, L,
	EAST, E, RIGHT, R;
	
	public static Direction getDirection(String direction) {
		
		try {
			
		Direction d = Direction.valueOf(direction.toUpperCase());
		// Will throw an exception string is not a real direction
		
		Direction ans = switch(d) {
			case NORTH, N, UP, U -> NORTH;
			case SOUTH, S, DOWN, D -> SOUTH;
			case WEST, W, LEFT, L -> WEST;
			case EAST, E, RIGHT, R -> EAST;
		};
		return ans;
		
		
		} catch(IllegalArgumentException e) {
			
			// String is not a possible option
			return null;
		}
		
	}
	
	static void printDirections() {
    	
        System.out.println("\nDirections:");
        for(Direction d : Direction.values()) {
            System.out.println(d.name());
        }
        System.out.println();
        
    }
	
}
