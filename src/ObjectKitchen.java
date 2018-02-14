import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

abstract class ObjectKitchen implements Serializable {
    private static final long serialVersionUID = -2L;

    protected final String name;

    protected ObjectKitchen(String n) {
        name = n;
    }

    protected String getName() {
        return name;
    }

    public int compareTo(ObjectKitchen obj) { //Calls the correct version of compareTo
        if (this instanceof Ingredient && obj instanceof Ingredient)
            return ((Ingredient) this).compareTo((Ingredient) obj);
        else if (this instanceof Recipe && obj instanceof Recipe)
            return ((Recipe) this).compareTo((Recipe) obj);
        else if (this instanceof Person && obj instanceof Person)
            return ((Person) this).compareTo((Person) obj);
        else
            return name.compareToIgnoreCase(obj.getName());
    }

    public static int addAlphabetically(ArrayList<? extends ObjectKitchen> objectKitchens, ObjectKitchen obj) {
        for (int i = 0; i < objectKitchens.size(); i++) {
            if (objectKitchens.get(i).compareTo(obj) > 0)
                return i;
        }
        return 0;
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

    public static ArrayList<ObjectKitchen> convertToObjectKitchen(ArrayList<? extends ObjectKitchen> oKs) {
        ArrayList<ObjectKitchen> newList = new ArrayList<>();
        newList.addAll(oKs);
        return newList;
    } //Converts an ArrayList<? extends ObjectKitchen> into an ArrayList<ObjectKitchen>

    @Override
    public String toString() {
        return getName();
    } //For compatibility purposes

}