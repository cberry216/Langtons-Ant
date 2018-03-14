import java.awt.*;

public class Ant {

    int xPos, yPos;
    Point direction;
    Map map;

    Ant(Map map) {
        this.xPos = 0;
        this.yPos = 0;
        this.direction = Direction.NORTH;
        this.map = map;
    }

    Ant(Map map, int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.direction = Direction.NORTH;
        this.map = map;

        Cell firstCell = new Cell(this.xPos, this.yPos, this.map);
        this.map.addCell(firstCell);
        this.map.addWhiteCell(firstCell);
    }

    void invert() {
        this.map.invert(this.xPos, this.yPos);
    }

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
