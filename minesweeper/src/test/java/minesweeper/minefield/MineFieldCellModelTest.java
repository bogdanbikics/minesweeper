package minesweeper.minefield;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldType;
import minesweeper.minefield.MineFieldCellModel.NoSuchValueForMineFieldCellException;

import org.junit.Test;

public class MineFieldCellModelTest {

	private static final MineFieldCoordinates DUMMY_COORDINATES = new MineFieldCoordinates(0, 0);
	
	@Test
	public void valueIsAsteriskIfMine() {
		assertThat(MineFieldCellModel.createMineCell(DUMMY_COORDINATES).getValue(), is("*"));
	}
	
	@Test
	public void valueIsAsGivenIfClear() {
		assertThat(MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 8).getValue(), is("8"));
	}
	
	@Test
	public void typeIsClearZeroIfValueIsZero() {
		assertThat(MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 0).getType(), is(MineFieldType.CLEAR_ZERO));
	}
	
	@Test
	public void typeIsClearIfValueIsBiggerThanZero() {
		assertThat(MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 2).getType(), is(MineFieldType.CLEAR));
	}
	
	@Test
	public void typeIsMineIfIsMineCell() {
		assertThat(MineFieldCellModel.createMineCell(DUMMY_COORDINATES).getType(), is(MineFieldType.MINE));
	}
	
	@Test(expected=NoSuchValueForMineFieldCellException.class)
	public void valueIsLessThanZero() throws NoSuchValueForMineFieldCellException {
		MineFieldCellModel.createClearCell(DUMMY_COORDINATES, -2);
	}
	
	@Test(expected=NoSuchValueForMineFieldCellException.class)
	public void valueIsMoreThanEight() throws NoSuchValueForMineFieldCellException {
		MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 9);
	}
	
	@Test
	public void unCheckedWhenCreated() {
		assertThat(MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 0).getStatus(), is(MineFieldCellStatus.UNCHECKED));
		assertThat(MineFieldCellModel.createMineCell(DUMMY_COORDINATES).getStatus(), is(MineFieldCellStatus.UNCHECKED));
	}
	
	@Test
	public void ifFlaggedValueIsF() {
		MineFieldCellModel clearCell = MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 0);
		clearCell.setStatus(MineFieldCellStatus.FLAGGED);
		assertThat(clearCell.getValue(), is("F"));
	}
}