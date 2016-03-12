package org.b.v.games.bingo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BingoApplication {
	
	private static final int DEFAULT_GRID_ROWS = 9;
	private static final int DEFAULT_GRID_COLS = 10;

	private static JFrame mainScreen;
	private static JFrame secondScreen;
	private static BingoScreen bingoPanel;
	
	private static BingoScreen contentPane(String[] args) {
		if(args.length < 2) {
			return new BingoScreen(DEFAULT_GRID_ROWS,DEFAULT_GRID_COLS);
		}else {
			int rows = Integer.parseInt(args[0]);
			int cols = Integer.parseInt(args[1]);
			return new BingoScreen(rows,cols);
		}
	}
	
	private static void initializeTheAdminstrationMenu(JMenuBar menuBar) {
		JMenu menu = new JMenu("Extra");
        menu.setMnemonic(KeyEvent.VK_E);
 
        JMenuItem extraFeatures = new JMenuItem("2de scherm");
        extraFeatures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(secondScreen==null) {
					secondScreen = new JFrame();
					secondScreen.setContentPane(new BingoScreen(DEFAULT_GRID_ROWS,DEFAULT_GRID_COLS));
					secondScreen.setSize(new Dimension(600,300));
				}
				secondScreen.setVisible(true);
			}
		});
        
    
        menu.add(extraFeatures);
        menuBar.add(menu);
	}
	
	public static void main(String[] args) {
		mainScreen = new JFrame();
		
		JMenuBar menuBar = new JMenuBar();
		mainScreen.setJMenuBar(menuBar);
		initializeTheAdminstrationMenu(menuBar);
		
		bingoPanel = contentPane(args);
		
		mainScreen.setContentPane(bingoPanel);
		mainScreen.setExtendedState(mainScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.pack();
		mainScreen.setVisible(true);
	}
}
