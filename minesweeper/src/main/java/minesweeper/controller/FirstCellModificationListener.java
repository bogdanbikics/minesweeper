package minesweeper.controller;

import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldListener;
import minesweeper.minefield.timermonitor.TimerMonitorController;

public class FirstCellModificationListener implements MineFieldListener {

	private final TimerMonitorController controller;
	private boolean hasStarted = false;
	
	public FirstCellModificationListener(TimerMonitorController controller) {
		this.controller = controller;
	}
	
	@Override
	public void changed(MineFieldEvent event) {
		if (!hasStarted && !isCellExploded(event)) {
			controller.start();
			hasStarted = true;
		}		
	}

	private boolean isCellExploded(MineFieldEvent event) {
		return event.getSourceModel().getStatus() == MineFieldCellStatus.EXPLODED;
	}
}