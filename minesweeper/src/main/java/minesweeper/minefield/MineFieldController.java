package minesweeper.minefield;

import lombok.Getter;
import minesweeper.controller.ListenableController;
import minesweeper.controller.listener.ListenerRegistry;
import minesweeper.minefield.MineFieldCellModelList.NoSuchMineFieldModelException;
import minesweeper.minefield.mine.SurroundingCellsFetcher;

public class MineFieldController implements ListenableController<MineFieldListener, MineFieldEvent> {
	private final MineFieldModel mineFieldModel;
	private final SurroundingCellsFetcher surroundingsChecker;
	@Getter private final ListenerRegistry<MineFieldListener, MineFieldEvent> listenerRegistry;
	
	public MineFieldController(final MineFieldModel mineFieldModel, ListenerRegistry<MineFieldListener, MineFieldEvent> listenerRegistry) {
		this.mineFieldModel = mineFieldModel;
		this.listenerRegistry = listenerRegistry;
		surroundingsChecker = new SurroundingCellsFetcher(mineFieldModel);
	}
	
	public void selectCell(MineFieldCellModel mineFieldCellModel) {
		if (containedByMineFieldModel(mineFieldCellModel) && !mineFieldModel.isDisabled() && !isFlagged(mineFieldCellModel)) {
			selectAffectedCells(mineFieldCellModel);
			listenerRegistry.notifyListeners(new MineFieldEvent(mineFieldCellModel, mineFieldModel));
		}
	}
	
	private void selectAffectedCells(MineFieldCellModel model) {
		if (!(isChecked(model) || isFlagged(model))) {
			setCellStatusForCheck(model);
			if (isClearZero(model)) {
				for (MineFieldCellModel cellModel : surroundingsChecker.fetchSurroundingCells(model.getCoordinates())) {
					selectAffectedCells(cellModel);
				}
			}
		}
	}

	public void flagCell(MineFieldCellModel model) {
		if (containedByMineFieldModel(model) && !mineFieldModel.isDisabled() && !isChecked(model)) {
			setCellStatusForFlag(model);
			listenerRegistry.notifyListeners(new MineFieldEvent(model, mineFieldModel));
		}
	}

	private boolean containedByMineFieldModel(MineFieldCellModel mineFieldCellModel) {
		MineFieldCellModelList cellModels = mineFieldModel.getMineFieldCellModels();
		if (!cellModels.contains(mineFieldCellModel)) {
			throw new NoSuchMineFieldModelException();
		}
		return true;
	}
	
	public void disable() {
		mineFieldModel.disable();
	}

	public void selectMineCells() {
		for (MineFieldCellModel model : mineFieldModel.getMineFieldCellModels()) {
			if (isMine(model) && !isExploded(model) && !isFlagged(model)) {
				model.setStatus(MineFieldCellStatus.CHECKED);
			}
		}
	}

	public void flagAllMines() {
		for (MineFieldCellModel model : mineFieldModel.getMineFieldCellModels()) {
			if (isMine(model) && !isFlagged(model)) {
				flagCell(model);
			}
		}
	}
	
	private void setCellStatusForCheck(MineFieldCellModel model) {
		if (!isExploded(model) && !isFlagged(model)) {
			model.setStatus(isMine(model) ? 
					MineFieldCellStatus.EXPLODED : 
					MineFieldCellStatus.CHECKED);
		}
	}
	
	private void setCellStatusForFlag(MineFieldCellModel model) {
		MineFieldCellStatus status = isFlagged(model) ? 
						MineFieldCellStatus.UNCHECKED : 
						MineFieldCellStatus.FLAGGED;
		model.setStatus(status);
	}	
	
	private boolean isClearZero(MineFieldCellModel model) {
		return model.getType() == MineFieldType.CLEAR_ZERO;
	}

	private boolean isFlagged(MineFieldCellModel model) {
		return model.getStatus() == MineFieldCellStatus.FLAGGED;
	}

	private boolean isChecked(MineFieldCellModel model) {
		return model.getStatus() == MineFieldCellStatus.CHECKED;
	}
	
	private boolean isExploded(MineFieldCellModel model) {
		return model.getStatus() == MineFieldCellStatus.EXPLODED;
	}

	private boolean isMine(MineFieldCellModel model) {
		return model.getType() == MineFieldType.MINE;
	}
}