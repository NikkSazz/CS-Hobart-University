import java.util.Random;

/**
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class RabbitHuntSim extends Sim {

	private boolean isRabbitFree_;
	private Creature[] creatures_;
	
	
	/**
	 * @param rows
	 * @param cols
	 */
	protected RabbitHuntSim ( int rows, int cols ) {
		super(rows,cols);
		isRabbitFree_ = true;
		
		makeCreatures(rows, cols);
		addCreatures();
		
	}
	
	private void makeCreatures(int rows, int cols) {
		
		creatures_ = new Creature[20];
		
		creatures_[0] = new Rabbit();
		creatures_[1] = new Fox();
		creatures_[2] = new Werebush(new Rabbit());
		creatures_[3] = new Werebush(new Fox());
		creatures_[4] = new Werebush(new Sloth());		
		
		for(int i = 5; i < creatures_.length; i++) {
			creatures_[i] = new Sloth();			
		}

		
		for(int j = 0; j < 10; j++) {
			field_.placeRandomly(new Bush());
		}
				
	}
	
	private void addCreatures() {
		for(Creature c : creatures_) {
			field_.placeRandomly(c);
		}
	}
	
	public void reset() {
		super.reset();
		isRabbitFree_ = true;
	}
	
	public void step() {
		if(isRabbitFree_) {
			super.step();
		}
		
		for(Creature c : creatures_) {
			
			int nextMove = c.getNextMove(field_);

			if(c instanceof Fox) {
				var neighbor = field_.neighbor(c,nextMove);
				
				if(neighbor instanceof Rabbit) {
					isRabbitFree_ = false; // ??
				} 
				
			} // if fox
			
			if(field_.canMove(c,nextMove)) {
				field_.move(c,nextMove); // if fox, it will still move into the rabbit
			}
			
		} // for c in creatures_
		
	} 
	
	public boolean isOver() {
			return super.isOver() || !isRabbitFree_;
	}
	
	public boolean isRabbitFree() {
		return isRabbitFree_;
	}
	
}
