package minesweeper.controller;

import minesweeper.controller.listener.ControllerListener;
import minesweeper.controller.listener.Event;
import minesweeper.controller.listener.ListenerRegistry;

public interface ListenableController<L extends ControllerListener<E>, E extends Event>{
	ListenerRegistry<L, E> getListenerRegistry();
}
