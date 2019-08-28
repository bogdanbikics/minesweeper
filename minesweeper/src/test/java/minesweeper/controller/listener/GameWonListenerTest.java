package minesweeper.controller.listener;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import minesweeper.bestscore.BestScoresController;
import minesweeper.game.GameEvent;
import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.minefield.MineFieldController;
import minesweeper.minefield.MineFieldDifficulty;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.timermonitor.TimerMonitorController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameWonListenerTest {
	
	private static final MineFieldModel MINEFIELD_MODEL = MineFieldModel.create(MineFieldDifficulty.BEGINNER);
	@Mock private MineFieldController mockFieldController;
	@Mock private TimerMonitorController mocktimeController;
	@Mock private BestScoresController mockBestScoreController;
	@Mock private GameEvent mockEvent;
	
	@Captor private ArgumentCaptor<MineFieldDifficulty> captorDifficulty;
	@Captor private ArgumentCaptor<Integer> captorTime;
	
	private GameWonListener listener;
	
	@Before
	public void init() {
		listener = new GameWonListener(mockFieldController, mocktimeController, mockBestScoreController);		
	}
	
	@Test
	public void win() {
		when(mockEvent.getSourceModel()).thenReturn(createWinningGameModel());
		listener.changed(mockEvent);
		verify(mockFieldController).flagAllMines();
		verify(mockFieldController).disable();
		verify(mocktimeController).stop();
		verify(mockBestScoreController).reportNewRecord(captorDifficulty.capture(), captorTime.capture());
	}
	
	@Test
	public void noWin() {
		when(mockEvent.getSourceModel()).thenReturn(new GameModel(MINEFIELD_MODEL));
		listener.changed(mockEvent);
		verify(mockFieldController, never()).flagAllMines();
		verify(mockFieldController, never()).disable();
	}

	private GameModel createWinningGameModel() {
		GameModel model = new GameModel(MINEFIELD_MODEL);
		model.setGameStatus(GameStatus.GAME_WON);
		return model;
	}

}
