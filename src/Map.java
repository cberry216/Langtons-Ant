import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    HashMap<Integer, HashMap<Integer, Cell>> map;
    ArrayList<Cell> blackCells, whiteCells;

    Map() {
        this.map = new HashMap<>();
        this.blackCells = new ArrayList<>();
        this.whiteCells = new ArrayList<>();
    }

    void addCell(int xPos, int yPos) {
        if(getCell(xPos, yPos) == null) {
            this.map.computeIfAbsent(xPos, k -> new HashMap<>());
            this.map.get(xPos).put(yPos, new Cell(xPos, yPos, this));
        }
    }

    void addCell(Cell cell) {
        if(getCell(cell.xPos, cell.yPos) == null) {
            this.map.computeIfAbsent(cell.xPos, k -> new HashMap<>());
            this.map.get(cell.xPos).put(cell.yPos, cell);
        }
    }

    void removeCell(int xPos, int yPos) {
        if(this.map.containsKey(xPos)) {
            if(this.map.get(xPos).containsKey(yPos)) {
                this.map.get(xPos).remove(yPos);
            }
        }
    }

    void addBlackCell(Cell cell) {
        this.blackCells.add(cell);
    }

    void addWhiteCell(Cell cell) {
        this.whiteCells.add(cell);
    }

    void removeBlackCell(Cell cell) {
        if(this.blackCells.contains(cell))
            this.blackCells.remove(cell);
    }

    void removeWhiteCell(Cell cell) {
        if(this.whiteCells.contains(cell))
            this.whiteCells.remove(cell);
    }

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

    Cell getCell(int xPos, int yPos) {
        if(this.map.get(xPos) == null)
            return null;
        else
            return this.map.get(xPos).get(yPos);
    }

    boolean getCellState(int xPos, int yPos) {
        try {
            return this.map.get(xPos).get(yPos).getState();
        }
        catch (NullPointerException e) {
            return false;
        }
    }

    boolean getCellState(Ant ant) {
        return this.map.get(ant.xPos).get(ant.yPos).getState();
    }

    ArrayList<Cell> getBlackCells() {
        return this.blackCells;
    }

    ArrayList<Cell> getWhiteCells() {
        return this.whiteCells;
    }
}
