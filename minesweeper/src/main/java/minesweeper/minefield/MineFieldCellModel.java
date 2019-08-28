package minesweeper.minefield;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(of={"coordinates", "type"})
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public class MineFieldCellModel {

	private static final int MINIMUM_VALUE = 0;
	private static final int MAXIMUM_VALUE = 8;
	private static final String MINE_VALUE = "*";
	private static final String EMPTY_STRING = "";
	private static final String FLAGGED_VALUE = "F";
	
	@Getter private final MineFieldType type;
	@Getter private final MineFieldCoordinates coordinates;
	private final String value;
	@Getter @Setter private MineFieldCellStatus status  = MineFieldCellStatus.UNCHECKED;
	
	public static MineFieldCellModel createMineCell(MineFieldCoordinates coordinates) {
		return new MineFieldCellModel(MineFieldType.MINE, coordinates, MINE_VALUE);
	}
	
	public static MineFieldCellModel createClearCell(MineFieldCoordinates coordinates, Integer value) {
		checkCellValue(value);
		return new MineFieldCellModel(value == 0 ? MineFieldType.CLEAR_ZERO : MineFieldType.CLEAR, coordinates, value.toString());
	}

	private static void checkCellValue(Integer value) throws NoSuchValueForMineFieldCellException {
		if (value < MINIMUM_VALUE || value > MAXIMUM_VALUE) {
			throw new NoSuchValueForMineFieldCellException();
		}
	}

	public String getValue() {
		if (status == MineFieldCellStatus.FLAGGED) {
			return FLAGGED_VALUE;
		}
		return value.equals("0") ? EMPTY_STRING : value;
	}
	
	public static class NoSuchValueForMineFieldCellException extends RuntimeException {
		public NoSuchValueForMineFieldCellException() {
			super();
		}
	}
}