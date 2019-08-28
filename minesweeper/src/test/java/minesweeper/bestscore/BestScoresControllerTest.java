package minesweeper.bestscore;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import minesweeper.bestscore.BestScoreModels.NoSuchBestScoreException;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.minefield.MineFieldDifficulty;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BestScoresControllerTest {
	
	@Mock private BestScoreModels mockScoreModels;
	@Mock private ListenerRegistry<BestScoreListener, BestScoreEvent> mockListenerRegistry;
	@Captor private ArgumentCaptor<BestScoreEvent> captorEvent;
	private BestScoresController controller;

	@Before
	public void init() {
		controller = new BestScoresController(mockScoreModels, mockListenerRegistry);
	}
	
	@Test
	public void saveScore() {
		BestScoreModel bestScoreModel = new BestScoreModel("t", 1, MineFieldDifficulty.EXPERT);
		controller.saveBestScore(bestScoreModel);
		verify(mockScoreModels).setBestScoreWithSameDifficulty(bestScoreModel);
	}
	
	@Test
	public void newRecord() throws Exception {
		MineFieldDifficulty difficulty = MineFieldDifficulty.BEGINNER;
		when(mockScoreModels.getBestScoreWithDifficulty(difficulty)).thenReturn(new BestScoreModel("bb", 11, difficulty));
		controller.reportNewRecord(difficulty, 10);
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void newDifficultyRecord() throws Exception {
		MineFieldDifficulty difficulty = MineFieldDifficulty.BEGINNER;
		when(mockScoreModels.getBestScoreWithDifficulty(difficulty)).thenThrow(NoSuchBestScoreException.class);
		controller.reportNewRecord(difficulty, 10);
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
}
