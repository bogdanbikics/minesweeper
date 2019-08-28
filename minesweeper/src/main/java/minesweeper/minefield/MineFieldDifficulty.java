package minesweeper.minefield;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MineFieldDifficulty {
	BEGINNER(9, 9, 10) {
		@Override
		public String getName() {
			return "Beginner";
		}
	}, 
	ADVANCED(16, 16, 40) {
		@Override
		public String getName() {
			return "Advanced";
		}
	}, 
	EXPERT(30, 16, 99) {
		@Override
		public String getName() {
			return "Expert";
		}
	};
	
	private final int width;
	private final int height;
	private final int mines;
	
	public abstract String getName();
	
	public static class NoSuchDifficultyException extends RuntimeException {
		public NoSuchDifficultyException() {
			super();
		}
	}
}
