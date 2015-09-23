/* Isaac Wismer
 *  Jun 17, 2015
 */
package nutrientcalculator;

/**
 *
 * @author isaac
 */
public class Measures {

    private int ID;
    private double conversion;
    private String name = "";

    /**
     *
     * @param ID
     * @param conversion
     */
    public Measures(int ID, double conversion) {
        this.ID = ID;
        this.conversion = conversion;
    }

    /**
     *
     */
    public Measures() {
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     */
    public double getConversion() {
        return conversion;
    }

    /**
     *
     * @param conversion
     */
    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

}
