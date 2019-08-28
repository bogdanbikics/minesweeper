package minesweeper.minefield;

import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import minesweeper.minefield.mine.MineDetector;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
public class MineFieldCellModelList extends ArrayList<MineFieldCellModel> {
	
	private final MineFieldModel mineFieldModel;
	
	public static MineFieldCellModelList createMineFieldCellModels(MineFieldModel mineFieldModel, MineDetector mineDetector) {
		MineFieldCellModelList list = new MineFieldCellModelList(mineFieldModel);
		list.addAllModels(mineDetector);
		return list;
	}

	private void addAllModels(MineDetector mineDetector) {
		for (int column = 0; column < mineFieldModel.getWidth(); column++) {
			for (int row = 0; row < mineFieldModel.getHeight(); row++) {
				add(createMineFieldCellModel(mineFieldModel, mineDetector, new MineFieldCoordinates(column, row)));
			}
		}
	}

	private static MineFieldCellModel createMineFieldCellModel(MineFieldModel model, MineDetector mineDetector, MineFieldCoordinates coordinates) {
		return model.getMineCoordinates().contains(coordinates) ? 
				MineFieldCellModel.createMineCell(coordinates) : 
				MineFieldCellModel.createClearCell(coordinates, mineDetector.detect(coordinates));
	}
	
	public MineFieldCellModel getMineFieldCellModelByCoordinates(MineFieldCoordinates coordinates) {
 		return get(coordinates.getY() + (coordinates.getX() * mineFieldModel.getHeight()));
	}
	
	public static class NoSuchMineFieldModelException extends RuntimeException {
		public NoSuchMineFieldModelException() {
			super();
		}
	}
}
