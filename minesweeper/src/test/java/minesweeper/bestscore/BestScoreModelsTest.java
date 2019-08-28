package minesweeper.bestscore;

import static org.junit.Assert.*;

import java.util.Set;

import minesweeper.bestscore.BestScoreModels.NoSuchBestScoreException;
import minesweeper.minefield.MineFieldDifficulty;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Sets;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BestScoreModelsTest {

	private static final BestScoreModel ADVANCED_SCORE_MODEL = new BestScoreModel("test2", 16, MineFieldDifficulty.ADVANCED);
	private static final BestScoreModel BEGINNER_SCORE_MODEL = new BestScoreModel("test1", 13, MineFieldDifficulty.BEGINNER);
	
	private Set<BestScoreModel> dummyBestScores;
	private BestScoreModels scoreModels;
	
	@Mock private BestScoresLoader mockLoader;
	@Captor private ArgumentCaptor<Set<BestScoreModel>> captorBestScores;
	
	@Before
	public void init() {
		dummyBestScores = Sets.newHashSet(BEGINNER_SCORE_MODEL, ADVANCED_SCORE_MODEL);
		when(mockLoader.load()).thenReturn(dummyBestScores);
		scoreModels = BestScoreModels.of(mockLoader);
	}
	
	@Test
	public void getExisting() throws Exception {
		assertEquals(ADVANCED_SCORE_MODEL, scoreModels.getBestScoreWithDifficulty(MineFieldDifficulty.ADVANCED));
	}
	
	@Test(expected = NoSuchBestScoreException.class)
	public void getNonExisting()  throws Exception {
		scoreModels.getBestScoreWithDifficulty(MineFieldDifficulty.EXPERT);
	}
	
	@Test
	public void setExisting() throws Exception {
		BestScoreModel update = new BestScoreModel("t", 1, MineFieldDifficulty.BEGINNER);
		assertEquals(BEGINNER_SCORE_MODEL, scoreModels.getBestScoreWithDifficulty(MineFieldDifficulty.BEGINNER));
		scoreModels.setBestScoreWithSameDifficulty(update);
		assertEquals(update, scoreModels.getBestScoreWithDifficulty(MineFieldDifficulty.BEGINNER));
		verify(mockLoader).save(captorBestScores.capture());
	}
	
	@Test
	public void setNonExisting() throws Exception {
		verifyExpertNotIncluded();
		BestScoreModel newScoreModel = new BestScoreModel("t", 1, MineFieldDifficulty.EXPERT);
		scoreModels.setBestScoreWithSameDifficulty(newScoreModel);
		assertEquals(newScoreModel, scoreModels.getBestScoreWithDifficulty(MineFieldDifficulty.EXPERT));
		verify(mockLoader).save(captorBestScores.capture());
	}

	private void verifyExpertNotIncluded() {
		try {
			scoreModels.getBestScoreWithDifficulty(MineFieldDifficulty.EXPERT);
			assertFalse(true);
		} catch (NoSuchBestScoreException e) {
			assertTrue(true);
		}
	}
}
