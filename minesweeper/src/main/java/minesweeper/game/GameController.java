package minesweeper.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.controller.ListenableController;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.flagmonitor.FlagMonitorModel;
import minesweeper.minefield.timermonitor.TimerMonitorModel;

@AllArgsConstructor
public class GameController implements ListenableController<GameListener, GameEvent>{
	private final GameModel gameModel;
	@Getter private final ListenerRegistry<GameListener, GameEvent> listenerRegistry;
	
	public void newGame(MineFieldModel mineFieldModel) {
		resetGameModel(mineFieldModel);
		listenerRegistry.notifyListeners(new GameEvent(gameModel));
	}

	public void resetGame() {
		MineFieldModel mineFieldModel = gameModel.getMineFieldModel();
		resetGameModel(mineFieldModel);		
		listenerRegistry.notifyListeners(new GameEvent(gameModel));
	}

	private void resetGameModel(MineFieldModel mineFieldModel) {
		gameModel.setMineFieldModel(resetMineFieldModel(mineFieldModel));
		gameModel.setFlagMonitorModel(new FlagMonitorModel(mineFieldModel.getMines()));
		gameModel.setTimerMonitorModel(new TimerMonitorModel());
		gameModel.setGameStatus(GameStatus.GO_ON);
	}
	
	private MineFieldModel resetMineFieldModel(MineFieldModel model) {
		return MineFieldModel.create(model.getWidth(), model.getHeight(), model.getMines());
	}
	
	public void gameOver() {
		gameModel.setGameStatus(GameStatus.GAME_OVER);
		listenerRegistry.notifyListeners(new GameEvent(gameModel));
	}
	
	
	public void gameWon() {
		gameModel.setGameStatus(GameStatus.GAME_WON);
		listenerRegistry.notifyListeners(new GameEvent(gameModel));
	}
}