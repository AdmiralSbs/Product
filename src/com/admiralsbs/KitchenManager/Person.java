package com.admiralsbs.KitchenManager;

import java.io.Serializable;

public class Person extends ObjectKitchen implements Serializable {
    private static final long serialVersionUID = -5L;

    public Person(String n) { super(n); }

    public int compareTo(Person p) {
        return name.compareToIgnoreCase(p.getName());
    }

    public String getName() {
        return name;
    }

}
