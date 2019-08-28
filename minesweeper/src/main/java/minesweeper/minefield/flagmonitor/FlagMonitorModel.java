package minesweeper.minefield.flagmonitor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class FlagMonitorModel {
	@Getter @Setter private int flagsLeft;
}