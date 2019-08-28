package minesweeper.game;

import lombok.Getter;
import lombok.Setter;
import minesweeper.bestscore.BestScoreModels;
import minesweeper.bestscore.loader.json.BestScoreJsonLoader;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.flagmonitor.FlagMonitorModel;
import minesweeper.minefield.timermonitor.TimerMonitorModel;

public class GameModel {
	@Getter @Setter private GameStatus gameStatus;
	@Getter @Setter private MineFieldModel mineFieldModel;
	@Getter @Setter private FlagMonitorModel flagMonitorModel;
	@Getter @Setter private TimerMonitorModel timerMonitorModel;
	@Getter @Setter private BestScoreModels bestScoreModels;
	
	public GameModel(MineFieldModel mineFieldModel) {
		this.mineFieldModel = mineFieldModel;
		this.flagMonitorModel = new FlagMonitorModel(mineFieldModel.getMines());
		this.timerMonitorModel = new TimerMonitorModel();
		this.gameStatus = GameStatus.GO_ON;
		this.bestScoreModels = BestScoreModels.of(new BestScoreJsonLoader("bestscores.json"));
	}
}