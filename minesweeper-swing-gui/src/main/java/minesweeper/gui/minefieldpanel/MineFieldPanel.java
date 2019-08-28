package minesweeper.gui.minefieldpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellModelList;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.MineFieldType;

public class MineFieldPanel extends JPanel {

	private final MineFieldModel mineFieldModel;
	private MouseListener listener;
	private final int fieldWidth;
	private final int fieldHeight;
	
	private MineFieldPanel(MineFieldModel mineFieldModel, MouseListener listener) {
		super();
		this.mineFieldModel = mineFieldModel;
		this.fieldWidth = mineFieldModel.getWidth();
		this.fieldHeight = mineFieldModel.getHeight();
		this.listener = listener;
	}
	
	public static MineFieldPanel create(MineFieldModel mineFieldModel, MouseListener listener) {
		MineFieldPanel panel = new MineFieldPanel(mineFieldModel, listener);
		panel.setLayout(new GridLayout(panel.fieldHeight, panel.fieldWidth));
		panel.drawMineField();
		panel.setPreferredSize(panel.calculatePreferredSize());
		return panel;
	}
	
	private Dimension calculatePreferredSize() {
		int w = fieldWidth  * 20;
		int h = fieldHeight * 20;
		return new Dimension(w, h);
	}
	
	private void drawMineField() {			
		MineFieldCellModelList modelList = mineFieldModel.getMineFieldCellModels();
		for (int y = 0; y < fieldHeight; y++) {
			for (int x = 0; x < fieldWidth; x++) {
				add(createButton(modelList.getMineFieldCellModelByCoordinates(new MineFieldCoordinates(x, y))));
			}
		}	
	}

	private MineFieldButton createButton(MineFieldCellModel model) {
		switch (model.getStatus()) {
			case FLAGGED: return createFlaggedButton(model);
			case EXPLODED: return MineFieldButton.createdExploded(model);
			case CHECKED: return MineFieldButton.createChecked(model);
			default: return MineFieldButton.createUnchecked(model, listener);
		}
	}

	private MineFieldButton createFlaggedButton(MineFieldCellModel model) {
		MineFieldButton button = MineFieldButton.createFlagged(model, listener);
		if (mineFieldModel.isDisabled() && model.getType() != MineFieldType.MINE) {
				button.setBackground(Color.RED);
		}
		return button;
	}
	
	public void redrawMineField() {
		removeAll();
		drawMineField();
		revalidate();
		repaint();
	}

	public MineFieldModel getMineFieldModel() {
		return mineFieldModel;
	}
}
