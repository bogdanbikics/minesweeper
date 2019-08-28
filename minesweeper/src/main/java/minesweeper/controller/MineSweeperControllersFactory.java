package minesweeper.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.bestscore.BestScoreEvent;
import minesweeper.bestscore.BestScoreListener;
import minesweeper.bestscore.BestScoresController;
import minesweeper.controller.listener.AllClearCellsCheckedListener;
import minesweeper.controller.listener.FlagChangeListener;
import minesweeper.controller.listener.GameOverListener;
import minesweeper.controller.listener.GameRestartedListener;
import minesweeper.controller.listener.GameWonListener;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.controller.listener.MineExplosionListener;
import minesweeper.game.GameController;
import minesweeper.game.GameEvent;
import minesweeper.game.GameListener;
import minesweeper.game.GameModel;
import minesweeper.minefield.MineFieldController;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldListener;
import minesweeper.minefield.flagmonitor.FlagMonitorController;
import minesweeper.minefield.flagmonitor.FlagMonitorEvent;
import minesweeper.minefield.flagmonitor.FlagMonitorListener;
import minesweeper.minefield.timermonitor.TimerMonitorController;
import minesweeper.minefield.timermonitor.TimerMonitorEvent;
import minesweeper.minefield.timermonitor.TimerMonitorListener;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class MineSweeperControllersFactory {
	
	@Getter private final GameController gameController;
	@Getter private final MineFieldController mineFieldController;
	@Getter private final FlagMonitorController flagMonitorController;
	@Getter private final TimerMonitorController timerMonitorController;
	@Getter private final BestScoresController bestScoresController;
	
	public static MineSweeperControllersFactory createAllControllers(GameModel gameModel) {
		MineSweeperControllersFactory factory = new MineSweeperControllersFactory(
								new GameController(gameModel, new ListenerRegistry<GameListener, GameEvent>()), 
								new MineFieldController(gameModel.getMineFieldModel(), new ListenerRegistry<MineFieldListener, MineFieldEvent>()),
								new FlagMonitorController(gameModel.getFlagMonitorModel(), new ListenerRegistry<FlagMonitorListener, FlagMonitorEvent>()),
								new TimerMonitorController(gameModel.getTimerMonitorModel(), new ListenerRegistry<TimerMonitorListener, TimerMonitorEvent>()),
								new BestScoresController(gameModel.getBestScoreModels(), new ListenerRegistry<BestScoreListener, BestScoreEvent>()));
		factory.addListeners();
		return factory;
	}
	
	
	private void addListeners() {
		addListenersToMineField();		
		addListenerToGame();
	}

	private void addListenerToGame() {
		ListenerRegistry<GameListener, GameEvent> gameListenerRegistry = gameController.getListenerRegistry();
		gameListenerRegistry.addControllerListener(new GameOverListener(mineFieldController, timerMonitorController));
		gameListenerRegistry.addControllerListener(new GameWonListener(mineFieldController, timerMonitorController, bestScoresController));
		gameListenerRegistry.addControllerListener(new GameRestartedListener(timerMonitorController));
	}

	private void addListenersToMineField() {
		ListenerRegistry<MineFieldListener, MineFieldEvent> mineFieldListenerRegistry = mineFieldController.getListenerRegistry();
		mineFieldListenerRegistry.addControllerListener(new MineExplosionListener(gameController));
		mineFieldListenerRegistry.addControllerListener(new FlagChangeListener(flagMonitorController));
		mineFieldListenerRegistry.addControllerListener(new AllClearCellsCheckedListener(gameController));
		mineFieldListenerRegistry.addControllerListener(new FirstCellModificationListener(timerMonitorController));
	}
}
