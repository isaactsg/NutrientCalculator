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

/**
 *
 * @author isaac
 */
public class Recipe {

    private String title = "", instructions = "", servingEng = "", servingFre = "";
    private int servings = 1;
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private DefaultComboBoxModel list = new DefaultComboBoxModel();

    /**
     *
     * @param title
     * @param instructions
     */
    public Recipe(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
    }

    /**
     *
     * @param title
     */
    public Recipe(String title) {
        this.title = title;
    }

    /**
     *
     */
    public Recipe() {
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     *
     * @return
     */
    public int size() {
        return ingredients.size();
    }

    /**
     *
     * @param instructions
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     *
     * @return
     */
    public String getServingEng() {
        return servingEng;
    }

    /**
     *
     * @param servingEng
     */
    public void setServingEng(String servingEng) {
        this.servingEng = servingEng;
    }

    /**
     *
     * @return
     */
    public String getServingFre() {
        return servingFre;
    }

    /**
     *
     * @param servingFre
     */
    public void setServingFre(String servingFre) {
        this.servingFre = servingFre;
    }

    /**
     *
     * @return
     */
    public int getServings() {
        return servings;
    }

    /**
     *
     * @param servings
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     *
     * @param ingred
     */
    public void addIngredient(Ingredient ingred) {
        ingredients.add(ingred);
    }

    /**
     *
     * @param index
     * @param ingred
     */
    public void setSingleIngredient(int index, Ingredient ingred) {
        ingredients.set(index, ingred);
    }

    /**
     *
     * @return
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @param index
     * @return
     */
    public Ingredient getSingleIngredientIndex(int index) {
        return ingredients.get(index);
    }

    /**
     *
     * @param ID
     * @return
     */
    public Ingredient getSingleIngredientID(int ID) {
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getID() == ID) {
                return ingredients.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param index
     */
    public void remove(int index) {
        ingredients.remove(index);
    }

    /**
     *
     * @return
     */
    public DefaultComboBoxModel getList() {
        return list;
    }

    /**
     *
     * @param list
     */
    public void setList(DefaultComboBoxModel list) {
        this.list = list;
    }

    /**
     *
     * @param item
     */
    public void addListItem(String item) {
        list.addElement(item);
    }

    /**
     *
     * @param r
     * @return
     */
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
