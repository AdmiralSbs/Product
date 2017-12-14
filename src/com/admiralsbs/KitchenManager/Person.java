package com.admiralsbs.KitchenManager;

public class Person extends ObjectKitchen {

    public Person(String n) { super(n); }

    public int compareTo(Person p) {
        return name.compareToIgnoreCase(p.getName());
    }

    public String getName() {
        return name;
    }

}
