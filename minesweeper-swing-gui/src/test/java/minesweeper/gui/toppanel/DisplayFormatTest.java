package minesweeper.gui.toppanel;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class DisplayFormatTest {

	@Test
	public void minusOne() {
		assertThat(DisplayFormat.format(-1), is("-01"));
	}
	
	@Test
	public void minusTwo() {
		assertThat(DisplayFormat.format(-2), is("-02"));
	}
	
	@Test
	public void minusTen() {
		assertThat(DisplayFormat.format(-10), is("-10"));
	}
	
	@Test
	public void minusNinetyNine() {
		assertThat(DisplayFormat.format(-99), is("-99"));
	}
	
	@Test
	public void zero() {
		assertThat(DisplayFormat.format(0), is("000"));
	}
	
	@Test
	public void one() {
		assertThat(DisplayFormat.format(1), is("001"));
	}
	
	@Test
	public void two() {
		assertThat(DisplayFormat.format(2), is("002"));
	}
	
	@Test
	public void ten() {
		assertThat(DisplayFormat.format(10), is("010"));
	}
	
	@Test
	public void eleven() {
		assertThat(DisplayFormat.format(11), is("011"));
	}
	
	@Test
	public void ninetynine() {
		assertThat(DisplayFormat.format(99), is("099"));
	}
	
	@Test
	public void onehundred() {
		assertThat(DisplayFormat.format(100), is("100"));
	}	
	
	@Test
	public void ninehundredninetynine() {
		assertThat(DisplayFormat.format(999), is("999"));
	}
	
	@Test
	public void smallerThanMinimum() {
		assertThat(DisplayFormat.format(-100), is("-99"));
	}
	
	@Test
	public void biggerThanMaximum() {
		assertThat(DisplayFormat.format(1000), is("999"));
	}
}
