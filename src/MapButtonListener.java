import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener for the buttons in the {@link GUIDriver}. Each
 * button has an number identifier that determines which button was
 * pressed.
 */
public class MapButtonListener implements ActionListener {

    private GUIDriver guiDriver;
    private byte button;

    /**
     * Constructor for the {@link MapButtonListener} object.
     * @param guiDriver  the {@link GUIDriver} the listener is located in.
     * @param button     the number identifier for each button.
     */
    MapButtonListener(GUIDriver guiDriver, byte button) {
        this.guiDriver = guiDriver;
        this.button = button;
    }

    /**
     * Controls what happens when each button is pressed.
     * @param e  the object returned from pressing each button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (button) {
            case 0:
                this.guiDriver.setPlay(true);
                break;
            case 1:
                this.guiDriver.setPlay(false);
                break;
            case 2:
                this.guiDriver.resetGUI();
                break;
        }
    }
}
