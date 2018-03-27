import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This is the listener for the cell size {@link javax.swing.JSpinner}.
 * When a change in cell size is detected, the {@link MapCanvas} is
 * adjusted accordingly.
 */
public class CellSizeChangeListener implements ChangeListener {

    private GUIDriver guiDriver;

    /**
     * Constructor for the {@link CellSizeChangeListener} object.
     * @param guiDriver  the {@link GUIDriver} the listener is located in.
     */
    CellSizeChangeListener(GUIDriver guiDriver) {
        this.guiDriver = guiDriver;
    }

    /**
     * Changes the {@link MapCanvas} according to what the cell size was
     * changed to.
     * @param e  the object returned from changing the cell size spinner.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        this.guiDriver.getMapCanvas().setCellLength(this.guiDriver.getCellLength());
        this.guiDriver.getMapCanvas().resetNumCols();
        this.guiDriver.getMapCanvas().resetNumRows();
        if(!this.guiDriver.getPlay())
            this.guiDriver.getMapCanvas().centerAnt();
        this.guiDriver.getMapCanvas().repaint();
    }
}
