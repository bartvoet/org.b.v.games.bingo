package org.b.v.games.bingo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BingoScreen extends JFrame {
	private static final long serialVersionUID = 3640685537306132479L;

	private static final String INITIAL_TOP_LABEL = "?";
	private static final String NEXT_LABEL = "volgende";
	private static final String RESTART_LABEL = "nieuw spel";
	
	private static final int DEFAULT_GRID_ROWS = 9;
	private static final int DEFAULT_GRID_COLS = 10;
	
	private static final int SIZE_OF_TOP_LABEL = 160;
	private static final int SIZE_OF_GRID_LABEL = 20;

	private JButton nextButton;
	private JButton restartButton;
	private JLabel topLabel;

	private GridPanel grid;
	private BingoGame game;
	
	public BingoScreen(int rows,int cols) {
		initializeTheComponents(rows, cols);
		configureSomeActions();
		doSomeLayout();

		pack();
        setVisible(true);
	}

	private void initializeTheComponents(int rows, int cols) {
		nextButton = new JButton(NEXT_LABEL);
		restartButton = new JButton(RESTART_LABEL);
		topLabel = new JLabel(INITIAL_TOP_LABEL,SwingConstants.CENTER);
		
		game = new BingoGame(rows * cols);
		grid = new GridPanel(rows,cols,SIZE_OF_GRID_LABEL);
	}

	private void configureSomeActions() {
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeAGuess();
			}
		});
		
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				grid.clean();
				game.clean();
				topLabel.setText(INITIAL_TOP_LABEL);
			}
		});
	}

	private void doSomeLayout() {
		setLayout(new BorderLayout());
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLabelSize(topLabel,SIZE_OF_TOP_LABEL);
		
		this.add(topLabel,BorderLayout.PAGE_START);
		this.add(grid,BorderLayout.CENTER);
		
		
		JPanel panel = new JPanel();
		panel.add(nextButton);
		panel.add(restartButton);
		this.add(panel,BorderLayout.PAGE_END);
	}

	private void setLabelSize(JLabel label,int size) {
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), size));
	}

	private void makeAGuess() {
		if(!game.isEverythingsAlreadySelected()) {
			markTheSelectedNumberOnTheScreen(game.guessSelection());
		}
	}
	
	private void markTheSelectedNumberOnTheScreen(int i) {
		topLabel.setText(Integer.toString((i+1)));
		grid.markANewNumberOnTheScreen(i);
	}
	
    public static void main(String[] args) {
		if(args.length < 2) {
			new BingoScreen(DEFAULT_GRID_ROWS,DEFAULT_GRID_COLS);
		}else {
			int rows = Integer.parseInt(args[0]);
			int cols = Integer.parseInt(args[1]);
			new BingoScreen(rows,cols);
		}
	}
}
