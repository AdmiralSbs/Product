import java.io.Serializable;
import java.util.ArrayList;

class Kitchen implements Serializable {
    private static final long serialVersionUID = -1L;

    private final String owner;
    private final ArrayList<Ingredient> ingredients;
    private final ArrayList<Recipe> recipes;
    private final ArrayList<Person> people;

    public Kitchen(String o) {
        owner = o;
        ingredients = new ArrayList<>();
        recipes = new ArrayList<>();
        people = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    //Nearly all uses of these three methods requires receiving a list of ObjectKitchen
    public ArrayList<ObjectKitchen> getIngredients() {
        return ObjectKitchen.convertToObjectKitchen(ingredients);
    }

    public ArrayList<ObjectKitchen> getRecipes() {
        return ObjectKitchen.convertToObjectKitchen(recipes);
    }

    public ArrayList<ObjectKitchen> getPeople() {
        return ObjectKitchen.convertToObjectKitchen(people);
    }

    public Ingredient getIngredient(String s) {
        for (Ingredient i : ingredients) {
            if (i.getName().equalsIgnoreCase(s))
                return i;
        }
        return null;
    }

    public Recipe getRecipe(String s) {
        for (Recipe r : recipes) {
            if (r.getName().equalsIgnoreCase(s))
                return r;
        }
        return null;
    }

    public Person getPerson(String s) {
        for (Person p : people) {
            if (p.getName().equalsIgnoreCase(s))
                return p;
        }
        return null;
    }

    public boolean removeIngredient(Ingredient i) {
        if (ingredients.contains(i)) {
            ingredients.remove(i);
            for (Recipe r : recipes) { //Clear all references to ingredient i
                r.getIngredients().remove(i);
            }
            return true;
        }
        return false;
    }

    public boolean removeRecipe(Recipe r) {
        if (recipes.contains(r)) {
            recipes.remove(r);
            if (!r.isSubRecipe()) { //If this recipe could be a parent
                for (Recipe rec : recipes) {
                    if (rec.getParentRecipe() == r) { //If the removed recipe is a parent for rec
                        rec.setParentRecipe(null);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean removePerson(Person p) {
        if (people.contains(p)) {
            people.remove(p);
            for (Recipe r : recipes) { //Clear all references to person p
                r.getPeople().remove(p);
            }
            return true;
        }
        return false;
    }

    public void addIngredient(Ingredient i) {
        if (!ingredients.contains(i)) {
            ObjectKitchen.addAlphabetically(ObjectKitchen.convertToObjectKitchen(ingredients), i);
        }

    }

    public void addRecipe(Recipe r) {
        if (!recipes.contains(r)) {
            ObjectKitchen.addAlphabetically(ObjectKitchen.convertToObjectKitchen(recipes), r);
        }

    }

    public void addPerson(Person p) {
        if (!people.contains(p)) {
            ObjectKitchen.addAlphabetically(ObjectKitchen.convertToObjectKitchen(people), p);
        }
    }

}