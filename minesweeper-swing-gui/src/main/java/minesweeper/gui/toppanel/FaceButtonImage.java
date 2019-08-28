package minesweeper.gui.toppanel;

import minesweeper.game.GameStatus;

public enum FaceButtonImage {
	
	SMILEY(GameStatus.GO_ON) {
		@Override
		public String getImagePath() {
			return IMAGE_SMILEY;
		}
	}, 
	CRYING(GameStatus.GAME_OVER) {
		@Override
		public String getImagePath() {
			return IMAGE_CRYING;
		}
	},
	COOL(GameStatus.GAME_WON) {
		@Override
		public String getImagePath() {
			return IMAGE_COOL;
		}
	};
	
	private static final String IMAGE_SMILEY = "/smiley.png";
	private static final String IMAGE_CRYING = "/lose.png";
	private static final String IMAGE_COOL = "/win.png";
	
	private final GameStatus status;
	
	private FaceButtonImage(GameStatus status) {
		this.status = status;
	}
	
	public abstract String getImagePath();

	public GameStatus getStatus() {
		return status;
	}
}
