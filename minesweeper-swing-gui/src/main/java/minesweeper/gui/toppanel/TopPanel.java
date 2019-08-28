package minesweeper.gui.toppanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TopPanel extends JPanel {	
	
	private TopPanel() {
		super();
	}
	
	public static TopPanel create(FaceButton faceButton, FlagTextField flagTextField, TimeTextField timerTextField) {
		TopPanel facePanel = new TopPanel();
		facePanel.setLayout(new BoxLayout(facePanel, BoxLayout.X_AXIS));
	    facePanel.setBorder(BorderFactory.createLoweredBevelBorder());
	    facePanel.setBackground(Color.LIGHT_GRAY);
	    facePanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    
	    facePanel.add(Box.createHorizontalStrut(5));
	    facePanel.add(flagTextField);
	    facePanel.add(Box.createHorizontalGlue());
	    facePanel.add(faceButton);
	    facePanel.add(Box.createHorizontalGlue());
	    facePanel.add(timerTextField);
	    facePanel.add(Box.createHorizontalStrut(5));
	    
	    return facePanel;
	}
}
