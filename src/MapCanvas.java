import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class MapCanvas extends JPanel {

    private int width, height, numCols, numRows;
    int cellLength;
    ArrayList<Point> blackCells;
    GUIDriver guiDriver;

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

    public void setupMap() {
        this.guiDriver.setGameDriver(new GameDriver());
        if(!this.guiDriver.getStarted())
            this.guiDriver.getGameDriver().setAntPos(this.numCols / 2, this.numRows / 2);
        for(Point blackCell : this.blackCells) {
            this.guiDriver.getGameDriver().addBlackCell((blackCell.x / this.cellLength), (blackCell.y / this.cellLength));
            this.guiDriver.getGameDriver().getGameMap().addCell((blackCell.x / this.cellLength), (blackCell.y / this.cellLength));
        }
    }

    public void resetMapCanvas() {
        this.blackCells.clear();
        centerAnt();
    }

    public void centerAnt() {
        this.guiDriver.getGameDriver().setAntPos(this.numCols / 2, this.numRows / 2);
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

    @Override
    public void paint(Graphics g) {
        try {
            this.numCols = 0;
            this.numRows = 0;

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.width, this.height);
            g.setColor(Color.BLACK);

            // Draw columns
            int currentDrawWidth = 0;
            while (currentDrawWidth < this.width) {
                g.drawLine(currentDrawWidth, 0, currentDrawWidth, this.height);
                this.numCols++;
                currentDrawWidth += this.cellLength;
            }

            // Draw rows
            int currentDrawHeight = 0;
            while (currentDrawHeight < this.height) {
                g.drawLine(0, currentDrawHeight, this.width, currentDrawHeight);
                this.numRows++;
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
            g.fillRect(this.cellLength * this.guiDriver.getGameDriver().getAnt().xPos + 1,
                    this.cellLength * this.guiDriver.getGameDriver().getAnt().yPos + 1,
                    this.cellLength - 1,
                    this.cellLength - 1);
        }
        catch (ConcurrentModificationException e) {}
    }

    // Setters
    public void setCellLength(int cellLength) {
        this.cellLength = cellLength;
    }

    // Resetters
    public void resetNumCols() {
        this.numCols = this.width / this.cellLength;
    }

    public void resetNumRows() {
        this.numRows = this.height / this.cellLength;
    }
}
