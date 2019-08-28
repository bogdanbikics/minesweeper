package minesweeper.game;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.flagmonitor.FlagMonitorModel;
import minesweeper.minefield.timermonitor.TimerMonitorModel;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	private GameModel gameModel;
	private MineFieldModel mineFieldModel;
	private FlagMonitorModel flagMonitorModel;
	private TimerMonitorModel timeMonitorModel;
	private GameController controller;
	
	@Mock private ListenerRegistry<GameListener, GameEvent> mockListenerRegistry;
	@Captor ArgumentCaptor<GameEvent> captorEvent;

	@Before
	public void init() {
		mineFieldModel = MineFieldModel.create(10, 10, 5);
		flagMonitorModel = new FlagMonitorModel(mineFieldModel.getMines());
		timeMonitorModel = new TimerMonitorModel();
		gameModel = new GameModel(mineFieldModel);
		controller = new GameController(gameModel, mockListenerRegistry);		
	}
	
	@Test
	public void newGame() {
		assertThat(gameModel.getMineFieldModel(), Is.is(mineFieldModel));
		MineFieldModel otherMineFieldModel = MineFieldModel.create(5, 5, 1);
		controller.newGame(otherMineFieldModel);
		assertThat(gameModel.getMineFieldModel(), not(mineFieldModel));
		verifyMineFieldModel(otherMineFieldModel);	
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
	
	@Test
	public void resetGame() {
		controller.resetGame();
		
		verifyMineFieldModel(mineFieldModel);	
		assertThat(gameModel.getFlagMonitorModel(), not(flagMonitorModel));
		assertThat(gameModel.getTimerMonitorModel(), not(timeMonitorModel));
		assertThat(gameModel.getGameStatus(), is(GameStatus.GO_ON));
		
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}

	private void verifyMineFieldModel(MineFieldModel mineFieldModel) {
		assertThat(gameModel.getMineFieldModel(), not(mineFieldModel));
		assertThat(gameModel.getMineFieldModel().getWidth(), is(mineFieldModel.getWidth()));
		assertThat(gameModel.getMineFieldModel().getHeight(), is(mineFieldModel.getHeight()));
		assertThat(gameModel.getMineFieldModel().getMines(), is(mineFieldModel.getMines()));
		assertThat(gameModel.getMineFieldModel().getMineCoordinates(), not(mineFieldModel.getMineCoordinates()));
	}
	
	@Test
	public void gameOver() {
		assertThat(gameModel.getGameStatus(), is(GameStatus.GO_ON));
		controller.gameOver();
		assertThat(gameModel.getGameStatus(), is(GameStatus.GAME_OVER));
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
	
	@Test
	public void gameWon() {
		assertThat(gameModel.getGameStatus(), is(GameStatus.GO_ON));
		controller.gameWon();
		assertThat(gameModel.getGameStatus(), is(GameStatus.GAME_WON));
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
}
