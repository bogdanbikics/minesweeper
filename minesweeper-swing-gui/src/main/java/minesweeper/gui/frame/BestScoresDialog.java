package minesweeper.gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import minesweeper.bestscore.BestScoreModel;
import minesweeper.bestscore.BestScoreModels;

public class BestScoresDialog extends JDialog implements ActionListener {

	public BestScoresDialog(JFrame parent, BestScoreModels bestScoreModels) {
		super(parent, "Best Scores", true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - parent.getSize().width / 2, screenSize.height / 2 - parent.getSize().height / 2);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		if (bestScoreModels.getBestScores().isEmpty()) {
			mainPanel.add(buildEmptyScorePanel(), BorderLayout.NORTH);
		}
		else {
			mainPanel.add(buildBestScorePanel(bestScoreModels), BorderLayout.NORTH);
		}
		
		
		mainPanel.add(buildButtonPanel(), BorderLayout.SOUTH);
		
	    add(mainPanel);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack(); 
		setVisible(true);
	}

	private JLabel buildEmptyScorePanel() {
		return new JLabel("There was no finished game yet.");
	}

	private JPanel buildBestScorePanel(BestScoreModels bestScoreModels) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 3, 10, 5));
		for (BestScoreModel scoreModel : bestScoreModels.getBestScores()) {
			panel.add(new JLabel(scoreModel.getDifficulty().getName()));
			JLabel timeLabel = new JLabel(scoreModel.getTime() + " sec");
			timeLabel.setHorizontalAlignment(JLabel.RIGHT);
			panel.add(timeLabel);
			panel.add(new JLabel(scoreModel.getName()));
		}
		return panel;
	}

	private JPanel buildButtonPanel() {
		JPanel buttonPane = new JPanel();
	    JButton button = new JButton("OK"); 
	    buttonPane.add(button); 
	    button.addActionListener(this);
		return buttonPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
