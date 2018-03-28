/**
 * Controls the logic of stepping through a game.
 */
public class GameDriver {

    private Map gameMap;
    private Ant ant;

    /**
     * Constructor for the {@link GameDriver} object.
     */
    GameDriver() {
        this.gameMap = new Map();
        this.ant = new Ant();
    }

    /**
     * Takes one step through the simulation and adjust each object
     * accordingly.
     */
    void step() {
        this.gameMap.invert(this.ant.getXPos(), this.ant.getYPos());
        this.ant.rotate(!this.gameMap.getCellState(ant.getXPos(), ant.getYPos()));
        this.ant.move();
    }

    // Getters

    /**
     * Gets the gameMap {@link Map} object.
     * @return  the gameMap object.
     */
    public Map getGameMap() {
        return this.gameMap;
    }

    /**
     * Gets the {@link Ant} object of the {@link GameDriver}.
     * @return  the {@link Ant} object.
     */
    public Ant getAnt() {
        return ant;
    }
}
