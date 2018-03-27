/**
 * This class is the main class that runs the whole game.
 */
public class LangtonsAnt {

    public static void main(String[] args) {
        GUIDriver guiDriver = new GUIDriver();
        guiDriver.setVisible(true);
        while(true) {
            if(guiDriver.getPlay()) {
                if(!guiDriver.getStarted()) {
                    guiDriver.startSimulation();
                }
                else
                    guiDriver.continueSimulation();
            }
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
