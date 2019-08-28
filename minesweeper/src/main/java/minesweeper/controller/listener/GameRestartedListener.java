package minesweeper.controller.listener;

import lombok.AllArgsConstructor;
import minesweeper.game.GameEvent;
import minesweeper.game.GameListener;
import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.minefield.timermonitor.TimerMonitorController;

@AllArgsConstructor
public class GameRestartedListener implements GameListener {

	TimerMonitorController controller;
	
	@Override
	public void changed(GameEvent event) {
		GameModel gameModel = event.getSourceModel();
		if (gameModel.getGameStatus() == GameStatus.GO_ON) {
			controller.reset();
			controller.start();
		}
	}

}
