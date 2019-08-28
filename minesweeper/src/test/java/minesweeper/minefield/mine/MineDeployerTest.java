package minesweeper.minefield.mine;

import static org.junit.Assert.*;

import java.util.List;

import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.mine.MineDeployer;

import org.hamcrest.core.Is;
import org.junit.Test;

public class MineDeployerTest {

	@Test
	public void deployOneMine() {
		List<MineFieldCoordinates> mineCoordinates = MineDeployer.createMineCoordinates(10, 10, 1);
		assertThat(mineCoordinates.size(), Is.is(1));
	}
	
	@Test
	public void deployTwoMines() {
		List<MineFieldCoordinates> mineCoordinates = MineDeployer.createMineCoordinates(10, 10, 2);
		assertThat(mineCoordinates.size(), Is.is(2));
	}
	
	@Test
	public void deployFiveMines() {
		List<MineFieldCoordinates> mineCoordinates = MineDeployer.createMineCoordinates(10, 10, 5);
		assertThat(mineCoordinates.size(), Is.is(5));
	}
	
	@Test
	public void deployMaxMines() {
		List<MineFieldCoordinates> mineCoordinates = MineDeployer.createMineCoordinates(10, 10, 100);
		assertThat(mineCoordinates.size(), Is.is(100));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deployMoreThanMaxMines() {
		MineDeployer.createMineCoordinates(10, 10, 101);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deployLessThanOneMines() {
		MineDeployer.createMineCoordinates(10, 10, 0);
	}
	
	@Test
	public void thereIsNoRepetition() {
		List<MineFieldCoordinates> mineCoordinates = MineDeployer.createMineCoordinates(10, 10, 100);
		for (MineFieldCoordinates mineFieldCoordinates : mineCoordinates) {
			if (mineCoordinates.indexOf(mineFieldCoordinates) != mineCoordinates.lastIndexOf(mineFieldCoordinates)) {
				throw new AssertionError();
			}
		}
	}
}
