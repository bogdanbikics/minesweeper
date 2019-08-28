package minesweeper.minefield;

import static org.junit.Assert.*;
import minesweeper.minefield.MineFieldCoordinates;

import org.junit.Test;

public class MineFieldCoordinatesTest {

	@Test
	public void sameCoordinatesEqual() {
		MineFieldCoordinates firstCoordinate = new MineFieldCoordinates(1, 1);
		MineFieldCoordinates secondCoordinate = new MineFieldCoordinates(1, 1);	
		assertEquals(firstCoordinate, secondCoordinate);
	}
}
