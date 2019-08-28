package minesweeper.bestscore;

import lombok.AllArgsConstructor;
import minesweeper.controller.listener.Event;

@AllArgsConstructor
public class BestScoreEvent implements Event {

	private final BestScoreModel bestScoreModel;
	
	@Override
	public BestScoreModel getSourceModel() {
		return bestScoreModel;
	}
	
}
