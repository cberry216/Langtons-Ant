import java.awt.*;

/**
 * This class keeps track of which cell to invert and controls the movement
 * and rotation of the {@link Ant}.
 */
public class Ant {

    private int xPos, yPos;
    private Point direction;

    /**
     * Constructor for the {@link Ant} object.
     */
    Ant() {
        this.xPos = 0;
        this.yPos = 0;
        this.direction = Direction.NORTH;
    }

    /**
     * Rotates the {@link Ant} depending on what state the {@link Ant} is
     * currently on.
     * @param state  the state of the cell the {@link Ant} is currently on.
     */
    void rotate(boolean state) {
        if(state) {
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
    }

    /**
     * Moves the {@link Ant} according to what direction it is facing.
     */
    void move() {
        this.xPos += this.direction.getX();
        this.yPos += this.direction.getY();
    }

    /**
     * Sets the position of the {@link Ant} to the given coordinates.
     * @param xPos  the x-position to change the {@link Ant}'s position to.
     * @param yPos  the y-position to change the {@link Ant}'s position to.
     */
    void setAntPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // Getters

    /**
     * Gets the x-position of the {@link Ant}.
     * @return  the x-position of the {@link Ant}.
     */
    public int getXPos() {
        return this.xPos;
    }

    /**
     * Gets the y-position of the {@link Ant}.
     * @return  the y-position of the {@link Ant}.
     */
    public int getYPos() {
        return this.yPos;
    }

    /**
     * Gets the current direction the {@link Ant} is facing.
     * @return  the {@link Ant}'s current direction.
     */
    Point getDirection() {
        return this.direction;
    }
}
