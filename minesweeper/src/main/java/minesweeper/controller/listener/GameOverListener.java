package minesweeper.controller.listener;

import lombok.AllArgsConstructor;
import minesweeper.game.GameEvent;
import minesweeper.game.GameListener;
import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.minefield.MineFieldController;
import minesweeper.minefield.timermonitor.TimerMonitorController;

@AllArgsConstructor
public class GameOverListener implements GameListener {
	private final MineFieldController fieldController;
	private final TimerMonitorController timeController;
	
	@Override
	public void changed(GameEvent event) {
		if (isGameOver(event.getSourceModel())) {
			fieldController.disable();
			fieldController.selectMineCells();
			timeController.stop();
		}
	}

	private boolean isGameOver(GameModel model) {
		return model.getGameStatus() == GameStatus.GAME_OVER;
	}

}
