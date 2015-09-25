/* Isaac Wismer
 *  Jun 14, 2015
 */
/*
 * Add in .print for ingredients so that i can print easily
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
     * @param title the Title of the recipe
     * @param instructions the instructions for the recipe
     */
    public Recipe(String title, String instructions) {
        this.title = title;
        this.instructions = instructions;
    }

    /**
     *
     * @param title the title of the recipe
     */
    public Recipe(String title) {
        this.title = title;
    }

    /**
     * the default constructor
     */
    public Recipe() {
    }

    /**
     *
     * @return the title of the recipe
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title of the recipe
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the instructions for the recipe
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     *
     * @return the number of ingredients
     */
    public int size() {
        return ingredients.size();
    }

    /**
     *
     * @param instructions instructions for the recipe
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    /**
     *
     * @return the english name for the servings
     */
    public String getServingEng() {
        return servingEng;
    }

    /**
     *
     * @param servingEng the english name for the serving
     */
    public void setServingEng(String servingEng) {
        this.servingEng = servingEng;
    }

    /**
     *
     * @return the french name for the serving
     */
    public String getServingFre() {
        return servingFre;
    }

    /**
     *
     * @param servingFre the french name for the serving
     */
    public void setServingFre(String servingFre) {
        this.servingFre = servingFre;
    }

    /**
     *
     * @return the number of servings it makes
     */
    public int getServings() {
        return servings;
    }

    /**
     *
     * @param servings the number of servings it makes
     */
    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     *
     * @param ingred an ingredient object to add to the recipe
     */
    public void addIngredient(Ingredient ingred) {
        ingredients.add(ingred);
    }

    /**
     *
     * @param index the index to overwrite/place the ingredient at
     * @param ingred the ingredient to place
     */
    public void setSingleIngredient(int index, Ingredient ingred) {
        ingredients.set(index, ingred);
    }

    /**
     *
     * @return the entire array of ingredients
     */
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     *
     * @param ingredients the entire array of ingredients
     */
    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     *
     * @param index the index of the ingredient
     * @return the ingredient object from the specified index
     */
    public Ingredient getSingleIngredientIndex(int index) {
        return ingredients.get(index);
    }

    /**
     *
     * @param ID the ID of the ingredient you want
     * @return the ingredient with the matching ID
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
     * @param index the index of the ingredient to remove
     */
    public void remove(int index) {
        ingredients.remove(index);
    }

    /**
     *
     * @return the list of ingredients for a combobox
     */
    public DefaultComboBoxModel getList() {
        return list;
    }

    /**
     *
     * @param list the list of ingredients for a combobox
     */
    public void setList(DefaultComboBoxModel list) {
        this.list = list;
    }

    /**
     *
     * @param item the item to be added to the list
     */
    public void addListItem(String item) {
        list.addElement(item);
    }

    /**
     *
     * @param r another recipe
     * @return whether or not they are equal
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
