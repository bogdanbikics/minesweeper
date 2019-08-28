package minesweeper.gui.minefieldpanel;

import java.awt.Color;

public enum ButtonValueColor {
	
	ONE("1") {
		@Override
		public Color getColor() {
			return ONE_COLOR;
		}
	},
	TWO("2") {
		@Override
		public Color getColor() {
			return TWO_COLOR;
		}
	},
	THREE("3") {
		@Override
		public Color getColor() {
			return THREE_COLOR;
		}
	},
	FOUR("4") {
		@Override
		public Color getColor() {
			return FOUR_COLOR;
		}
	},
	FIVE("5") {
		@Override
		public Color getColor() {
			return FIVE_COLOR;
		}
	},
	SIX("6") {
		@Override
		public Color getColor() {
			return SIX_COLOR;
		}
	},
	SEVEN("7") {
		@Override
		public Color getColor() {
			return SEVEN_COLOR;
		}
	},
	EIGHT("8") {
		@Override
		public Color getColor() {
			return EIGHT_COLOR;
		}
	};
	
	private static final Color ONE_COLOR = Color.BLUE;
	private static final Color TWO_COLOR = new Color(50, 205, 50);
	private static final Color THREE_COLOR = Color.RED;
	private static final Color FOUR_COLOR = new Color(128, 0, 128);
	private static final Color FIVE_COLOR = new Color(165, 42, 42);
	private static final Color SIX_COLOR = new Color(64, 224, 208);
	private static final Color SEVEN_COLOR = Color.BLACK;
	private static final Color EIGHT_COLOR = Color.GRAY;
	
	private final String value;

	private ButtonValueColor(String value) {
		this.value = value;
	}
	
	public abstract Color getColor();

	public String getValue() {
		return value;
	} 
}
