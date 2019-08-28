package minesweeper.controller.listener;

public interface ControllerListener <E extends Event> {
	void changed(E event);
}
