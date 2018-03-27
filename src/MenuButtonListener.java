import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener for the buttons within the menu in the
 * {@link GUIDriver}. Each button is assigned a number that is used to
 * determine which button is pressed.
 */
public class MenuButtonListener implements ActionListener {

    private GUIDriver guiDriver;
    private byte menuItem;

    /**
     * Constructor for the {@link MenuButtonListener} object.
     * @param guiDriver the {@link GUIDriver} that this listener is located in.
     * @param menuItem  the number that determines which button is pressed.
     */
    MenuButtonListener(GUIDriver guiDriver, byte menuItem) {
        this.guiDriver = guiDriver;
        this.menuItem = menuItem;
    }

    /**
     * Controls what happens when each button is pressed.
     * @param e  the object returned from clicking the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(this.menuItem){
            case(0):
                System.exit(0);
                break;
            case(1):
                this.guiDriver.getMapCanvas().generateRandomMap();
                break;
        }
    }
}
