package minesweeper.bestscore;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.minefield.MineFieldDifficulty;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BestScoreModels{

	@Getter private final Set<BestScoreModel> bestScores;
	private final BestScoresLoader loader;
	
	public static BestScoreModels of(BestScoresLoader loader) {
		return new BestScoreModels(loader.load(), loader);
	}
	
	public BestScoreModel getBestScoreWithDifficulty(MineFieldDifficulty difficulty) throws NoSuchBestScoreException {
		for (BestScoreModel scoreModel : bestScores) {
			if (scoreModel.getDifficulty() == difficulty) {
				return scoreModel;
			}
		}
		throw new NoSuchBestScoreException();
	}
	
	public void setBestScoreWithSameDifficulty(BestScoreModel newScoreModel) {
		try {
			BestScoreModel removeModel = findRemovable(newScoreModel);		
			swapBestScore(newScoreModel, removeModel);
		} catch(NoSuchBestScoreException e) {
			bestScores.add(newScoreModel);			
		}
		loader.save(bestScores);
	}

	private void swapBestScore(BestScoreModel newScoreModel, BestScoreModel removeModel) {
		bestScores.remove(removeModel);
		bestScores.add(newScoreModel);
	}

	private BestScoreModel findRemovable(BestScoreModel newScoreModel) throws NoSuchBestScoreException {
		for (BestScoreModel scoreModel : bestScores) {
			if (scoreModel.getDifficulty() == newScoreModel.getDifficulty()) {
				return scoreModel;
			}
		}
		throw new NoSuchBestScoreException();
	}
	
	public static class NoSuchBestScoreException extends Exception {
		public NoSuchBestScoreException() {
			super();
		}
	}
}
