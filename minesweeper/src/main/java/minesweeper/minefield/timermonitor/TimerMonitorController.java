package minesweeper.minefield.timermonitor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import minesweeper.controller.ListenableController;
import minesweeper.controller.listener.ListenerRegistry;

public class TimerMonitorController implements ListenableController<TimerMonitorListener, TimerMonitorEvent> {

	private final static int PERIOD = 1000;
	
	private final TimerMonitorModel model;
	@Getter private final ListenerRegistry<TimerMonitorListener, TimerMonitorEvent> listenerRegistry;
	private Timer timer;
	
	public TimerMonitorController(TimerMonitorModel model, ListenerRegistry<TimerMonitorListener, TimerMonitorEvent> listenerRegistry) {
		this.model = model;
		this.listenerRegistry = listenerRegistry;
		this.timer = new Timer(PERIOD, new TimerListener(model, listenerRegistry));
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		if(timer != null) {
			timer.stop();
		}
	}
	
	public void reset() {
		stop();
		model.setTime(0);
	}
	
	@AllArgsConstructor
	private static class TimerListener implements ActionListener {
		
		private final TimerMonitorModel timerMonitorModel;
		private final ListenerRegistry<TimerMonitorListener, TimerMonitorEvent> listenerRegistry;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			timerMonitorModel.setTime(timerMonitorModel.getTime() + 1);
			listenerRegistry.notifyListeners(new TimerMonitorEvent(timerMonitorModel));
		}
	}
}
