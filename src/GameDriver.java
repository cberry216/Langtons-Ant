/**
 * This class is where Langton's Ant is run. The {@link GameDriver} has a
 * {@link Map} and an {@link Ant} to keep track of. The
 * {@link GameDriver}'s main responsibility is to step through the game
 * constantly.
 */
public class GameDriver {

    private Map gameMap;
    private Ant ant;

    /**
     * Constructor for the {@link GameDriver} object.
     */
    GameDriver() {
        this.gameMap = new Map();
        this.ant = new Ant(this.gameMap);
    }

    /**
     * Adds a black {@link Cell} to the {@link Map}.
     * @param xPos  x-position to add the {@link Cell}.
     * @param yPos  y-position to add the {@link Cell}.
     */
    void addBlackCell(int xPos, int yPos) {
        Cell toAdd = new Cell(xPos, yPos, this.gameMap);
        toAdd.invert();
        this.gameMap.addBlackCell(toAdd);
    }

    /**
     * Take one step in the simulation.
     */
    void step() {
        this.ant.step();
    }

    // Getters

    /**
     * Returns the game's {@link Map} object.
     * @return  the games's {@link Map} object.
     */
    public Map getGameMap() {
        return this.gameMap;
    }

    /**
     * Returns the game's {@link Ant} object.
     * @return  the game's {@link Ant} object.
     */
    public Ant getAnt() {
        return this.ant;
    }

    // Setters

    /**
     * Sets the position of the {@link Ant} to the given coordinates.
     * @param xPos  x-position to set the {@link Ant}.
     * @param yPos  y-position to set the {@link Ant}.
     */
    public void setAntPos(int xPos, int yPos) {
        this.ant.xPos = xPos;
        this.ant.yPos = yPos;
    }

}
