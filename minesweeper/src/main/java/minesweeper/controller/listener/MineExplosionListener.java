package minesweeper.controller.listener;

import lombok.AllArgsConstructor;
import minesweeper.game.GameController;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldListener;

@AllArgsConstructor
public class MineExplosionListener implements MineFieldListener {
	private final GameController controller;
	
	@Override
	public void changed(MineFieldEvent event) {
		MineFieldCellModel model = event.getSourceModel();
		if (model.getStatus() == MineFieldCellStatus.EXPLODED) {
			controller.gameOver();
		}
	}
}