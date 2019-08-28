package minesweeper.controller.listener;

import lombok.AllArgsConstructor;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldListener;
import minesweeper.minefield.flagmonitor.FlagMonitorController;

@AllArgsConstructor
public class FlagChangeListener implements MineFieldListener {
	private final FlagMonitorController controller;
	
	@Override
	public void changed(MineFieldEvent event) {
		MineFieldCellModel model = event.getSourceModel();
		if (!isChecked(model) && !isExploded(model)) {
			countFlags(model);
		}
	}

	private void countFlags(MineFieldCellModel model) {
		if (isFlagged(model)) {
			controller.decreaseLeftFlags();
			return;
		}
		controller.increaseLeftFlags();
	}

	private boolean isFlagged(MineFieldCellModel model) {
		return model.getStatus() == MineFieldCellStatus.FLAGGED;
	}
	
	private boolean isExploded(MineFieldCellModel model) {
		return model.getStatus() == MineFieldCellStatus.EXPLODED;
	}
	
	private boolean isChecked(MineFieldCellModel model) {
		return model.getStatus() == MineFieldCellStatus.CHECKED;
	}
}
