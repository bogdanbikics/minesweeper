package minesweeper.bestscore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.bestscore.BestScoreModels.NoSuchBestScoreException;
import minesweeper.controller.ListenableController;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.minefield.MineFieldDifficulty;

@AllArgsConstructor
public class BestScoresController implements ListenableController<BestScoreListener, BestScoreEvent>{

	private final BestScoreModels bestScoreModels;
	@Getter private final ListenerRegistry<BestScoreListener, BestScoreEvent> listenerRegistry;

	public void reportNewRecord(MineFieldDifficulty difficulty, int time) {
		try {
			BestScoreModel bestScoreModel = bestScoreModels.getBestScoreWithDifficulty(difficulty);
			if (bestScoreModel.getTime() > time) {
				listenerRegistry.notifyListeners(new BestScoreEvent(bestScoreModel));
			}
		} catch(NoSuchBestScoreException e) {
			listenerRegistry.notifyListeners(new BestScoreEvent(new BestScoreModel("", time, difficulty)));			
		}
	}
	
	public void saveBestScore(BestScoreModel bestScoreModel) {
		bestScoreModels.setBestScoreWithSameDifficulty(bestScoreModel);
	}
}
