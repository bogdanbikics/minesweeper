package minesweeper.minefield.flagmonitor;

import lombok.AllArgsConstructor;
import minesweeper.controller.listener.Event;

@AllArgsConstructor
public class FlagMonitorEvent implements Event {

	private final FlagMonitorModel flagMonitorModel;

	@Override
	public FlagMonitorModel getSourceModel() {
		return flagMonitorModel;
	}
}
