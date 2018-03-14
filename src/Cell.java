public class Cell {

    int xPos, yPos;
    Map map;
    boolean state;

    Cell(int xPos, int yPos, Map map) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.map = map;
        this.state = false;
        this.map.addWhiteCell(this);
    }

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

    boolean getState() {
        return this.state;
    }
}
