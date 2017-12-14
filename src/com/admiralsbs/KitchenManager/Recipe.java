package com.admiralsbs.KitchenManager;

import java.util.ArrayList;

public class Recipe extends ObjectKitchen {
    protected final String category;
    protected ArrayList<Ingredient> ingredients;
    protected ArrayList<Person> people;
    protected boolean[] mealTimes = new boolean[5]; //[Breakfast, Lunch, Dinner, Dessert, Snack]

    public Recipe(String n, String c, ArrayList<Ingredient> ings, ArrayList<Person> peeps, boolean[] mT) {
        super(n);
        category = c;
        ingredients = ings;
        people = peeps;
        mealTimes = mT;
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

    public boolean[] getMealTimes() {
        return mealTimes;
    }

    public boolean getMealTime(int i) {
        return mealTimes[i];
    }

    public int compareTo(Recipe recipe) {
        return name.compareToIgnoreCase(recipe.getName());
    }

    public int compareTo(SubRecipe recipe) {
        int nameDiff = name.compareToIgnoreCase(recipe.getParentRecipe().getName());
        if (nameDiff == 0) {
            return -1;
        } else {
            return nameDiff;
        }
    }
}
