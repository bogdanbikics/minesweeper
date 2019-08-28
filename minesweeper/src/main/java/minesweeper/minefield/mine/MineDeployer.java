package minesweeper.minefield.mine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import minesweeper.minefield.MineFieldCoordinates;

public class MineDeployer {
	
	public static List<MineFieldCoordinates> createMineCoordinates(int width, int height, int mines) {		
		if (mines > width * height || mines < 1) {
			throw new IllegalArgumentException("mines less than 1 or more than minefield's maximum!");
		}
		
		Set<MineFieldCoordinates> mineCoordinates = new HashSet<MineFieldCoordinates>();
				
		for (int minesDeployed = 0; minesDeployed < mines; ) {
			MineFieldCoordinates coordinates = new MineFieldCoordinates(randomValueOf(width), randomValueOf(height));
			if (mineCoordinates.add(coordinates)) {
				minesDeployed++;
			}
		}
		return new ArrayList<MineFieldCoordinates>(mineCoordinates);
	}
	
	private static int randomValueOf(int dimension) {
		return ((Long)Math.round(Math.random() * (dimension - 1))).intValue();
	}
}
