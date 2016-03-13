package org.b.v.games.bingo;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BingoPanel extends JPanel {
	private static final long serialVersionUID = 3640685537306132479L;

	private static final String INITIAL_TOP_LABEL = "?";
	
	private static final int SIZE_OF_TOP_LABEL = 400;
	private static final int SIZE_OF_GRID_LABEL = 20;

	private JLabel topLabel;

	private GridPanel grid;

	private int cols;

	private int rows;
	
	private BingoPanel() {}
	
	public BingoPanel(int rows,int cols) {
		this.rows=rows;
		this.cols=cols;
		initializeTheComponents(rows, cols);
		doSomeLayout();
	}

	private void initializeTheComponents(int rows, int cols) {
		topLabel = new JLabel(INITIAL_TOP_LABEL,SwingConstants.CENTER);
		grid = GridPanel.fromTopToBottom(cols,rows,SIZE_OF_GRID_LABEL);
	}
	
	public void clean() {
		grid.clean();
		topLabel.setText(INITIAL_TOP_LABEL);
	}

	public void markTheSelectedNumberOnTheScreen(int i) {
		topLabel.setText(Integer.toString((i+1)));
		grid.markANewNumberOnTheScreen(i);
	}

	private void doSomeLayout() {
		setLayout(new BorderLayout());
		setLabelSize(topLabel,SIZE_OF_TOP_LABEL);
		
		this.add(topLabel,BorderLayout.PAGE_START);
		this.add(grid,BorderLayout.CENTER);
	}

	private void setLabelSize(JLabel label,int size) {
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), size));
	}

	public BingoPanel copy() {
		BingoPanel copy = new BingoPanel();
		copy.rows=rows;
		copy.cols=cols;
		copy.grid = grid.copy();
		copy.topLabel=new JLabel(topLabel.getText(),SwingConstants.CENTER);
		copy.doSomeLayout();
		return copy;
	}
}
