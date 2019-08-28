package minesweeper.controller.listener;

import lombok.AllArgsConstructor;
import minesweeper.bestscore.BestScoresController;
import minesweeper.game.GameEvent;
import minesweeper.game.GameListener;
import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.minefield.MineFieldController;
import minesweeper.minefield.timermonitor.TimerMonitorController;

@AllArgsConstructor
public class GameWonListener implements GameListener {
	private final MineFieldController fieldController;
	private final TimerMonitorController timeController;
	private final BestScoresController bestScoresController;
	
	@Override
	public void changed(GameEvent event) {
		GameModel gameModel = event.getSourceModel();
		if (isGameWon(gameModel)) {
			fieldController.flagAllMines();
			fieldController.disable();
			timeController.stop();
			
			bestScoresController.reportNewRecord(
					gameModel.getMineFieldModel().getDifficulty(), 
					gameModel.getTimerMonitorModel().getTime());
		}
	}

	private boolean isGameWon(GameModel model) {
		return model.getGameStatus() == GameStatus.GAME_WON;
	}
}