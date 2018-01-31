package com.admiralsbs.KitchenManager;

import java.io.Serializable;
import java.util.ArrayList;

public class Kitchen implements Serializable {
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<Person> getPeople() {
        return people;
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
            for (Recipe r : recipes) {
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
            for (Recipe r : recipes) {
                r.getPeople().remove(p);
            }
            return true;
        }
        return false;
    }

    public boolean addIngredient(Ingredient i) {
        //noinspection SimplifiableIfStatement
        if (!ingredients.contains(i)) {
            return ingredients.add(i); //true
        }
        return false;
    }

    public boolean addRecipe(Recipe r) {
        //noinspection SimplifiableIfStatement
        if (!recipes.contains(r)) {
            return recipes.add(r); //true
        }
        return false;
    }

    public boolean addPerson(Person p) {
        //noinspection SimplifiableIfStatement
        if (!people.contains(p)) {
            return people.add(p); //true
        }
        return false;
    }

}