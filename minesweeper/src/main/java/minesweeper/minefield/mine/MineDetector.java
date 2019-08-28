package minesweeper.minefield.mine;

import java.util.List;

import lombok.AllArgsConstructor;
import minesweeper.minefield.MineFieldCoordinates;

@AllArgsConstructor
public class MineDetector {

	private final List<MineFieldCoordinates> minesCoordinates;

	public Integer detect(MineFieldCoordinates buttonCoordinates) {
		return detectAroundCoordinates(buttonCoordinates.getX(), buttonCoordinates.getY());
	}

	private Integer detectAroundCoordinates(int buttonX, int buttonY) {
		Integer scannedMines = 0;		
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				if (minesCoordinates.contains(new MineFieldCoordinates(buttonX + x, buttonY + y)))
					scannedMines++;
			}
		}
		return scannedMines;
	}
}
