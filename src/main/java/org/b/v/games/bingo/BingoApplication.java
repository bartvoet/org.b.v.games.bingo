package org.b.v.games.bingo;

import javax.swing.JFrame;

public class BingoApplication {
	
	private static final int DEFAULT_GRID_ROWS = 9;
	private static final int DEFAULT_GRID_COLS = 10;

	private static BingoScreen contentPane(String[] args) {
		if(args.length < 2) {
			return new BingoScreen(DEFAULT_GRID_ROWS,DEFAULT_GRID_COLS);
		}else {
			int rows = Integer.parseInt(args[0]);
			int cols = Integer.parseInt(args[1]);
			return new BingoScreen(rows,cols);
		}
	}
	
	public static void main(String[] args) {
		JFrame mainScreen = new JFrame();
		mainScreen.setContentPane(contentPane(args));
		mainScreen.setExtendedState(mainScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.pack();
		mainScreen.setVisible(true);
	}
}
