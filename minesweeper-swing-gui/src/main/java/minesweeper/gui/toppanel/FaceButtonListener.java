package minesweeper.gui.toppanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import minesweeper.game.GameController;

public class FaceButtonListener extends MouseAdapter {
	
	private final GameController controller;

	public FaceButtonListener(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		controller.resetGame();
	}
}