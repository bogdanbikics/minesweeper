package minesweeper.minefield.flagmonitor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.controller.ListenableController;
import minesweeper.controller.listener.ListenerRegistry;

@AllArgsConstructor
public class FlagMonitorController implements ListenableController<FlagMonitorListener, FlagMonitorEvent>{

	private final FlagMonitorModel flagMonitorModel;
	@Getter private final ListenerRegistry<FlagMonitorListener, FlagMonitorEvent> listenerRegistry;
	
	public void decreaseLeftFlags() {
		flagMonitorModel.setFlagsLeft(flagMonitorModel.getFlagsLeft() - 1);		
		listenerRegistry.notifyListeners(new FlagMonitorEvent(flagMonitorModel));
	}
	
	public void increaseLeftFlags() {
		flagMonitorModel.setFlagsLeft(flagMonitorModel.getFlagsLeft() + 1);		
		listenerRegistry.notifyListeners(new FlagMonitorEvent(flagMonitorModel));
	}
}