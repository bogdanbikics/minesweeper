package minesweeper.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import minesweeper.bestscore.BestScoreEvent;
import minesweeper.bestscore.BestScoreListener;
import minesweeper.bestscore.BestScoreModel;
import minesweeper.bestscore.BestScoresController;
import minesweeper.controller.MineSweeperControllersFactory;
import minesweeper.game.GameController;
import minesweeper.game.GameEvent;
import minesweeper.game.GameListener;
import minesweeper.game.GameModel;
import minesweeper.game.GameStatus;
import minesweeper.gui.minefieldpanel.MineFieldButtonListener;
import minesweeper.gui.minefieldpanel.MineFieldPanel;
import minesweeper.gui.toppanel.FaceButton;
import minesweeper.gui.toppanel.FaceButtonListener;
import minesweeper.gui.toppanel.FlagTextField;
import minesweeper.gui.toppanel.TimeTextField;
import minesweeper.gui.toppanel.TopPanel;
import minesweeper.minefield.MineFieldController;
import minesweeper.minefield.MineFieldEvent;
import minesweeper.minefield.MineFieldListener;
import minesweeper.minefield.MineFieldModel;
import minesweeper.minefield.flagmonitor.FlagMonitorController;
import minesweeper.minefield.flagmonitor.FlagMonitorEvent;
import minesweeper.minefield.flagmonitor.FlagMonitorListener;
import minesweeper.minefield.timermonitor.TimerMonitorController;
import minesweeper.minefield.timermonitor.TimerMonitorEvent;
import minesweeper.minefield.timermonitor.TimerMonitorListener;

public class MineSweeperFrame extends JFrame {
	
	private final GameModel gameModel;
	
	private GameController gameController;
	private MineFieldController mineFieldController;
	private FlagMonitorController flagMonitorController;
	private TimerMonitorController timerMonitorController;
	private BestScoresController bestScoreController;

	private FaceButton faceButton;
	private FlagTextField flagTextField;
	private TimeTextField timeTextField;
	private MineFieldPanel mineFieldPanel;


	private  MineSweeperFrame(GameModel gameModel) {
		super("MineSweeper");
		this.gameModel = gameModel;
	    setupControllers(gameModel);
	}
	
	public static MineSweeperFrame create() {
		MineSweeperFrame frame = new MineSweeperFrame(new GameModel(MineFieldModel.create(9, 9, 10)));
		frame.setIconImage(new ImageIcon(frame.getClass().getResource("/mine.png")).getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.draw();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2);
		frame.setVisible(true);
		return frame;
	}
	
	private void setupControllers(GameModel gameModel) {
	    MineSweeperControllersFactory controllerFactory = MineSweeperControllersFactory.createAllControllers(gameModel);
		gameController = controllerFactory.getGameController();
		mineFieldController = controllerFactory.getMineFieldController();
		flagMonitorController = controllerFactory.getFlagMonitorController();
		timerMonitorController = controllerFactory.getTimerMonitorController();
		bestScoreController = controllerFactory.getBestScoresController();
		
		new ListenerHandler().addListenersToControllers();		
	}

	private void draw() {
		JPanel contentPane = (JPanel)getContentPane();
	    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane.setBackground(Color.LIGHT_GRAY);
	    	    
	    faceButton = FaceButton.create(gameModel);
	    faceButton.addMouseListener(new FaceButtonListener(gameController));
	    
	    flagTextField = FlagTextField.create(gameModel.getFlagMonitorModel());
	    timeTextField = TimeTextField.create(gameModel.getTimerMonitorModel());
	    TopPanel topPanel = TopPanel.create(faceButton, flagTextField, timeTextField);
	    
	    contentPane.add(topPanel);
	    contentPane.add(Box.createVerticalStrut(5));
	    contentPane.add(buildMineFieldPanel());
	    
	    setJMenuBar(MineSweeperMenuBar.create(this, gameModel, gameController));
	    pack();
	}

	private JPanel buildMineFieldPanel() {
		JPanel mineFieldBorderPanel = new JPanel();
	    mineFieldPanel = MineFieldPanel.create(gameModel.getMineFieldModel(), new MineFieldButtonListener(mineFieldController, gameModel));
	    mineFieldBorderPanel.setBorder(BorderFactory.createLoweredBevelBorder());
	    mineFieldBorderPanel.setLayout(new BorderLayout());
	    mineFieldBorderPanel.add(mineFieldPanel, BorderLayout.CENTER);
		return mineFieldBorderPanel;
	}
	
	public void redraw() {
		getContentPane().removeAll();
		setupControllers(gameModel);
		draw();
	}
	
	private void showBestScoreDialog() {
		String name = JOptionPane.showInputDialog(
				this, 
				"You have the best time in this difficulty.\nPlease enter name!", 
				"New Best Score",
				JOptionPane.PLAIN_MESSAGE);
		bestScoreController.saveBestScore(new BestScoreModel(name, gameModel.getTimerMonitorModel().getTime(), gameModel.getMineFieldModel().getDifficulty()));
	}

	private class ListenerHandler {
		
		public void addListenersToControllers() {
			toGameController();
			toMineFieldController();
			toFlagMonitorController();
			toTimerMonitorController();
			toBestScoreController();
		}

		private void toGameController() {
			gameController.getListenerRegistry().addControllerListener(
					new GameListener() {
						@Override
						public void changed(GameEvent event) {
							if (event.getSourceModel().getGameStatus() == GameStatus.GO_ON) {
								redraw();
							}
						}
					});
			gameController.getListenerRegistry().addControllerListener(
					new GameListener() {
						@Override
						public void changed(GameEvent event) {
							faceButton.refreshIcon();
						}
					});
		}

		private void toMineFieldController() {
			mineFieldController.getListenerRegistry().addControllerListener(
					new MineFieldListener() {
						@Override
						public void changed(MineFieldEvent event) {
							mineFieldPanel.redrawMineField();
						}
					});
		}

		private void toFlagMonitorController() {
			flagMonitorController.getListenerRegistry().addControllerListener(
					new FlagMonitorListener() {
						@Override
						public void changed(FlagMonitorEvent event) {
							flagTextField.refreshFlagText();
						}
					});
		}

		private void toTimerMonitorController() {
			timerMonitorController.getListenerRegistry().addControllerListener(
					new TimerMonitorListener() {
						@Override
						public void changed(TimerMonitorEvent event) {
							timeTextField.refreshTimeText();
						}
					});
		}

		private void toBestScoreController() {
			bestScoreController.getListenerRegistry().addControllerListener(
					new BestScoreListener() {
						@Override
						public void changed(BestScoreEvent event) {
							mineFieldPanel.redrawMineField();
							faceButton.refreshIcon();
							showBestScoreDialog();
						}
					});
		}
	}
}