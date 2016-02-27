package org.b.v.games.bingo;

import java.util.Random;

public class BingoGame  {

	private final int size;
	private Random random;
	private boolean selections[];
	
	private int mostRecentlySelected = -1;
	
	private volatile int numberOfAlreadSelectedPositions = 0;
	
	public BingoGame(int size) throws IllegalArgumentException {
		if(size < 1) {
			throw new IllegalArgumentException("size should be at least 1, is now " + size);
		}
		
		random = new Random();
		this.size = size;
		selections = new boolean[size];
		initializeGridToFalse();
	}
	
	private void initializeGridToFalse() {
		for (int i = 0; i < size; ++i) {
        	selections[i]=false;
        }
	}

	public int guessSelection() throws EveryThingsAlreadySelected {
		if(isEverythingsAlreadySelected()) { throw new EveryThingsAlreadySelected(); }
		
		int guess = random.nextInt(numberOfRemainingPositionsToBeSelected());
		int numberOfSelection = searchNumberOfSelection(guess);
	
		rememberTheSelection(numberOfSelection);

		return numberOfSelection;
	}

	private int searchNumberOfSelection(int guess) {
		int numberOfSelectionsVerified = 0;
		for(int i=0;i<size;i++) {
			if(selectionAlreadyGuessed(i)){
				continue;
			} else {
				if(numberOfSelectionsVerified==guess) {
					return i;
				} else {
					numberOfSelectionsVerified++;
				}
			}
		}
		throw new RuntimeException("should not reach");
	}

	public int getMostRecentlySelected() {
		return mostRecentlySelected;
	}
	
	public int numberOfRemainingPositionsToBeSelected() {
		return size-numberOfAlreadSelectedPositions;
	}

	public boolean isEverythingsAlreadySelected() {
		return numberOfAlreadSelectedPositions >= size;
	}
	
	
	private boolean selectionAlreadyGuessed(int i) {
		return selections[i];
	}
	
	private void rememberTheSelection(int i) {
		mostRecentlySelected = i;
		selections[i]=true;
		numberOfAlreadSelectedPositions++;
	}

	public boolean isRunning() {
		if(mostRecentlySelected >= 0 ) {
			if(isEverythingsAlreadySelected()) {
				return false;
			}
			return true;
		}
		return false;
	}

	public void clean() {
		mostRecentlySelected = -1;
		numberOfAlreadSelectedPositions = 0;
		random = new Random();
		selections = new boolean[size];
		initializeGridToFalse();
	}
}
