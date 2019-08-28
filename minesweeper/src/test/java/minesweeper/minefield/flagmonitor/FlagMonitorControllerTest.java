package minesweeper.minefield.flagmonitor;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.game.GameEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FlagMonitorControllerTest {
	
	private FlagMonitorModel model;
	private FlagMonitorController controller;
	
	@Mock private ListenerRegistry<FlagMonitorListener, FlagMonitorEvent> mockListenerRegistry;
	@Captor ArgumentCaptor<GameEvent> captorEvent;

	@Before
	public void init() {
		model = new FlagMonitorModel(10);
		controller = new FlagMonitorController(model, mockListenerRegistry);
	}
	
	@Test
	public void increase() {
		assertThat(model.getFlagsLeft(), is(10));
		controller.increaseLeftFlags();
		assertThat(model.getFlagsLeft(), is(11));
	}
	
	@Test
	public void decrease() {
		assertThat(model.getFlagsLeft(), is(10));
		controller.decreaseLeftFlags();
		assertThat(model.getFlagsLeft(), is(9));
	}
	
}
