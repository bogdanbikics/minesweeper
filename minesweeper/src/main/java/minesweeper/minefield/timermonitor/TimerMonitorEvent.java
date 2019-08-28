package minesweeper.minefield.timermonitor;

import lombok.AllArgsConstructor;
import minesweeper.controller.listener.Event;

@AllArgsConstructor
public class TimerMonitorEvent implements Event {

	private final TimerMonitorModel timerMonitorModel;
	
	@Override
	public TimerMonitorModel getSourceModel() {
		return timerMonitorModel;
	}

}
