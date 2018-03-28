import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is what keeps track of what cells are black and white and
 * where they are located. It also controls the inverting of cells from one
 * color to the other.
 */
public class Map {

    HashMap<Integer, HashMap<Integer, Boolean>> map;
    ArrayList<Point> blackCells;

    /**
     * Constructor for the {@link Map} object.
     */
    Map() {
        this.map = new HashMap<>();
        this.blackCells = new ArrayList<>();
    }

    /**
     * Adds a cell to the {@link Map}. If a cell is being added that it is
     * already there, it will not add it. It also adds the position of the cell
     * to the blackCells array if the state is true.
     * @param xPos   x-position of cell to add.
     * @param yPos   y-position of cell to add.
     * @param state  state of cell to add; true or false;
     */
    void addCell(int xPos, int yPos, boolean state) {
        if(state)
            blackCells.add(new Point(xPos, yPos));
        if(this.map.containsKey(xPos)) {
            if(!this.map.get(xPos).containsKey(yPos)) {
                this.map.get(xPos).put(yPos, state);
            }
        }
        else {
            this.map.put(xPos, new HashMap<>());
            this.map.get(xPos).put(yPos, state);
        }
    }

    /**
     * Inverts the given cell and adjusts the blackCells array accordingly.
     * @param xPos  x-position to invert.
     * @param yPos  y-position to invert.
     */
    void invert(int xPos, int yPos) {
        addCell(xPos, yPos, false);
        this.map.get(xPos).replace(yPos, !this.map.get(xPos).get(yPos));
        if(getCellState(xPos, yPos))
            this.blackCells.add(new Point(xPos, yPos));
        else
            this.blackCells.remove(new Point(xPos, yPos));
    }

    /**
     * Gets the state of the cell at the given coordinates, false if the cell
     * does not exist.
     * @param xPos  x-position of cell to get state of.
     * @param yPos  y-position of cell to get state of.
     * @return      state of the cell at the coordinates, false otherwise.
     */
    boolean getCellState(int xPos, int yPos) {
        if(this.map.containsKey(xPos)) {
            return this.map.get(xPos).getOrDefault(yPos, false);
        }
        else {
            return false;
        }
    }
}
