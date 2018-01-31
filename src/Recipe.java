import java.io.Serializable;
import java.util.ArrayList;

public class Recipe extends ObjectKitchen implements Serializable {
    private static final long serialVersionUID = -3L;

    private String category;
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<Person> people;
    private boolean[] mealTimes = new boolean[5]; //[Breakfast, Lunch, Dinner, Dessert, Snack]
    public static final String[] MEALS = {"Breakfast", "Lunch", "Dinner", "Dessert", "Snack"};
    private Recipe parentRecipe;

    public Recipe(String n, String c, ArrayList<Ingredient> ings, ArrayList<Person> peeps, boolean[] mT) {
        super(n);
        category = c;
        ingredients = ings;
        people = peeps;
        mealTimes = mT;
        parentRecipe = null;
    }

    public Recipe(String n, String c, ArrayList<Ingredient> ings, ArrayList<Person> peeps, boolean[] mT, Recipe parRec) {
        super(n);
        category = c;
        ingredients = ings;
        people = peeps;
        mealTimes = mT;
        parentRecipe = parRec;
    }

    public void setCategory(String c) {
        category = c;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public boolean getMealTime(int i) {
        return mealTimes[i];
    }

    public void setMealTime(boolean b, int i) {
        mealTimes[i] = b;
    }

    public Recipe getParentRecipe() {
        return parentRecipe;
    }

    public void setParentRecipe(Recipe parRec) {
        parentRecipe = parRec;
    }

    public boolean isSubRecipe() {
        return parentRecipe != null;
    }

    public int compareTo(Recipe recipe) {
        if (!isSubRecipe() && !recipe.isSubRecipe()) //both have no parent
            return name.compareToIgnoreCase(recipe.getName());
        else if (!isSubRecipe() && recipe.isSubRecipe()) { //recipe has parent
            int nameDiff = name.compareToIgnoreCase(recipe.getParentRecipe().getName());
            if (nameDiff == 0)
                return -1;
            else
                return nameDiff;
        } else if (!recipe.isSubRecipe()) { //this has parent
            int nameDiff = getParentRecipe().getName().compareToIgnoreCase(recipe.getName());
            if (nameDiff == 0)
                return 1;
            else
                return nameDiff;
        } else { //both have parent
            int nameDiff = getParentRecipe().getName().compareToIgnoreCase(recipe.getParentRecipe().getName());
            if (nameDiff == 0)
                return name.compareToIgnoreCase(recipe.getName());
            else
                return nameDiff;
        }
    }



}