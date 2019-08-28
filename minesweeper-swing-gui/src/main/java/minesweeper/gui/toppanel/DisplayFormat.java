package minesweeper.gui.toppanel;

public class DisplayFormat {

	private static int MIN_VALUE = -99;
	private static int MAX_VALUE = 999;
	
	public static String format(int number) {
		if (number < MIN_VALUE) {
			return String.valueOf(MIN_VALUE);
		}
		else if (number > MAX_VALUE) {
			return String.valueOf(MAX_VALUE);
		}
		return formatValid(number);
	}

	private static String formatValid(int number) {
		if (number < 0) {
			return formatNegative(number);
		}
		else {
			return formatPositive(number);
		}
	}

	private static String formatNegative(int number) {
		if (number <= -10) {
			return "-" + Math.abs(number);
		}
		return "-0" + Math.abs(number);
	}

	private static String formatPositive(int number) {
		StringBuilder builder = new StringBuilder(3);
		for (int limit = 100; limit >= 10 && number < limit; limit /= 10) {
			builder.append("0");
		}
		return builder.append(number).toString();
	}

}
