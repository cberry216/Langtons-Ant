import java.awt.*;

public class Ant {

    private int xPos, yPos;
    Point direction;

    Ant() {
        this.xPos = 0;
        this.yPos = 0;
        this.direction = Direction.NORTH;
    }

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

    void move() {
        this.xPos += this.direction.getX();
        this.yPos += this.direction.getY();
    }

    void setAntPos(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // Getters

    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    Point getDirection() {
        return this.direction;
    }
}
