package minesweeper.gui.main;

import javax.swing.SwingUtilities;

import minesweeper.gui.frame.MineSweeperFrame;

public class Main {
	
	public static void main(String[] args) {
		macProperties();
		SwingUtilities.invokeLater(new Runnable() {			
			@Override
			public void run() {
				MineSweeperFrame.create();
			}
		});
	}
	
	private static void macProperties() {
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Minesweeper");
			System.setProperty("apple.laf.useScreenMenuBar", "true");
		}
	}
}