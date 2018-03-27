import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is where every {@link Cell} and the {@link Ant} object lives.
 * The {@link Map} object keeps track of the black {@link Cell}s and the
 * white {@link Cell}s. The {@link Map} object has the ability to
 * manipulate the various {@link Cell}s located inside it.
 */
public class Map {

    private HashMap<Integer, HashMap<Integer, Cell>> map;
    private ArrayList<Cell> blackCells, whiteCells;

    /**
     * The constructor for the {@link Map} object
     */
    Map() {
        this.map = new HashMap<>();
        this.blackCells = new ArrayList<>();
        this.whiteCells = new ArrayList<>();
    }

    /**
     * Adds a new white {@link Cell} to the {@link Map} only if it is not
     * present in the {@link HashMap}.
     * @param xPos  x-position of the {@link Cell} to add.
     * @param yPos  y-position of the {@link Cell} to add.
     */
    void addCell(int xPos, int yPos) {
        if(getCell(xPos, yPos) == null) {
            this.map.computeIfAbsent(xPos, k -> new HashMap<>());
            this.map.get(xPos).put(yPos, new Cell(xPos, yPos, this));
        }
    }

    /**
     * Adds the given {@link Cell} to the {@link Map}.
     * @param cell  the {@link Cell} to add.
     */
    private void addCell(Cell cell) {
        if(getCell(cell.xPos, cell.yPos) == null) {
            this.map.computeIfAbsent(cell.xPos, k -> new HashMap<>());
            this.map.get(cell.xPos).put(cell.yPos, cell);
        }
    }

    /**
     * Adds the given black {@link Cell} to the black cells array.
     * @param cell  the black {@link Cell} to add.
     */
    void addBlackCell(Cell cell) {
        if(cell.getState()) {
            this.blackCells.add(cell);
            this.map.get(cell.xPos).put(cell.yPos, cell);
        }
    }

    /**
     * Adds the given white {@link Cell} to the white cells array.
     * @param cell  the white {@link Cell} to add.
     */
    void addWhiteCell(Cell cell) {
        if(!cell.getState()) {
            this.whiteCells.add(cell);
            addCell(cell);
        }
    }

    /**
     * Remove the given black {@link Cell} from the black cells array only if
     * it contains the given {@link Cell}.
     * @param cell  the black {@link Cell} to remove.
     */
    void removeBlackCell(Cell cell) {
        if(this.blackCells.contains(cell))
            this.blackCells.remove(cell);
    }

    /**
     * Remove the given white {@link Cell} from the white cells array only if
     * it contains the given {@link Cell}.
     * @param cell  the white {@link Cell} to remove.
     */
    void removeWhiteCell(Cell cell) {
        if(this.whiteCells.contains(cell))
            this.whiteCells.remove(cell);
    }

    /**
     * Inverts the {@link Cell} at the given position.
     * @param xPos  the x-position of the {@link Cell} to invert.
     * @param yPos  the y-position of the {@link Cell} to invert.
     */
    void invert(int xPos, int yPos) {
        try {
            this.map.get(xPos).get(yPos).invert();
        }
        catch (NullPointerException e) {
            addCell(new Cell(xPos, yPos, this));
            this.map.get(xPos).get(yPos).invert();
        }
    }

    // Getters

    /**
     * Returns the {@link Cell} at the given coordinates.
     * @param xPos  the x-position of the {@link Cell} to return.
     * @param yPos  the y-position of the {@link Cell} to return.
     * @return the {@link Cell} located at the coordinates.
     */
    private Cell getCell(int xPos, int yPos) {
        if(this.map.get(xPos) == null)
            return null;
        else
            return this.map.get(xPos).get(yPos);
    }

    /**
     * Returns the state of the {@link Cell} at the given coordinates.
     * @param xPos  x-position of the {@link Cell} to return.
     * @param yPos  y-position of the {@link Cell} to return.
     * @return  the state of the cell; true or false.
     */
    boolean getCellState(int xPos, int yPos) {
        try {
            return this.map.get(xPos).get(yPos).getState();
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns the black cells array
     * @return the black cells array
     */
    ArrayList<Cell> getBlackCells() {
        return this.blackCells;
    }
}
