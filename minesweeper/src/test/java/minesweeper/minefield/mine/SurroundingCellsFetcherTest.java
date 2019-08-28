package minesweeper.minefield.mine;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.mine.SurroundingCellsFetcher;

import org.junit.Before;
import org.junit.Test;

public class SurroundingCellsFetcherTest {
	
	private MineFieldModel mineFieldModel;
	private SurroundingCellsFetcher surroundingCellsChecker;

	@Before
	public void init() {
		mineFieldModel = MineFieldModel.create(10, 10, 1);
		surroundingCellsChecker = new SurroundingCellsFetcher(mineFieldModel);
	}
	
	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValue() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 1));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 0)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 2)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 0)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 2)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(2, 0)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(2, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(2, 2)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}
	
	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValueAtLeftEdge() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 2));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 3)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 2)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 3)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}
	
	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValueAtRightEdge() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(9, 2));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(9, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(9, 3)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(8, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(8, 2)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(8, 3)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}
	
	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValueAtToptEdge() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(5, 0));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(4, 0)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(6, 0)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(4, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(5, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(6, 1)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}
	
	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValueAtBottomEdge() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(5, 9));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(4, 9)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(6, 9)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(4, 8)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(5, 8)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(6, 8)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}
	
	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValueAtRightBottomCorner() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(9, 9));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(8, 8)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(8, 9)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(9, 8)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}

	@Test
	public void SelectSurroundingsWhenSelectClearZeroCellWithZeroValueAtLeftTopCorner() throws Exception {
		MineFieldCellModel selectedModel = mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 0));
		List<MineFieldCellModel> surroundings = Arrays.asList(
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(0, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 1)),
				mineFieldModel.getMineFieldCellModels().getMineFieldCellModelByCoordinates(new MineFieldCoordinates(1, 0)));
		verifySelectedModelSurroundings(selectedModel, surroundings);
	}
	
	private void verifySelectedModelSurroundings(MineFieldCellModel selectedModel, List<MineFieldCellModel> surroundings) {
		List<MineFieldCellModel> cellModels = surroundingCellsChecker.fetchSurroundingCells(selectedModel.getCoordinates());
		assertTrue(cellModels.size() == surroundings.size() && surroundings.containsAll(cellModels));
	}
}
