package minesweeper.gui.toppanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import minesweeper.minefield.timermonitor.TimerMonitorModel;

public class TimeTextField extends JTextField {
	
	private final TimerMonitorModel timeMonitorModel;

	public TimeTextField(TimerMonitorModel timeMonitorModel) {
		super();
		this.timeMonitorModel = timeMonitorModel;
	}
	
	public static TimeTextField create(TimerMonitorModel timeMonitorModel) {
	    TimeTextField textField = new TimeTextField(timeMonitorModel);
	    textField.setText(modelToString(timeMonitorModel));
	    textField.setEditable(false);
	    textField.setMaximumSize(new Dimension(60, 30));
	    textField.setBorder(BorderFactory.createEmptyBorder());
	    textField.setFont(new Font("Courier New", Font.BOLD, 25));
	    textField.setBackground(Color.BLACK);
	    textField.setForeground(Color.RED);
	    return textField;
	}

	private static String modelToString(TimerMonitorModel model) {
		return DisplayFormat.format(model.getTime());
	}
	
	public void refreshTimeText() {
		setText(modelToString(timeMonitorModel));
	}
}
