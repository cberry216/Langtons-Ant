import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the listener for the speed {@link javax.swing.JSlider} in
 * {@link GUIDriver}. When a speed change is detected, the game is adjusted
 * accordingly.
 */
public class SpeedChangeListener implements ChangeListener {

    private GUIDriver guiDriver;

    /**
     * Constructor for the {@link SpeedChangeListener} object.
     * @param guiDriver the {@link GUIDriver} that this listener is located in.
     */
    SpeedChangeListener(GUIDriver guiDriver) {
        this.guiDriver = guiDriver;
    }

    /**
     * When a change is detected, it set the speed of the game to the speed of
     * the slider.
     * @param e  the object returned from changing the speed.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        guiDriver.setSpeed(guiDriver.getSpeed());
    }
}
