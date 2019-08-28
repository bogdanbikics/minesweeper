package minesweeper.bestscore;

import java.util.Set;

public interface BestScoresLoader {
	Set<BestScoreModel> load();
	void save(Set<BestScoreModel> bestScores);
}
