/* Isaac Wismer
 *  Jun 14, 2015
 */
/*
 * Add in .print for ingredients so thati can print easily
 */
package nutrientcalculator;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;

public class Recipe {

    private String title = "", instructions = "", servingEng = "", servingFre = "";
    private int servings = 1;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private DefaultComboBoxModel list = new DefaultComboBoxModel();

    public Recipe(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
    }

    public Recipe(String title) {
        this.title = title;
    }

    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public int size() {
        return ingredients.size();
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getServingEng() {
        return servingEng;
    }

    public void setServingEng(String servingEng) {
        this.servingEng = servingEng;
    }

    public String getServingFre() {
        return servingFre;
    }

    public void setServingFre(String servingFre) {
        this.servingFre = servingFre;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void addIngredient(Ingredient ingred) {
        ingredients.add(ingred);
    }

    public void setSingleIngredient(int index, Ingredient ingred) {
        ingredients.set(index, ingred);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Ingredient getSingleIngredientIndex(int index) {
        return ingredients.get(index);
    }

    public Ingredient getSingleIngredientID(int ID) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getID() == ID) {
                return ingredients.get(i);
            }
        }
        return null;
    }

    public void remove(int index) {
        ingredients.remove(index);
    }

    public DefaultComboBoxModel getList() {
        return list;
    }

    public void setList(DefaultComboBoxModel list) {
        this.list = list;
    }

    public void addListItem(String item) {
        list.addElement(item);
    }

    public boolean equals(Recipe r) {
        if (r == null) {
            return false;
        }
        if (getClass() != r.getClass()) {
            return false;
        }
        if (!Objects.equals(this.title, r.title)) {
            return false;
        }
        if (!Objects.equals(this.instructions, r.instructions)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, r.ingredients)) {
            return false;
        }
        if (!Objects.equals(this.list, r.list)) {
            return false;
        }
        return true;
    }

}
