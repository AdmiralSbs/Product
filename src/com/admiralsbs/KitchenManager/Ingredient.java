package com.admiralsbs.KitchenManager;

import java.io.Serializable;

public class Ingredient extends ObjectKitchen implements Serializable {
    private static final long serialVersionUID = -4L;
    private String unit;
    private int count;
    private Priority priority;

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

    public void setUnit(String u) {
        unit = u;
        if (u.equals(""))
            count = -1;
        else if (count < 0)
            count = 0;
    }

    public void setPriority(Priority p) {
        priority = p;
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