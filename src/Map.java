import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    HashMap<Integer, HashMap<Integer, Boolean>> map;
    ArrayList<Point> blackCells;

    Map() {
        this.map = new HashMap<>();
        this.blackCells = new ArrayList<>();
    }

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

    void invert(int xPos, int yPos) {
        addCell(xPos, yPos, false);
        this.map.get(xPos).replace(yPos, !this.map.get(xPos).get(yPos));
        if(getCellState(xPos, yPos))
            this.blackCells.add(new Point(xPos, yPos));
        else
            this.blackCells.remove(new Point(xPos, yPos));
    }

    boolean getCellState(int xPos, int yPos) {
        if(this.map.containsKey(xPos)) {
            return this.map.get(xPos).getOrDefault(yPos, false);
        }
        else {
            return false;
        }
    }
}
