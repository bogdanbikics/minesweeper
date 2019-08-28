package minesweeper.controller.listener;

import lombok.AllArgsConstructor;
import minesweeper.game.GameController;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellModelList;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldListener;
import minesweeper.minefield.MineFieldType;

@AllArgsConstructor
public class AllClearCellsCheckedListener implements MineFieldListener {
	
	private final GameController controller;
	
	@Override
	public void changed(MineFieldEvent event) {
		MineFieldCellModelList modelList = event.getMineFieldModel().getMineFieldCellModels();
		for (MineFieldCellModel model : modelList) {
			if (model.getType() != MineFieldType.MINE && 
					(model.getStatus() == MineFieldCellStatus.UNCHECKED || model.getStatus() == MineFieldCellStatus.FLAGGED)) {
				return;
			}
		}
		
		controller.gameWon();
	}
}
