/* Isaac Wismer
 *  Jun 17, 2015
 */
package nutrientcalculator;

public class Measures {

    private int ID;
    private double conversion;
    private String name = "";

    public Measures(int ID, double conversion) {
        this.ID = ID;
        this.conversion = conversion;
    }

    public Measures() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getConversion() {
        return conversion;
    }

    public void setConversion(double conversion) {
        this.conversion = conversion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
