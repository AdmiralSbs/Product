package com.admiralsbs.KitchenManager;

public class Ingredient extends ObjectKitchen {
    private final String unit;
    private int count;
    private final Priority priority;

    public Ingredient(String n, int c, String u, Priority p) {
        super(n);
        count = c;
        unit = u;
        priority = p;
    }

    public int getCount() {
        return count;
    }

    public String getUnit() {
        return unit;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setCount(int i) {
        if (count == -1)
            return;
        if (i >= 0)
            count = i;
    }

    public void changeCount(int i) {
        if (count == -1)
            return;
        count += i;
    }

    public int compareTo(Ingredient i) {
        return name.compareToIgnoreCase(i.getName());
    }

}