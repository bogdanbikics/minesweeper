package minesweeper.controller.listener;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.flagmonitor.FlagMonitorController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlagChangeListenerTest {

	@Mock private FlagMonitorController mockController;
	@Mock private MineFieldEvent mockEvent;
	private FlagChangeListener listener;
	private MineFieldCellModel cell;

	@Before
	public void init() {
		listener = new FlagChangeListener(mockController);
		cell = MineFieldCellModel.createClearCell(new MineFieldCoordinates(0, 0), 0);		
		when(mockEvent.getSourceModel()).thenReturn(cell);
	}
	
	@Test
	public void flag() {
		cell.setStatus(MineFieldCellStatus.FLAGGED);
		listener.changed(mockEvent);
		verify(mockController).decreaseLeftFlags();
	}
	
	@Test
	public void unFlag() {
		cell.setStatus(MineFieldCellStatus.UNCHECKED);
		listener.changed(mockEvent);
		verify(mockController).increaseLeftFlags();
	}
	
	@Test
	public void checked() {
		cell.setStatus(MineFieldCellStatus.CHECKED);
		listener.changed(mockEvent);
		verify(mockController, never()).increaseLeftFlags();
	}
	
	@Test
	public void exploded() {
		cell.setStatus(MineFieldCellStatus.EXPLODED);
		listener.changed(mockEvent);
		verify(mockController, never()).increaseLeftFlags();
	}

}
