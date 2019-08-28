package minesweeper.minefield;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.minefield.MineFieldCellModelList.NoSuchMineFieldModelException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MineFieldControllerTest {
	private static final MineFieldCoordinates DUMMY_COORDINATES = new MineFieldCoordinates(0, 0);
	private static final List<MineFieldCoordinates> DUMMY_MINE_COORDINATES = Arrays.asList(
					new MineFieldCoordinates(0, 0),
					new MineFieldCoordinates(0, 2),
					new MineFieldCoordinates(2, 2));

	@Mock private MineFieldEvent mockEvent;	
	@Mock private ListenerRegistry<MineFieldListener, MineFieldEvent> mockListenerRegistry;
	@Mock private MineFieldController mockController;
	@Captor ArgumentCaptor<MineFieldEvent> captorEvent;
	
	private MineFieldModel mineFieldModelWithoutMines;		
	private MineFieldModel smallestFieldModelWithoutMine;
	private MineFieldModel mineFieldModelWithThreeMines;
	
	private MineFieldController controllerForSmallest;
	private MineFieldCellModel cellModelWithDummyCoordinates;
	private MineFieldCellModel mineCellModelWithDummyCoordinates;


	@Before
	public void init() {
		mineFieldModelWithoutMines = new MineFieldModel(10, 10, new ArrayList<MineFieldCoordinates>());
		smallestFieldModelWithoutMine = new MineFieldModel(1, 1, new ArrayList<MineFieldCoordinates>());
		controllerForSmallest = new MineFieldController(smallestFieldModelWithoutMine, mockListenerRegistry);
		cellModelWithDummyCoordinates = MineFieldCellModel.createClearCell(DUMMY_COORDINATES, 0);
		mineCellModelWithDummyCoordinates = MineFieldCellModel.createMineCell(DUMMY_COORDINATES);
		mineFieldModelWithThreeMines = new MineFieldModel(3, 3, DUMMY_MINE_COORDINATES);
	}
	
	@Test(expected = NoSuchMineFieldModelException.class)
	public void onCellSelectWhichIsNotInMineField() throws Exception {
		new MineFieldController(mineFieldModelWithoutMines, mockListenerRegistry)
			.selectCell(mineCellModelWithDummyCoordinates);
	}
	
	@Test(expected = NoSuchMineFieldModelException.class)
	public void onCellFlagWhichIsNotInMineField() throws Exception {
		new MineFieldController(mineFieldModelWithoutMines, mockListenerRegistry)
			.flagCell(mineCellModelWithDummyCoordinates);
	}
	
	@Test
	public void onSelectMineFieldCellModelisChecked() {
		assertThat(cellModelWithDummyCoordinates.getStatus(), is(MineFieldCellStatus.UNCHECKED));
		new MineFieldController(mineFieldModelWithoutMines, mockListenerRegistry).selectCell(cellModelWithDummyCoordinates);
		assertThat(cellModelWithDummyCoordinates.getStatus(), is(MineFieldCellStatus.CHECKED));
	}
	
	@Test
	public void cellCheckedWhenSelectedOnceOrMore() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.UNCHECKED));
		checkCell(cellModel);
		checkCell(cellModel);
	}

	@Test
	public void cellCheckIfFlagged() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		cellModel.setStatus(MineFieldCellStatus.FLAGGED);
		controllerForSmallest.selectCell(cellModel);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.FLAGGED));
		verify(mockListenerRegistry, never()).notifyListeners(captorEvent.capture());
	}

	private void checkCell(MineFieldCellModel cellModel) {
		controllerForSmallest.selectCell(cellModel);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.CHECKED));
	}
	
	@Test
	public void cellExplodedWhenSelectedMine() {
		MineFieldModel fieldModel = MineFieldModel.create(1, 1, 1);
		MineFieldController controller = new MineFieldController(fieldModel, mockListenerRegistry);
		MineFieldCellModel cellModel = fieldModel.getMineFieldCellModels().get(0);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.UNCHECKED));		
		controller.selectCell(cellModel);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.EXPLODED));		
	}
	
	@Test
	public void cellIsFlaggedWhenFlaggedOnce() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.UNCHECKED));
		controllerForSmallest.flagCell(cellModel);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.FLAGGED));
	}
	
	@Test
	public void notifyListenersWhenFlag() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		controllerForSmallest.flagCell(cellModel);
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
	
	@Test
	public void notifyListenersWhenSelect() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		controllerForSmallest.selectCell(cellModel);
		verify(mockListenerRegistry).notifyListeners(captorEvent.capture());
	}
	
	@Test
	public void whenDisabledDoesntNotify() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		controllerForSmallest.disable();
		flagDisabled(cellModel);
		selectDisabled(cellModel);
	}

	private void flagDisabled(MineFieldCellModel cellModel) {
		controllerForSmallest.flagCell(cellModel);
		verify(mockListenerRegistry, never()).notifyListeners(captorEvent.capture());
	}
	
	private void selectDisabled(MineFieldCellModel cellModel) {
		controllerForSmallest.selectCell(cellModel);
		verify(mockListenerRegistry, never()).notifyListeners(captorEvent.capture());
	}
	
	@Test
	public void selectAllMines() {
		MineFieldController controller = new MineFieldController(mineFieldModelWithThreeMines, mockListenerRegistry);
		controller.selectMineCells();
		assertStatus(MineFieldCellStatus.CHECKED);
	}
	
	@Test
	public void flagAllMines() {
		MineFieldController controller = new MineFieldController(mineFieldModelWithThreeMines, mockListenerRegistry);
		controller.flagAllMines();
		assertStatus(MineFieldCellStatus.FLAGGED);		
	}

	private void assertStatus(MineFieldCellStatus status) {
		for (MineFieldCellModel cell : mineFieldModelWithThreeMines.getMineFieldCellModels()) {
			if (cell.getStatus() == status) {
				assertTrue(DUMMY_MINE_COORDINATES.contains(cell.getCoordinates()));
			}
			else {
				assertFalse(DUMMY_MINE_COORDINATES.contains(cell.getCoordinates()));
			}
		}
	}
	
	@Test
	public void isUnFlaggedWhenFlaggedTwice() {
		MineFieldCellModel cellModel = smallestFieldModelWithoutMine.getMineFieldCellModels().get(0);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.UNCHECKED));
		controllerForSmallest.flagCell(cellModel);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.FLAGGED));
		controllerForSmallest.flagCell(cellModel);
		assertThat(cellModel.getStatus(), is(MineFieldCellStatus.UNCHECKED));
	}
}
