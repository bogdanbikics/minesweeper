package minesweeper.bestscore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import minesweeper.minefield.MineFieldDifficulty;

@AllArgsConstructor
@EqualsAndHashCode(exclude = "time")
@Getter
public class BestScoreModel {
	private final String name;
	private final int time;
	private final MineFieldDifficulty difficulty;
}
