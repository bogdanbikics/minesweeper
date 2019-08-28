package minesweeper.minefield;

import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.controller.listener.Event;

@AllArgsConstructor
public class MineFieldEvent implements Event {

	private final MineFieldCellModel mineFieldCellModel;
	@Getter private final MineFieldModel mineFieldModel;
	
	@Override
	public MineFieldCellModel getSourceModel() {
		return mineFieldCellModel;
	}
}