/* Isaac Wismer
 * 
 */
package nutrientcalculator;

import java.util.ArrayList;

public class Ingredient {

    //food ID number, the cmb number for the fraction, the cmb num for the unit, quantity
    private int id, fractionNum, unitNum, quantity;
    //Short name, what unit is used, Formatted name (with correct quantity in front), the name of the fraction/other unit
    private String name, unit, formattedName, FractionName;
    private ArrayList<Measures> measures = new ArrayList<>();

    public Ingredient(int i, String s) {
        id = i;
        name = s;
    }

    public Ingredient() {
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setID(int i) {
        id = i;
    }

    public void setName(String s) {
        name = s;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void setUnit(String u) {
        unit = u;
    }

    public int getFractionNum() {
        return fractionNum;
    }

    public void setFractionNum(int fractionNum) {
        this.fractionNum = fractionNum;
    }

    public String getFormattedName() {
        return formattedName;
    }

    public void setFormattedName(String formattedName) {
        this.formattedName = formattedName;
    }

    public String getFractionName() {
        return FractionName;
    }

    public void setFractionName(String FractionName) {
        this.FractionName = FractionName;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public ArrayList<Measures> getMeasures() {
        return measures;
    }

    public void setMeasures(ArrayList<Measures> measures) {
        this.measures = measures;
    }

    public Measures getSingleMeasureID(int ID) {
        for (int i = 0; i < measures.size(); i++) {
            if (measures.get(i).getID() == ID) {
                return measures.get(i);
            }
        }
        return null;
    }

    public void setMeasures(int ID, String name) {
        for (int i = 0; i < measures.size(); i++) {
            if (measures.get(i).getID() == ID) {
                measures.get(i).setName(name);
            }
        }
    }

    public void addMeasure(int ID, double conversion) {
        measures.add(new Measures(ID, conversion));
    }

    public void addMeasureFull(Measures measure) {
        measures.add(measure);
    }

    public void removeSingleMeasure(int index) {
        measures.remove(index);
    }

    public Measures getSingleMeasureIndex(int index) {
        return measures.get(index);
    }

    public boolean equals(Ingredient in) {
        if (in == null) {
            return false;
        }
        if (getClass() != in.getClass()) {
            return false;
        }
        if (this.id != in.id) {
            return false;
        }
        if (this.quantity != in.quantity) {
            return false;
        }
        return true;
    }

}
