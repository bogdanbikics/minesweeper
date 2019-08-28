package minesweeper.gui.toppanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import minesweeper.minefield.flagmonitor.FlagMonitorModel;

public class FlagTextField extends JTextField {
	
	private final FlagMonitorModel flagMonitorModel;

	public FlagTextField(FlagMonitorModel flagMonitorModel) {
		super();
		this.flagMonitorModel = flagMonitorModel;
	}
	
	public static FlagTextField create(FlagMonitorModel flagMonitorModel) {
	    FlagTextField textField = new FlagTextField(flagMonitorModel);
	    textField.setText(modelToString(flagMonitorModel));
	    textField.setEditable(false);
	    textField.setMaximumSize(new Dimension(60, 30));
	    textField.setBorder(BorderFactory.createEmptyBorder());
	    textField.setFont(new Font("Courier New", Font.BOLD, 25));
	    textField.setBackground(Color.BLACK);
	    textField.setForeground(Color.RED);
	    return textField;
	}

	private static String modelToString(FlagMonitorModel flagMonitorModel) {
		return DisplayFormat.format(flagMonitorModel.getFlagsLeft());
	}
	
	public void refreshFlagText() {
		setText(modelToString(flagMonitorModel));
	}
}
