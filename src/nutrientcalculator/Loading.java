/* Isaac Wismer
 * 
 */
package nutrientcalculator;

/**
 *
 * @author isaac
 */
public class Loading implements Runnable {

    @Override
    public void run() {
        GUI.lblLoad.setVisible(true);
        while (GUI.update) {
            GUI.lblLoad.update(GUI.lblLoad.getGraphics());
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    /**
     * starts the loading image
     */
    public static void start() {
        (new Thread(new Loading())).start();
    }
}
