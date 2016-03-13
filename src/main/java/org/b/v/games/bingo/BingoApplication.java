package org.b.v.games.bingo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class BingoApplication {
	
	private static final int DEFAULT_GRID_ROWS = 9;
	private static final int DEFAULT_GRID_COLS = 10;

	private static JFrame mainScreen;
	private static BingoPanel mainPanel;
	private static JFrame secondScreen;
	private static BingoPanel secondPanel;
	
	private static final String NEXT_LABEL = "volgende";
	private static final String RESTART_LABEL = "nieuw spel";
	
	private static JButton nextButton = new JButton(NEXT_LABEL);
	private static JButton restartButton = new JButton(RESTART_LABEL);
	
	
	private static BingoGame game;
	
	private static BingoPanel contentPane(String[] args) {
		if(args.length < 2) {
			game=new BingoGame(DEFAULT_GRID_ROWS*DEFAULT_GRID_COLS);
			return new BingoPanel(DEFAULT_GRID_ROWS,DEFAULT_GRID_COLS);
		}else {
			int rows = Integer.parseInt(args[0]);
			int cols = Integer.parseInt(args[1]);
			game=new BingoGame(rows*cols);
			return new BingoPanel(rows,cols);
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
					secondPanel = mainPanel.copy();
					secondScreen.setContentPane(secondPanel);
					secondScreen.setSize(new Dimension(600,300));
				}
				secondScreen.setVisible(true);
			}
		});
        
    
        menu.add(extraFeatures);
        menuBar.add(menu);
	}
	
	private static void configureSomeActions() {
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!game.isEverythingsAlreadySelected()) {
					int selection = game.guessSelection();
					mainPanel.markTheSelectedNumberOnTheScreen(selection);
					if(secondPanel!=null) {
						secondPanel.markTheSelectedNumberOnTheScreen(selection);
					}
				}
			}
		});
		
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.clean();
				mainPanel.clean();
				if(secondPanel!=null) {
					secondPanel.clean();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		mainScreen = new JFrame();
		
		JMenuBar menuBar = new JMenuBar();
		mainScreen.setJMenuBar(menuBar);
		initializeTheAdminstrationMenu(menuBar);
		
		JPanel mainScreenPanel = new JPanel();
		mainScreenPanel.setLayout(new BorderLayout());
		mainPanel = contentPane(args);
		mainScreenPanel.add(mainPanel,BorderLayout.CENTER);
		
		nextButton = new JButton(NEXT_LABEL);
		restartButton = new JButton(RESTART_LABEL);
		configureSomeActions();
		
		JPanel panel = new JPanel();
		panel.add(nextButton);
		panel.add(restartButton);
		mainScreenPanel.add(panel,BorderLayout.PAGE_END);
		
		mainScreen.setContentPane(mainScreenPanel);
		mainScreen.setExtendedState(mainScreen.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.pack();
		mainScreen.setVisible(true);
	}
}
