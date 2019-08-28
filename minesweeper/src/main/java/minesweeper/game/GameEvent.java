package minesweeper.game;

import lombok.AllArgsConstructor;
import minesweeper.controller.listener.Event;

@AllArgsConstructor
public class GameEvent implements Event {
	
	private final GameModel gameModel;

	@Override
	public GameModel getSourceModel() {
		return gameModel;
	}
}
