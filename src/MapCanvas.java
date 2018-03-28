import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * This class acts as a canvas to draw the grid, black cells, white cells,
 * and the {@link Ant}. Controls map setup, map reset, centering the
 * {@link Ant}, and generating a random {@link Map}.
 */
public class MapCanvas extends JPanel {

    private int width, height, numCols, numRows;
    int cellLength;
    ArrayList<Point> blackCells;
    private GUIDriver guiDriver;

    /**
     * Constructor of the {@link MapCanvas} object.
     * @param width      width of the canvas
     * @param height     height of the canvas
     * @param cellLength the length in pixels of each cell
     * @param guiDriver  the {@link GUIDriver} that the {@link MapCanvas} is in
     */
    MapCanvas(int width, int height, int cellLength, GUIDriver guiDriver) {
        this.width = width;
        this.height = height;
        this.cellLength = cellLength;
        this.guiDriver = guiDriver;
        this.blackCells = new ArrayList<>();

        this.numCols = this.width / this.cellLength;
        this.numRows = this.height / this.cellLength;

        setPreferredSize(new Dimension(this.width, this.height));

        addMouseListener(new MapMouseListener(this));
    }

    /**
     * Sets the {@link Ant}'s position and adds all of the custom added black
     * cells to the {@link Map}.
     */
    public void setupMap() {
        this.guiDriver.setGameDriver(new GameDriver());
        if(!this.guiDriver.getStarted())
            this.guiDriver.getGameDriver().getAnt().setAntPos(this.numCols / 2, this.numRows / 2);
        // For each black cell in the MapCanvas, add them to the GameDriver
        for(Point blackCell : this.blackCells) {
            this.guiDriver.getGameDriver().getGameMap().addCell((blackCell.x / this.cellLength), (blackCell.y / this.cellLength), true);
        }
    }

    /**
     * Clears the canvas and centers the {@link Ant}.
     */
    public void resetMapCanvas() {
        this.blackCells.clear();
        centerAnt();
    }

    /**
     * Centers the {@link Ant} in the {@link MapCanvas}.
     */
    public void centerAnt() {
        this.guiDriver.getGameDriver().getAnt().setAntPos(this.numCols / 2, this.numRows / 2);
    }

    public void generateRandomMap() {
        this.blackCells.clear();
        for(int i = this.numCols / 10; i < this.numCols * 9 / 10; i++) {
            for(int j = this.numRows / 10; j < this.numRows * 9 / 10; j++) {
                if(Math.random() > 0.7) {
                    int xVal = i * this.cellLength;
                    int yVal = j * this.cellLength;
                    this.blackCells.add(new Point(xVal, yVal));
                }
            }
        }
        this.repaint();
    }

    /**
     * Paints the white background, the grid, black cells, and the {@link Ant}.
     * @param g  Not used explicitly.
     */
    @Override
    public void paint(Graphics g) {
        try {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.width, this.height);
            g.setColor(Color.BLACK);

            // Draw columns
            int currentDrawWidth = 0;
            while (currentDrawWidth < this.width) {
                g.drawLine(currentDrawWidth, 0, currentDrawWidth, this.height);
                currentDrawWidth += this.cellLength;
            }

            // Draw rows
            int currentDrawHeight = 0;
            while (currentDrawHeight < this.height) {
                g.drawLine(0, currentDrawHeight, this.width, currentDrawHeight);
                currentDrawHeight += this.cellLength;
            }

            // Draw Black Cells
            for (Point blackCell : this.blackCells) {
                g.setColor(Color.BLACK);
                g.fillRect((int) blackCell.getX(), (int) blackCell.getY(), this.cellLength, this.cellLength);
                if (this.cellLength >= 7) {
                    g.setColor(Color.WHITE);
                    g.drawRect((int) blackCell.getX() + 1, (int) blackCell.getY() + 1, this.cellLength - 2, this.cellLength - 2);
                }
            }

            // Draw Ant
            g.setColor(new Color(0, 255, 0));
            g.fillRect(this.cellLength * this.guiDriver.getGameDriver().getAnt().getXPos() + 1,
                    this.cellLength * this.guiDriver.getGameDriver().getAnt().getYPos() + 1,
                    this.cellLength - 1,
                    this.cellLength - 1);
        }
        catch (ConcurrentModificationException e) {}
    }

    // Setters

    /**
     * Sets the {@link MapCanvas}'s cell length to the given length.
     * @param cellLength  width to set the cell length to.
     */
    public void setCellLength(int cellLength) {
        this.cellLength = cellLength;
    }

    // Resetters

    /**
     * Reset the number of columns in case the width and/or height change.
     */
    public void resetNumCols() {
        this.numCols = this.width / this.cellLength;
    }

    /**
     * Reset the number of rows in case the width and/or height change.
     */
    public void resetNumRows() {
        this.numRows = this.height / this.cellLength;
    }
}
