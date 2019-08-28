package minesweeper.gui.toppanel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.gui.minefieldpanel.MineFieldButton;

public class FaceButton extends JButton {
	
	private final GameModel gameModel;

	private FaceButton(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	
	public static FaceButton create(GameModel gameModel) {
		FaceButton faceButton = new FaceButton(gameModel);
		faceButton.setIcon(new ImageIcon(faceButton.getClass().getResource(chooseImage(gameModel.getGameStatus()))));
	    faceButton.setPreferredSize(new Dimension(30, 30));
	    faceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    faceButton.setBorder(MineFieldButton.BORDER_UNSELECTED);
		return faceButton;
	}
	
	private static String chooseImage(GameStatus status) {
		for (FaceButtonImage image : FaceButtonImage.values()) {
			if (image.getStatus() == status) {
				return image.getImagePath();
			}
		}
		return FaceButtonImage.SMILEY.getImagePath();
	}
	
	public void refreshIcon() {
		setIcon(new ImageIcon(getClass().getResource(chooseImage(gameModel.getGameStatus()))));
	}
}
