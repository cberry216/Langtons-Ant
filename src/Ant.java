import java.awt.*;

/**
 * This class is used to simulate the ant in the cellular automaton. It has
 * Two rules: if the ant is a black square, turn 90 degrees right, move
 * forward and invert the previous {@link Cell}, and if the ant is on a white
 * square, turn 90 degrees left, move forward, and invert the previous
 * {@link Cell}.
 */
public class Ant {

    int xPos, yPos;
    private Point direction;
    private Map map;

    /**
     * Constructor for the {@link Ant} object
     * @param map  the {@link Map} which contains the {@link Ant}
     */
    Ant(Map map) {
        this.xPos = 0;
        this.yPos = 0;
        this.direction = Direction.NORTH;
        this.map = map;
    }

    /**
     * Inverts the {@link Cell} that the {@link Ant} is currently on. Makes a
     * call the {@link Cell}'s invert method.
     */
    private void invert() {
        this.map.invert(this.xPos, this.yPos);
    }

    /**
     * Controls the rules for how the {@link Ant} can move.
     */
    void step() {
        if (this.map.getCellState(this.xPos, this.yPos)) {
            if(this.direction == Direction.NORTH)
                this.direction = Direction.EAST;
            else if(this.direction == Direction.EAST)
                this.direction = Direction.SOUTH;
            else if(this.direction == Direction.SOUTH)
                this.direction = Direction.WEST;
            else if(this.direction == Direction.WEST)
                this.direction = Direction.NORTH;
        }
        else {
            if(this.direction == Direction.NORTH)
                this.direction = Direction.WEST;
            else if(this.direction == Direction.WEST)
                this.direction = Direction.SOUTH;
            else if(this.direction == Direction.SOUTH)
                this.direction = Direction.EAST;
            else if(this.direction == Direction.EAST)
                this.direction = Direction.NORTH;
        }
        invert();
        this.xPos += this.direction.x;
        this.yPos += this.direction.y;
        this.map.addCell(this.xPos, this.yPos);
    }

}
