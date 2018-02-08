import java.io.Serializable;

public class Ingredient extends ObjectKitchen implements Serializable {
    private static final long serialVersionUID = -4L;
    private String unit;
    private int count;
    private Priority priority;
    //When unit is "", count of -1 represents available, and -2 unavailable
    //Otherwise, count > 0 is available, and 0 unavailable

    public Ingredient(String n, int c, String u, Priority p) {
        super(n);
        count = c;
        setUnit(u);
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
        if (u.equals("")) {
            if (count > 0)
                count = -1;
            else if (count == 0)
                count = -2;
        } else if (count < 0)
            count = 0;
    }

    public void setPriority(Priority p) {
        priority = p;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setCount(int i) {
        count = i;
    }

    public int compareTo(Ingredient i) {
        return name.compareToIgnoreCase(i.getName());
    }

    public boolean isAvailable() {
        return (count >= 1 || count == -1);
    }

}