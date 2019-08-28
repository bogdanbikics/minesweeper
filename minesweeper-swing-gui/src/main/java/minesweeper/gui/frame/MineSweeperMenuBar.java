package minesweeper.gui.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import minesweeper.game.GameController;
import minesweeper.game.GameModel;
import minesweeper.minefield.MineFieldDifficulty;
import minesweeper.minefield.MineFieldModel;

public class MineSweeperMenuBar extends JMenuBar {	
	
	private GameModel model;
	private final GameController controller;

	private MineSweeperMenuBar(GameModel model, GameController controller) {
		this.model = model;
		this.controller = controller;
	}
	
	public static MineSweeperMenuBar create(JFrame parent, GameModel model, GameController controller) {
		MineSweeperMenuBar menuBar = new MineSweeperMenuBar(model, controller);
		final JMenu gameMenu = new JMenu("Game");
	    ButtonGroup buttonGroup = new ButtonGroup();
	    
	    for (MineFieldDifficulty difficulty : MineFieldDifficulty.values()) {
	    	gameMenu.add(menuBar.createButtonGroupItem(buttonGroup, difficulty));
	    }
		gameMenu.addSeparator();
		
		gameMenu.add(menuBar.createScoreItem(parent));
		
		menuBar.add(gameMenu);
		return menuBar;
	}

	private JMenuItem createScoreItem(final JFrame parent) {
		JMenuItem bestScoreItem = new JMenuItem("Best Scores");
		bestScoreItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BestScoresDialog(parent, model.getBestScoreModels());
			}
		});
		return bestScoreItem;
	}

	private JRadioButtonMenuItem createButtonGroupItem(ButtonGroup group, final MineFieldDifficulty difficulty) {
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(difficulty.getName());
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.newGame(MineFieldModel.create(difficulty));
			}
		});
		if (model.getMineFieldModel().getDifficulty() == difficulty) {
			item.setSelected(true);
		}
		group.add(item);
		return item;
	}
}
