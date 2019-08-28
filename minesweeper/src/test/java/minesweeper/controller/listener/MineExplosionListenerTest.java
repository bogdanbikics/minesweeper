package minesweeper.controller.listener;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import minesweeper.game.GameController;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellStatus;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MineExplosionListenerTest {
	
	@Mock private GameController mockController;
	@Mock private MineFieldEvent mockEvent;
	
	@Test
	public void explode() {
		MineExplosionListener listener = new MineExplosionListener(mockController);
		when(mockEvent.getSourceModel()).thenReturn(createExplodedCell());
		listener.changed(mockEvent);
		verify(mockController).gameOver();
	}
	
	@Test
	public void nothing() {
		MineExplosionListener listener = new MineExplosionListener(mockController);
		when(mockEvent.getSourceModel()).thenReturn(createCheckedCell());
		listener.changed(mockEvent);
		verify(mockController, never()).gameOver();
	}

	private MineFieldCellModel createCheckedCell() {
		MineFieldCellModel cell = MineFieldCellModel.createClearCell(new MineFieldCoordinates(0, 0), 0);
		cell.setStatus(MineFieldCellStatus.CHECKED);
		return cell;
	}

	private MineFieldCellModel createExplodedCell() {
		MineFieldCellModel cell = MineFieldCellModel.createMineCell(new MineFieldCoordinates(0, 0));
		cell.setStatus(MineFieldCellStatus.EXPLODED);
		return cell;
	}

}
