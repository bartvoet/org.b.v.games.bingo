package org.b.v.games.bingo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GridPanel extends JPanel
{
	private static final long serialVersionUID = 4475571958607851323L;
	
	private JLabel gridLabels[]; 

	private int size;
	private int lastSelected = -1;
	
	private PositionManager positionManager;

	private int sizeOfLabels;

	private int rows;

	private int cols;
	
	private interface PositionManager {
		int getActualPosition(int pos);
	}
	
	private GridPanel(){}
	
	private GridPanel(int rows,int cols,int sizeOfLabels) {
        this.size=rows * cols;
        this.rows=rows;
        this.cols=cols;
        this.sizeOfLabels=sizeOfLabels;
		gridLabels = new JLabel[size];

        for (int i = 0; i < size; ++i) {
        	gridLabels[i] = new JLabel("",SwingConstants.CENTER);
        	setLabelSize(gridLabels[i],sizeOfLabels);
        	add(gridLabels[i]);
        }
	}
	
	public static GridPanel fromLeftToRight(int rows,int cols,int sizeOfLabels) {
		GridPanel panel = new GridPanel(rows , cols,sizeOfLabels);
		panel.setLayout(new GridLayout(rows,cols));
        panel.positionManager = new PositionManager() {
			@Override
			public int getActualPosition(int pos) {
				return pos;
			}
		};
		return panel;
	}
	

	public static GridPanel fromTopToBottom(final int rows,final int cols,int sizeOfLabels) {
		GridPanel panel = new GridPanel(rows,cols,sizeOfLabels);
		panel.setLayout(new GridLayout(rows,cols));
        
        panel.positionManager = new PositionManager() {
			@Override
			public int getActualPosition(int pos) {
				return(((pos % rows) * cols) + (pos / rows));
			}
		};
		
		return panel;
	}

	
	
	private void setLabelSize(JLabel label,int size) {
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), size));
	}
	
	public void markANewNumberOnTheScreen(int j) {
		int i = positionManager.getActualPosition(j);
		
		gridLabels[i].setText(Integer.toString((j+1)));
		gridLabels[i].setBorder(BorderFactory.createLineBorder(Color.black));
		if(lastSelected >= 0) {
			gridLabels[lastSelected].setBorder(new EmptyBorder(0,0,0,0));
		}
		lastSelected=i;
	}

	public void clean() {
        for (int i = 0; i < size; ++i) {
        	gridLabels[i].setText("");
        	gridLabels[i].setBorder(new EmptyBorder(0,0,0,0));
        }
		
	}

	public GridPanel copy() {
		GridPanel copy = new GridPanel();
		copy.size=rows * cols;
		copy.rows=rows;
		copy.cols=cols;
		copy.sizeOfLabels=sizeOfLabels;
		copy.gridLabels = new JLabel[size];

		copy.setLayout(new GridLayout(rows,cols));
		copy.positionManager = new PositionManager() {
			@Override
			public int getActualPosition(int pos) {
				return(((pos % rows) * cols) + (pos / rows));
			}
		};
		
        for (int i = 0; i < size; ++i) {
        	copy.gridLabels[i] = new JLabel(this.gridLabels[i].getText(),SwingConstants.CENTER);
        	copy.setLabelSize(copy.gridLabels[i],sizeOfLabels);
        	copy.add(copy.gridLabels[i]);
        }
		
		return copy;
	}
}