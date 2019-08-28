package minesweeper.gui.minefieldpanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;

import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldType;

public class MineFieldButton extends JButton {
	
	public static final Border BORDER_SELECTED = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	public static final Border BORDER_UNSELECTED = BorderFactory.createRaisedBevelBorder();

	private static final long serialVersionUID = 1L;
	
	private final MineFieldCellModel buttonModel;

	private MineFieldButton(MineFieldCellModel buttonModel) {
		this.buttonModel = buttonModel;
	}
	
	public static MineFieldButton createUnchecked(MineFieldCellModel model, MouseListener listener) {
		MineFieldButton mineFieldButton = new MineFieldButton(model);	
		mineFieldButton.setBorder(BORDER_UNSELECTED);
		mineFieldButton.addMouseListener(listener);
		mineFieldButton.setOpaque(true);
		mineFieldButton.setBackground(Color.LIGHT_GRAY);
		return mineFieldButton;
	}
	
	public static MineFieldButton createChecked(MineFieldCellModel model) {
		MineFieldButton mineFieldButton = new MineFieldButton(model);
		if (model.getType() == MineFieldType.MINE) {
			mineFieldButton.setIcon(new ImageIcon(mineFieldButton.getClass().getResource("/mine.png")));
		}
		else {
			mineFieldButton.setForeground(chooseColorFor(model.getValue()));
			mineFieldButton.setFont(mineFieldButton.getFont().deriveFont(Font.BOLD));		
			mineFieldButton.setText(model.getValue());
		}		
		mineFieldButton.setBorder(BORDER_SELECTED);
		return mineFieldButton;
	}
	
	public static MineFieldButton createFlagged(MineFieldCellModel model, MouseListener listener) {
		MineFieldButton mineFieldButton = createUnchecked(model, listener);
		mineFieldButton.setIcon(new ImageIcon(mineFieldButton.getClass().getResource("/flag.png")));
		mineFieldButton.setOpaque(true);
		return mineFieldButton;
	}
	
	public static MineFieldButton createdExploded(MineFieldCellModel model) {
		MineFieldButton mineFieldButton = createChecked(model);
		mineFieldButton.setOpaque(true);
		mineFieldButton.setBackground(Color.RED);
		return mineFieldButton;
	}

	private static Color chooseColorFor(String value) {
		for (ButtonValueColor buttonColor : ButtonValueColor.values()) {
			if (buttonColor.getValue().equals(value)) {
				return buttonColor.getColor();
			}
		}
		return Color.BLACK;
	}

	public MineFieldCellModel getButtonModel() {
		return buttonModel;
	}
}
