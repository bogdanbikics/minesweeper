package minesweeper.minefield.mine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import minesweeper.minefield.MineFieldCoordinates;
import minesweeper.minefield.mine.MineDetector;

import org.hamcrest.core.Is;
import org.junit.Test;

public class MineDetectorTest {

	@Test
	public void noMines() {
		List<MineFieldCoordinates> minesCoordinates = new ArrayList<MineFieldCoordinates>();
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(1, 1);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(0));
	}
	
	@Test
	public void oneMineAround() {
		List<MineFieldCoordinates> minesCoordinates = Arrays.asList(new MineFieldCoordinates(2, 1));
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(1, 1);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(1));
	}
	
	@Test
	public void twoMinesAroundHorizontal() {
		List<MineFieldCoordinates> minesCoordinates = Arrays.asList(
				new MineFieldCoordinates(1, 1), 
				new MineFieldCoordinates(3, 1));
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(2, 1);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(2));
	}
	
	@Test
	public void twoMinesAroundVertical() {
		List<MineFieldCoordinates> minesCoordinates = Arrays.asList(
				new MineFieldCoordinates(1, 1), 
				new MineFieldCoordinates(1, 3));
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(1, 2);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(2));
	}
	
	@Test
	public void sixMinesAround() {
		List<MineFieldCoordinates> minesCoordinates = Arrays.asList(
				new MineFieldCoordinates(2, 2), 
				new MineFieldCoordinates(4, 2), 
				new MineFieldCoordinates(4, 3), 
				new MineFieldCoordinates(4, 4), 
				new MineFieldCoordinates(2, 4), 
				new MineFieldCoordinates(3, 4));
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(3, 3);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(6));
	}
	
	@Test
	public void EightMinesAround() {
		List<MineFieldCoordinates> minesCoordinates = Arrays.asList(
				new MineFieldCoordinates(2, 2), 
				new MineFieldCoordinates(4, 2), 
				new MineFieldCoordinates(4, 3), 
				new MineFieldCoordinates(4, 4), 
				new MineFieldCoordinates(2, 4), 
				new MineFieldCoordinates(3, 2), 
				new MineFieldCoordinates(2, 3), 
				new MineFieldCoordinates(3, 4));
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(3, 3);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(8));
	}
	
	@Test
	public void sixMinesAroundNineMinesTotal() {
		List<MineFieldCoordinates> minesCoordinates = Arrays.asList(
				new MineFieldCoordinates(2, 2), 
				new MineFieldCoordinates(4, 2), 
				new MineFieldCoordinates(4, 3), 
				new MineFieldCoordinates(4, 4), 
				new MineFieldCoordinates(2, 4), 
				new MineFieldCoordinates(6, 6), 
				new MineFieldCoordinates(6, 7), 
				new MineFieldCoordinates(8, 8), 
				new MineFieldCoordinates(3, 4));
		MineFieldCoordinates buttonCoordinates = new MineFieldCoordinates(3, 3);
		assertThat(new MineDetector(minesCoordinates).detect(buttonCoordinates), Is.is(6));
	}
}
