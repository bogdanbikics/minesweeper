package minesweeper.controller.listener;

import java.util.ArrayList;
import java.util.List;

public class ListenerRegistry <L extends ControllerListener<E>, E extends Event> {
	protected final List<L> listeners = new ArrayList<L>();
	
	public void addControllerListener(L listener) {
		listeners.add(listener);
	}
	
	public void removeControllerListener(L listener) {
		listeners.remove(listener);
	}
	public void notifyListeners(E event) {
		for (ControllerListener<E> listener : listeners) {
			listener.changed(event);
		}
	}
}
