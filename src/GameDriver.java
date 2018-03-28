public class GameDriver {

    Map gameMap;
    Ant ant;

    GameDriver() {
        this.gameMap = new Map();
        this.ant = new Ant();
    }

    void step() {
        this.gameMap.invert(this.ant.getXPos(), this.ant.getYPos());
        this.ant.rotate(!this.gameMap.getCellState(ant.getXPos(), ant.getYPos()));
        this.ant.move();
    }

    // Getters

    public Map getGameMap() {
        return this.gameMap;
    }

    public Ant getAnt() {
        return ant;
    }
}
