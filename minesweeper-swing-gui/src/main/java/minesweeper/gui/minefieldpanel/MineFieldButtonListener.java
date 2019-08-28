package minesweeper.gui.minefieldpanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldController;

public class MineFieldButtonListener extends MouseAdapter {

	private final MineFieldController controller;
	private final GameModel gameModel;
	private boolean pressed = false;
	private MineFieldButton pressedButton = null;

	public MineFieldButtonListener(MineFieldController controller, GameModel gameModel) {
		this.controller = controller;
		this.gameModel = gameModel;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;
		pressedButton = (MineFieldButton) e.getSource();
		
		if (pressedButton.getButtonModel().getStatus() == MineFieldCellStatus.FLAGGED || SwingUtilities.isRightMouseButton(e)) {
			return;
		}
		if (gameModel.getGameStatus() != GameStatus.GAME_OVER) {			
			pressedButton.setBorder(MineFieldButton.BORDER_SELECTED);
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
		
		if (SwingUtilities.isRightMouseButton(e)) {
			controller.flagCell(pressedButton.getButtonModel());
		} 
		else {
			controller.selectCell(pressedButton.getButtonModel());
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (pressed) {
			pressedButton = (MineFieldButton) e.getSource();
			mousePressed(e);
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if (pressed) {
			pressedButton.setBorder(MineFieldButton.BORDER_UNSELECTED);
		}
	}
}
