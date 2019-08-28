package minesweeper.minefield;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class MineFieldCoordinates {
	@Getter private final Integer x;
	@Getter private final Integer y;
}