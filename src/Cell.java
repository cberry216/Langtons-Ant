/**
 * This class acts a cell inside a {@link Map} object. Each {@link Cell}
 * has an x- and y-coordinate that tells its position and a state, which is
 * either true (which is black) or false (which is white). The {@link Cell}
 * has only one functionality which is to invert its current state.
 */
public class Cell {

    int xPos, yPos;
    private Map map;
    private boolean state;

    /**
     * Constructor for a Cell object
     * @param xPos  x-position of the cell
     * @param yPos  y-position of the cell
     * @param map   {@link Map} that the cell is located in
     */
    Cell(int xPos, int yPos, Map map) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.map = map;
        this.state = false;
        this.map.addWhiteCell(this);
    }

    /**
     * Inverts the current state; if state is true, make it false, if it is
     * false, make it true. Also removes the cell from the given state array in
     * the {@link Map} member.
     */
    void invert() {
        this.state = !this.state;
        if(this.state) {
            this.map.removeWhiteCell(this);
            this.map.addBlackCell(this);
        }
        else {
            this.map.removeBlackCell(this);
            this.map.addWhiteCell(this);
        }
    }

    // Getters

    /**
     * Returns the {@link Cell}'s state.
     * @return  the state of the {@link Cell}
     */
    boolean getState() {
        return this.state;
    }
}
