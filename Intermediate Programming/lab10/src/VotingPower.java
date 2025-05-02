import java.util.Scanner;

public class VotingPower {

	/**
	 * Track the number of votes each candidate has in a 2-party election.
	 */
	private static class ElectionTally {

		private int[] counts_;

		/**
		 * Initialize a new tally - both candidates have 0 votes.
		 */
		ElectionTally () {
			counts_ = new int[] { 0, 0 };
		}

		/**
		 * Add to a candidate's vote total.
		 * 
		 * @param candidate
		 *          candidate (0 or 1)
		 * @param numvotes
		 *          number of votes to add
		 */
		void vote ( int candidate, int numvotes ) {
			if ( candidate < 0 || candidate > 1 ) {
				throw new IllegalArgumentException("candidate must be 0 or 1");
			}

			counts_[candidate] += numvotes;
		}

		/**
		 * Subtract from a candidate's vote total.
		 * 
		 * @param candidate
		 *          candidate (0 or 1)
		 * @param numvotes
		 *          number of votes to subtract
		 */
		void unvote ( int candidate, int numvotes ) {
			if ( candidate < 0 || candidate > 1 ) {
				throw new IllegalArgumentException("candidate must be 0 or 1");
			}

			counts_[candidate] -= numvotes;
		}

		/**
		 * Does a voter with numvotes affect the outcome of the election?
		 * 
		 * @param numvotes
		 *          number of votes
		 * @return true if the outcome of the election changes depending on which
		 *         candidate gets numvotes extra votes, false others
		 */
		boolean swings ( int numvotes ) {
			return getWinner(0,numvotes) != getWinner(1,numvotes);
		}

		/**
		 * Get the winner of the election if the candidate specified has extra
		 * votes.
		 * 
		 * @param candidate
		 * @param numvotes
		 *          number of extra votes for the candidate
		 * @return which candidate wins (0 or 1), or -1 if there's a tie
		 */
		private int getWinner ( int candidate, int numvotes ) {
			if ( candidate < 0 || candidate > 1 ) {
				throw new IllegalArgumentException("candidate must be 0 or 1");
			}

			int other = (candidate == 0 ? 1 : 0);
			if ( counts_[candidate] + numvotes > counts_[other] ) {
				// candidate wins
				return candidate;
			} else if ( counts_[candidate] + numvotes == counts_[other] ) {
				// tie
				return -1;
			} else {
				// other wins
				return other;
			}
		}

		/**
		 * Print each candidate's vote total.
		 */
		void print () {
			System.out.println("candidate 0 - " + counts_[0] + " / candidate 1 - "
			    + counts_[1]);
		}
	}

	/**
	 * Count the number of times the specified voting block swings the outcome of
	 * the election.
	 * 
	 * @param blockVotes
	 *          blockVotes[i] is the number of votes block i has
	 * @param block
	 *          the block to count swings for (0 <= block < blockVotes.length)
	 * @return the number of times the specified voting block swings the outcome
	 *         of the election
	 */
	public static int countSwingScenarios ( int[] blockVotes, int block ) {		
		return countSwingScenarios(blockVotes, block, new ElectionTally(), 0);

	}

	private static int countSwingScenarios(int[] blockVotes, int block, ElectionTally eTally, int currentBlock) {
		
		if(currentBlock == blockVotes.length) {
			return eTally.swings(blockVotes[block]) ? 1 : 0;
		}
		
		if (currentBlock == block) {
			return countSwingScenarios(blockVotes, block, eTally, currentBlock + 1);
		}
		
		int swings = 0;
		for(int candidate = 0; candidate <= 1; candidate++) {
			eTally.vote(candidate, blockVotes[currentBlock]);
			swings += countSwingScenarios(blockVotes, block, eTally, currentBlock + 1);
			eTally.unvote(candidate, blockVotes[currentBlock]);
		}
		
		return swings;
	}
	
	public static void main ( String[] args ) {

		Scanner input = new Scanner(System.in);

		System.out.print("how many blocks?  ");
		int numblocks = input.nextInt();

		System.out.print("enter votes for each block:  ");
		int[] votes = new int[numblocks];
		for ( int i = 0 ; i < votes.length ; i++ ) {
			votes[i] = input.nextInt();
		}

		System.out.println();

		System.out.println("number of scenarios where a block swings the election:");
		for ( int i = 0 ; i < votes.length ; i++ ) {
			System.out.println("block " + i + ": " + countSwingScenarios(votes,i));
		}
	}

}
