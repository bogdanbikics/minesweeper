package minesweeper.minefield;

import java.util.List;

import lombok.Getter;
import minesweeper.minefield.MineFieldDifficulty.NoSuchDifficultyException;
import minesweeper.minefield.mine.MineDeployer;
import minesweeper.minefield.mine.MineDetector;

public class MineFieldModel {
	@Getter private final int width;
	@Getter private final int height;
	@Getter private final List<MineFieldCoordinates> mineCoordinates;
	@Getter private final MineFieldCellModelList mineFieldCellModels;
	private boolean disabled;
	
	public MineFieldModel (int width, int height, List<MineFieldCoordinates> mineCoordinates) {
		this.width = width;
		this.height = height;
		this.mineCoordinates = mineCoordinates;
		this.mineFieldCellModels = MineFieldCellModelList.createMineFieldCellModels(this, new MineDetector(mineCoordinates));
		this.disabled = false;
	}
	
	public static MineFieldModel create(int width, int height, int mines) {
		return new MineFieldModel(width, height, MineDeployer.createMineCoordinates(width, height, mines));
	}
	
	public static MineFieldModel create(MineFieldDifficulty difficulty) {
		return create(
				difficulty.getWidth(), 
				difficulty.getHeight(), 
				difficulty.getMines());
	}
	
	public int getMines() {
		return mineCoordinates.size();
	}

	public MineFieldDifficulty getDifficulty() {
		for (MineFieldDifficulty difficulty : MineFieldDifficulty.values()) {
			if (difficulty.getWidth() == width && 
				difficulty.getHeight() == height && 
				difficulty.getMines() == getMines()) {
				return difficulty;
			}
		}
		throw new NoSuchDifficultyException();
	}
	
	public boolean isDisabled() {
		return disabled;
	}

	public void disable() {
		this.disabled = true;
	}
}