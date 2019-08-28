package minesweeper.controller.listener;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import minesweeper.game.GameController;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellModelList;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.MineFieldType;
import minesweeper.minefield.mine.MineDetector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class AllClearCelssCheckedListenerTest {

	@Mock private GameController mockGameController;
	@Mock private MineFieldEvent mockEvent;
	@Mock private MineFieldModel mockMineFieldModel;
	private AllClearCellsCheckedListener listener;
	
	@Before
	public void init() {
		listener = new AllClearCellsCheckedListener(mockGameController);
		when(mockEvent.getMineFieldModel()).thenReturn(mockMineFieldModel);		
	}
	
	@Test
	public void allClearChecked() {
		MineFieldCellModelList list = createClearList();
		checkClearFields(list);
		when(mockMineFieldModel.getMineFieldCellModels()).thenReturn(list);
		listener.changed(mockEvent);
		verify(mockGameController).gameWon();
	}
	
	@Test
	public void oneClearChecked() {
		MineFieldCellModelList list = createClearList();
		checkOneField(list);
		when(mockMineFieldModel.getMineFieldCellModels()).thenReturn(list);
		listener.changed(mockEvent);
		verify(mockGameController, never()).gameWon();
	}
	
	@Test
	public void allClearCheckedOneFlagged() {
		MineFieldCellModelList list = createClearList();
		checkClearFields(list);
		flagOneField(list);
		when(mockMineFieldModel.getMineFieldCellModels()).thenReturn(list);
		listener.changed(mockEvent);
		verify(mockGameController, never()).gameWon();
	}
	
	@Test
	public void AllClearCheckedOneMIneNot() {
		MineFieldCellModelList list = createOneMineList();
		checkClearFields(list);
		when(mockMineFieldModel.getMineFieldCellModels()).thenReturn(list);
		listener.changed(mockEvent);
		verify(mockGameController).gameWon();
	}

	private MineFieldCellModelList createClearList() {
		List<MineFieldCoordinates> minesCoordinates = Lists.<MineFieldCoordinates>newArrayList();
		MineFieldModel mineFieldModel = new MineFieldModel(2, 2, minesCoordinates);
		MineDetector mineDetector = new MineDetector(minesCoordinates);
		MineFieldCellModelList list = MineFieldCellModelList.createMineFieldCellModels(mineFieldModel, mineDetector);		
		return list;
	}
	
	private MineFieldCellModelList createOneMineList() {
		List<MineFieldCoordinates> minesCoordinates = Lists.<MineFieldCoordinates>newArrayList(new MineFieldCoordinates(0, 0));
		MineFieldModel mineFieldModel = new MineFieldModel(2, 2, minesCoordinates);
		MineDetector mineDetector = new MineDetector(minesCoordinates);
		MineFieldCellModelList list = MineFieldCellModelList.createMineFieldCellModels(mineFieldModel, mineDetector);		
		return list;
	}

	private void checkClearFields(MineFieldCellModelList list) {
		for (MineFieldCellModel mineFieldCellModel : list) {
			if (mineFieldCellModel.getType() != MineFieldType.MINE) {
				mineFieldCellModel.setStatus(MineFieldCellStatus.CHECKED);
			}
		}
	}
	
	private void flagOneField(MineFieldCellModelList list) {
		list.get(0).setStatus(MineFieldCellStatus.FLAGGED);
	}
	
	private void checkOneField(MineFieldCellModelList list) {
		list.get(0).setStatus(MineFieldCellStatus.CHECKED);
	}
}
