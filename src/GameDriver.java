import java.util.ArrayList;
import java.util.Scanner;

public class GameDriver {

    Map gameMap;
    Ant ant;

    GameDriver() {
        this.gameMap = new Map();
        this.ant = new Ant(this.gameMap);
    }

    void addBlackCell(int xPos, int yPos) {
        Cell toAdd = new Cell(xPos, yPos, this.gameMap);
        toAdd.invert();
        this.gameMap.addBlackCell(toAdd);
    }

    void step() {
        this.ant.step();
    }

    // Getters
    public Map getGameMap() {
        return this.gameMap;
    }

    public Ant getAnt() {
        return this.ant;
    }

    // Setters
    public void setAntPos(int xPos, int yPos) {
        this.ant.xPos = xPos;
        this.ant.yPos = yPos;
    }

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        GameDriver game = new GameDriver();
//        while(true) {
//            System.out.println("Ant on (" + game.ant.xPos + ", " + game.ant.yPos + ").");
//            System.out.println("Current state of cell: " + game.gameMap.getCellState(game.ant));
//            System.out.println("Current direction of ant: " + game.ant.direction.toString());
//            System.out.println("Black cells: " + game.gameMap.getBlackCells().toString());
//            System.out.println("White cells: " + game.gameMap.getWhiteCells().toString());
//            System.out.println("-----------------------------------------------------------");
//            scan.nextLine();
//            game.step();
//        }
//    }

}
