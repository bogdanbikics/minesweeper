package minesweeper.minefield;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.ArrayList;

import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldModel;

import org.junit.Test;

public class MineFieldModelTest {

	@Test
	public void sizeOneByOneHasOneMineFieldButtons() {
		MineFieldModel mineFieldModel = new MineFieldModel(1, 1, new ArrayList<MineFieldCoordinates>());
		assertThat(getMineFieldButtons(mineFieldModel), is(1));
	}
	
	@Test
	public void sizeTwoByTwoHasFourMineFieldButtons() {
		MineFieldModel mineField = new MineFieldModel(2, 2, new ArrayList<MineFieldCoordinates>());
		assertThat(getMineFieldButtons(mineField), is(4));
	}
	
	@Test
	public void sizeThreeByTwoHasSixMineFieldButtons() {
		MineFieldModel mineField = new MineFieldModel(3, 2, new ArrayList<MineFieldCoordinates>());
		assertThat(getMineFieldButtons(mineField), is(6));
	}
	
	@Test
	public void sizeFourByFiveHasSixMineFieldButtons() {
		MineFieldModel mineFieldModel = new MineFieldModel(4, 5, new ArrayList<MineFieldCoordinates>());
		assertThat(getMineFieldButtons(mineFieldModel), is(20));
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void getNonExistingMineFieldButtonByCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(11, 11));
	}
	
	@Test
	public void getExistingMineFieldButtonByCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		assertNotNull(mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(9, 9)));
	}
	
	@Test
	public void getMineFieldButtonByZeroZeroCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(0, 0);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByZeroOneCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(0, 1);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByZeroTwoCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(0, 2);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByOneZeroCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(1, 0);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByTwoZeroCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(2, 0);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByOneTwoCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(1, 2);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByTwoOneCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(2, 1);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getMineFieldButtonByTwoTwoCoordinates() throws Exception {
		MineFieldModel mineFieldModel = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		MineFieldCoordinates coordinates = new MineFieldCoordinates(2, 2);
		MineFieldCellModel cellModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(coordinates);
		assertThat(cellModel.getCoordinates(), is(coordinates));
	}
	
	@Test
	public void getDifficulty() throws Exception {
		for (MineFieldDifficulty difficulty : MineFieldDifficulty.values()) {
			MineFieldModel model = MineFieldModel.create(difficulty);
			assertThat(model.getDifficulty(), is(difficulty));
		}
	}

	private int getMineFieldButtons(MineFieldModel mineField) {
		return mineField.getMineFieldCellModels().size();
	}
}