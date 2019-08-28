package minesweeper.minefield.mine;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import minesweeper.minefield.MineFieldCellModel;
import minesweeper.minefield.MineFieldCellModelList;
import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.MineFieldModel;

@AllArgsConstructor
public class SurroundingCellsFetcher {
	
	private final MineFieldModel mineFieldModel;

	public List<MineFieldCellModel> fetchSurroundingCells(MineFieldCoordinates baseCoordinates) {
		List<MineFieldCellModel> surroundingCells = new ArrayList<MineFieldCellModel>();
			for (int addX = -1; addX <= 1; addX++) {
				for (int addY = -1; addY <= 1; addY++) {
					MineFieldCoordinates coordinates = getCoordinates(baseCoordinates, addX, addY);
					if (!coordinates.equals(baseCoordinates)) {
						addSurroundingCell(surroundingCells, coordinates);
					}
				}
			}
		return surroundingCells;
	}

	private void addSurroundingCell(List<MineFieldCellModel> surroundingCells, MineFieldCoordinates coordinates) {
		if (checkValidCoordinates(coordinates)) {
			MineFieldCellModelList modelList = mineFieldModel.getMineFieldCellModels();
			surroundingCells.add(modelList.getMineFieldCellModelByCoordinates(coordinates));
		}
	}
	
	private boolean checkValidCoordinates(MineFieldCoordinates coordinates) {
		boolean isInvalid = 
				coordinates.getX() >= mineFieldModel.getWidth() || 
				coordinates.getX() < 0 || 
				coordinates.getY() >= mineFieldModel.getHeight() ||
				coordinates.getY() < 0;
		return !isInvalid;
	}

	public MineFieldCoordinates getCoordinates(MineFieldCoordinates baseCoordinates, int addX, int addY) {
		return new MineFieldCoordinates(baseCoordinates.getX() + addX, baseCoordinates.getY() + addY);
	}
}
