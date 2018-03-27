import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class acts as the actual display that is seen when the program is
 * run. This class controls the listeners that listen to the user's mouse
 * clicks. It also controls the game loop when the game is played.
 */
public class GUIDriver extends JFrame {

    private Dimension screenSize;

    private MapCanvas mapCanvas;
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JLabel speedLabel;
    private JSlider speed;
    private JLabel cellSizeLabel;
    private JSpinner cellSize;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu gameMenu;
    private JMenuItem exitGame;
    private JMenuItem randomGame;

    private GameDriver gameDriver;
    private int playSpeed;
    private boolean play;
    private boolean started;

    /**
     * Constructor for the {@link GUIDriver} object.
     */
    GUIDriver(){
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(this.screenSize);
        setSize(this.screenSize);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Add all components
        this.mapCanvas = new MapCanvas(this.screenSize.width - 219, this.screenSize.height - 219, 10, this);
        this.gameDriver = new GameDriver();
        this.mapCanvas.centerAnt();

        this.start = new JButton("Start");
        this.stop = new JButton("Stop");
        this.reset = new JButton("Restart");
        this.speedLabel = new JLabel("Speed", SwingConstants.CENTER);
        this.speed = new JSlider(0, 50, 5);
        this.cellSizeLabel = new JLabel("Cell Size", SwingConstants.CENTER);
        SpinnerNumberModel snm = new SpinnerNumberModel(10, 2, 100, 1);
        this.cellSize = new JSpinner(snm);

        this.menuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.gameMenu = new JMenu("Game");
        this.exitGame = new JMenuItem("Exit");
        this.randomGame = new JMenuItem("Random Game");

        this.play = false;
        this.playSpeed = 1;

        // True if the game has already been started, reset upon resetting
        this.started = false;

        this.addListeners();
        this.initJFrame();
    }

    /**
     * Adds the listeners to the corresponding GUI object.
     */
    private void addListeners() {
        this.start.addActionListener(new MapButtonListener(this, (byte)0));
        this.stop.addActionListener(new MapButtonListener(this, (byte)1));
        this.reset.addActionListener(new MapButtonListener(this, (byte)2));

        this.speed.addChangeListener(new SpeedChangeListener(this));

        this.cellSize.addChangeListener(new CellSizeChangeListener(this));

        this.exitGame.addActionListener(new MenuButtonListener(this, (byte)0));
        this.randomGame.addActionListener(new MenuButtonListener(this, (byte)1));
    }

    /**
     * Adds all the elements to the GUI and sets the size of everything.
     */
    private void initJFrame() {
        setLayout(new GridBagLayout());
        setPreferredSize(this.screenSize);

        Dimension buttonSize = new Dimension(200, 130);

        this.fileMenu.add(this.exitGame);

        this.gameMenu.add(this.randomGame);

        this.menuBar.add(this.fileMenu);
        this.menuBar.add(this.gameMenu);

        setJMenuBar(this.menuBar);

        this.start.setPreferredSize(buttonSize);
        this.stop.setPreferredSize(buttonSize);
        this.reset.setPreferredSize(buttonSize);

        this.speedLabel.setPreferredSize(new Dimension(200, 20));
        this.speed.setPreferredSize(new Dimension(200, 60));
        this.speed.setPaintTicks(true);
        this.speed.setPaintLabels(true);
        this.speed.setPaintTrack(true);
        this.speed.setSnapToTicks(true);
        this.speed.setMajorTickSpacing(10);
        this.speed.setMinorTickSpacing(5);

        this.cellSizeLabel.setPreferredSize(new Dimension(200, 20));
        this.cellSize.setPreferredSize(new Dimension(200, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.start, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.stop, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,3,3);
        add(this.reset, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 25;
        gbc.insets = new Insets(3,3,0,3);
        add(this.speedLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.ipady = 25;
        gbc.insets = new Insets(0,3,3,3);
        add(this.speed, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(3,3,0,3);
        add(this.cellSizeLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,3,3,3);
        add(this.cellSize, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.gridheight = 7;
        add(this.mapCanvas, gbc);
    }

    /**
     * Take a single step through the simulation.
     */
    private void stepSimulation() {
        this.gameDriver.step();
        ArrayList<Cell> stepBlackCells = this.gameDriver.getGameMap().getBlackCells();
        this.mapCanvas.blackCells.clear();
        // Convert all cells in the gameDriver to cells in the mapCanvas
        for(Cell blackCell : stepBlackCells) {
            this.mapCanvas.blackCells.add(new Point(blackCell.xPos * this.mapCanvas.cellLength, blackCell.yPos * this.mapCanvas.cellLength));
        }
        this.mapCanvas.repaint();
        try {
            // Wait for a period of time depending on the playSpeed
            Thread.sleep(500 / this.playSpeed);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the map and continually steps through the simulation.
     */
    public void startSimulation() {
        this.mapCanvas.setupMap();
        this.play = true;
        this.started = true;
        while(this.play) {
            stepSimulation();
        }
    }

    /**
     * If the simulation was already started, this just continues the
     * simulation without resetting the map.
     */
    public void continueSimulation() {
        while(this.play) {
            stepSimulation();
        }
    }

    /**
     * Resets the {@link GUIDriver} and the {@link MapCanvas}.
     */
    public void resetGUI() {
        this.gameDriver = new GameDriver();
        this.mapCanvas.resetMapCanvas();
        this.started = false;
        this.mapCanvas.repaint();
        this.play = false;
    }

    // Getters

    /**
     * Returns the {@link GameDriver} object.
     * @return  the {@link GameDriver}
     */
    public GameDriver getGameDriver() {
        return this.gameDriver;
    }

    /**
     * Returns whether the game is currently running.
     * @return  the play boolean.
     */
    public boolean getPlay() {
        return this.play;
    }

    /**
     * Returns whether the game has already been started.
     * @return  the started member.
     */
    public boolean getStarted() {
        return this.started;
    }

    /**
     * Returns the {@link MapCanvas} object.
     * @return  the {@link MapCanvas}
     */
    public MapCanvas getMapCanvas() {
        return this.mapCanvas;
    }

    /**
     * Returns the current value of the speed {@link JSlider}
     * @return  the value of the speed slider.
     */
    public int getSpeed() {
        return this.speed.getValue();
    }

    /**
     * Returns the current value of the cell size {@link JSpinner}
     * @return  the value of the cell size spinner.
     */
    public int getCellLength() {
        return (int)this.cellSize.getValue();
    }

    // Setters

    /**
     * Sets the {@link GameDriver} to another {@link GameDriver}.
     * @param gameDriver  the {@link GameDriver} to set gameDriver to.
     */
    public void setGameDriver(GameDriver gameDriver) {
        this.gameDriver = gameDriver;
    }

    /**
     * Change the value of play.
     * @param play  The value of play; true or false.
     */
    public void setPlay(boolean play) {
        this.play = play;
    }

    /**
     * Set the speed of the game. If speed equals 0, stop the game.
     * @param speed  the speed to set the game to.
     */
    public void setSpeed(int speed) {
        if(speed == 0) {
            this.play = false;
        }
        else {
            this.playSpeed = speed;
        }
    }
}
