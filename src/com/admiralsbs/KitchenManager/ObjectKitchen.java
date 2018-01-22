package com.admiralsbs.KitchenManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public abstract class ObjectKitchen implements Serializable {
    private static final long serialVersionUID = -2L;

    protected final String name;

    public ObjectKitchen(String n) {
        name = n;
    }

    protected String getName() {
        return name;
    }

    public int compareTo(ObjectKitchen obj) {
        return name.compareToIgnoreCase(obj.getName());
    }

    public static void addAlphabetically(ArrayList<ObjectKitchen> objectKitchens, ObjectKitchen obj) {
        for (int i = 0; i < objectKitchens.size(); i++) {
            if (objectKitchens.get(i).compareTo(obj) > 0) {
                objectKitchens.add(i, obj);
                return;
            }
        }
        objectKitchens.add(obj);
    }

    public static void sort(ArrayList<ObjectKitchen> objectKitchens) {
        for (int i = 0; i < objectKitchens.size() - 1; i++) {
            boolean swapped = false;
            for (int j = i; j < objectKitchens.size() - 1; j++) {
                if (objectKitchens.get(j).compareTo(objectKitchens.get(j + 1)) > 0) {
                    Collections.swap(objectKitchens, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    @Override
    public String toString() {
        return getName();
    }

}
