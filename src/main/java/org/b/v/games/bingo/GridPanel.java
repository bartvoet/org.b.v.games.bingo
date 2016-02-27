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
	
	public GridPanel(int rows,int cols,int sizeOfLabels) {
        setLayout(new GridLayout(rows,cols));
        size = rows * cols;
        gridLabels = new JLabel[size];
        
        for (int i = 0; i < size; ++i) {
        	gridLabels[i] = new JLabel("",SwingConstants.CENTER);
        	setLabelSize(gridLabels[i],sizeOfLabels);
        	this.add(gridLabels[i]);
        }
    }
	
	private void setLabelSize(JLabel label,int size) {
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), size));
	}
	
	public void markANewNumberOnTheScreen(int i) {
		gridLabels[i].setText(Integer.toString((i+1)));
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
}