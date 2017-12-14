package com.admiralsbs.KitchenManager;

import java.util.ArrayList;

public class SubRecipe extends Recipe {
    private final Recipe parentRecipe;


    public SubRecipe(String n, String c, ArrayList<Ingredient> ings, ArrayList<Person> peeps, boolean[] mT, Recipe r) {
        super(n, c, ings, peeps, mT);
        parentRecipe = r;
    }

    public Recipe getParentRecipe() {
        return parentRecipe;
    }

    @Override
    public int compareTo(Recipe recipe) {
        int nameDiff = getParentRecipe().getName().compareToIgnoreCase(recipe.getName());
        if (nameDiff == 0) {
            return 1;
        } else {
            return nameDiff;
        }
    }

    @Override
    public int compareTo(SubRecipe recipe) {
        int nameDiff = getParentRecipe().getName().compareToIgnoreCase(recipe.getParentRecipe().getName());
        if (nameDiff == 0) {
            return name.compareToIgnoreCase(recipe.getName());
        } else {
            return nameDiff;
        }
    }
}
