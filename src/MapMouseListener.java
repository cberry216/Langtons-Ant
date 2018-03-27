import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is the listener for the {@link MapCanvas} class. In this
 * class, when a user clicks on the canvas, it places a black square on the
 * canvas. Clicking on a black square, removes it.
 */
public class MapMouseListener extends MouseAdapter {

    private MapCanvas mapCanvas;

    /**
     * Constructor for the {@link MapMouseListener} object.
     * @param mapCanvas  the {@link MapCanvas} the listener is located in
     */
    MapMouseListener(MapCanvas mapCanvas) {
        super();
        this.mapCanvas = mapCanvas;
    }

    /**
     * Controls how the black {@link Cell} is added to the {@link MapCanvas}.
     * @param me  the object returned from the click.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        int xVal = (me.getX() / this.mapCanvas.cellLength) * this.mapCanvas.cellLength;
        int yVal = (me.getY() / this.mapCanvas.cellLength) * this.mapCanvas.cellLength;
        Point toAdd = new Point(xVal, yVal);
        if(this.mapCanvas.blackCells.contains(toAdd))
            this.mapCanvas.blackCells.remove(toAdd);
        else
            this.mapCanvas.blackCells.add(toAdd);
        this.mapCanvas.repaint();
    }
}
