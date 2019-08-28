package minesweeper.controller.listener;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import minesweeper.game.GameEvent;
import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.minefield.MineFieldController;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.timermonitor.TimerMonitorController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameOverListenerTest {

	@Mock private MineFieldController mockFieldController;
	@Mock private TimerMonitorController mockTimerController;
	@Mock private GameEvent mockEvent;
	@Mock private MineFieldModel mockMineFieldModel;
	private GameOverListener listener;
	
	@Before
	public void init() {
		listener = new GameOverListener(mockFieldController, mockTimerController);		
	}
	
	@Test
	public void lost() {
		eventFor(GameStatus.GAME_OVER);
		listener.changed(mockEvent);
		verify(mockFieldController).disable();
		verify(mockFieldController).selectMineCells();
		verify(mockTimerController).stop();
	}
	
	@Test
	public void notLost() {
		eventFor(GameStatus.GO_ON);
		listener.changed(mockEvent);
		verify(mockFieldController, never()).disable();
		verify(mockFieldController, never()).selectMineCells();;
	}

	private void eventFor(GameStatus status) {
		GameModel gameModel = new GameModel(mockMineFieldModel);
		gameModel.setGameStatus(status);
		when(mockEvent.getSourceModel()).thenReturn(gameModel);
	}

}
