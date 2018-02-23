import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class ViewMenuPanel extends JPanelKitchen {

    private final JAutoComboBox people;
    private final JAutoComboBox selectedPeople;
    private final JCheckBox[] timeCheckBoxes = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};

    public ViewMenuPanel() {
        topLabel = new JLabel("View Menu");
        buttons = new JButton[]{new JButton("Create Menu"), new JButton("Back"), new JButton("Add Person"), new JButton("Remove Person")};
        locations = new int[]{-1, 0, -1, -1};
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel[] timeLabels = {new JLabel("Breakfast"), new JLabel("Lunch"), new JLabel("Dinner"),
                new JLabel("Dessert"), new JLabel("Snack")};
        JLabel peopleLabel = new JLabel("Unselected People");
        JLabel selectedPeopleLabel = new JLabel("Selected People");

        people = new JAutoComboBox();
        selectedPeople = new JAutoComboBox();

        buttons[2].addActionListener((ActionEvent e) -> {
            Person peep = (Person) people.getSelectedItem();
            if (peep != null) {
                people.removeItem(peep);
                selectedPeople.addItem(peep);
            }
        });
        buttons[3].addActionListener((ActionEvent e) -> {
            Person peep = (Person) selectedPeople.getSelectedItem();
            if (peep != null) {
                selectedPeople.removeItem(peep);
                people.addItem(peep);
            }
        });
        buttons[0].addActionListener((ActionEvent e) -> createMenu());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 4;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        for (int i = 0; i < 5; i++) {
            c.anchor = GridBagConstraints.EAST;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy++;
            add(timeLabels[i], c);

            c.anchor = GridBagConstraints.WEST;
            c.gridx = 1;
            add(timeCheckBoxes[i], c);
        }

        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 2;
        c.gridy = 1;
        add(peopleLabel, c);

        c.gridx = 3;
        add(selectedPeopleLabel, c);

        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 5;
        add(people, c);

        c.gridx = 3;
        add(selectedPeople, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 6;
        add(buttons[2], c);

        c.gridx = 3;
        add(buttons[3], c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(buttons[1], c);

        c.gridx = 0;
        c.gridy = 7;
        add(buttons[0]);
    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> peep = new ArrayList<>();
        peep.addAll(Main.getKitchen().getPeople());
        people.setList(peep);
        selectedPeople.setList(new ArrayList<>());
    }

    private void createMenu() {
        createCSS(); //Hardcodes CSS

        if (selectedPeople.getBaseList().size() == 0) //Requires at least one person
            return;
        boolean[] times = new boolean[5];
        Vector<Integer> ints = new Vector<>(); //Holds the selected times as integers
        boolean b = false;
        for (int i = 0; i < 5; i++) {
            times[i] = timeCheckBoxes[i].isSelected();
            b = times[i] || b;
            if (times[i]) ints.add(i);
        }
        if (!b) return; //Requires at least one time box

        ArrayList<Recipe> recipes = new ArrayList<>(), allRecipes = new ArrayList<>();
        for (ObjectKitchen ok : Main.getKitchen().getRecipes()) allRecipes.add((Recipe) ok);

        AllRecipes:
        for (Recipe r : allRecipes) { //Filters out recipes that don't fit the times and people
            boolean[] tests = {false, false};
            for (Integer i : ints) {
                if (r.getMealTime(i)) { //Checks time
                    tests[0] = true;
                    break;
                }
            }
            if (!tests[0]) continue; //If fails time, check next
            for (Person p : r.getPeople()) { //Checks people
                if (selectedPeople.getBaseList().contains(p)) {
                    tests[1] = true;
                    break;
                }
            }
            if (!tests[1]) continue; //If fails people, check next
            for (Ingredient ing : r.getIngredients()) {
                if (!ing.isAvailable()) { //Any missing ingredient fails a recipe, check next
                    continue AllRecipes;
                }
            }
            //At this point, the recipe meets all qualifications
            recipes.add(r);
        }

        StringBuilder html = new StringBuilder(); //Contains the entire html code
        final String nL = "\n";
        //Add beginning section
        html.append("<html>").append(nL).append("<head>").append(nL).append("<title>Menu</title>").append(nL)
                .append("<link rel=\"stylesheet\" href=\"style.css\">").append(nL)
                .append("</head>").append(nL).append("<body>").append(nL);

        //Create top text
        StringBuilder title = new StringBuilder();
        for (Integer i : ints) {
            title.append(Recipe.MEALS[i]).append("/");
        }
        title.setLength(title.length() - 1);
        title.append(" for ");
        for (ObjectKitchen ok : selectedPeople.getBaseList()) {
            title.append(ok.getName()).append("/");
        }
        title.setLength(title.length() - 1);
        html.append("<h1>").append(title).append("</h1>");

        //Sort recipes into times, then categories
        for (Integer i : ints) {//For each time

            ArrayList<Recipe> recs = new ArrayList<>();
            for (Recipe r : recipes) //Add to recs all recipes for time i
                if (r.getMealTime(i))
                    recs.add(r);
            html.append("<h2>").append(Recipe.MEALS[i]).append("</h2>");
            HashSet<String> cats = new HashSet<>();
            for (Recipe r : recs) { //Establish the categories
                cats.add(r.getCategory().toLowerCase());
            }

            ArrayList<String> categories = new ArrayList<>();
            categories.addAll(cats);
            @SuppressWarnings("unchecked")
            ArrayList<Recipe>[] subGroups = new ArrayList[categories.size()];
            for (int j = 0; j < subGroups.length; j++) { //Add recipes to an array by category
                subGroups[j] = new ArrayList<>();
                for (Recipe r : recs) {
                    if (r.getCategory().equalsIgnoreCase(categories.get(j)))
                        subGroups[j].add(r);
                }
            }

            for (ArrayList<Recipe> list : subGroups) { //Write the text
                html.append("<h3>").append(list.get(0).getCategory()).append("</h3>");
                for (Recipe r : list) {
                    html.append("<p>").append(r.getName()).append("</p>");
                }
            }

        }
        html.append("</body>" + nL + "</html>");

        createFile(html.toString());
    }

    private void createFile(String html) {
        File file = new File("Menu.html");
        BufferedWriter writer;
        try {
            file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(html);
            writer.close();
            Desktop.getDesktop().browse(file.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createCSS() {
        File cssFile = new File("style.css");
        if (cssFile.exists()) return;
        try {
            cssFile.createNewFile();
        } catch (Exception ignored) {
        }
        StringBuilder cssText = new StringBuilder("@import url('https://fonts.googleapis.com/css?family=Open+Sans:400,700');\n")
                .append("@import url('https://fonts.googleapis.com/css?family=Merriweather:400,700');\n")
                .append("body {\n")
                .append("  /* background-color: white; a*/\n")
                .append("  background-color: #E98446;\n")
                .append("  font-family: \"Open Sans\", sans-serif;\n")
                .append("  padding: 5px 25px;\n")
                .append("  font-size: 18px;\n")
                .append("  margin: 0;\n")
                .append("  color: #444;\n")
                .append("}\n")
                .append("\n")
                .append("h1 {\n")
                .append("text-align: center;")
                .append("  font-family: \"Merriweather\", serif;\n")
                .append("  font-size: 32px;\n")
                .append("}\n")
                .append("\n")
                .append("h2 {\n")
                .append("text-align: center;")
                .append("  font-family: \"Merriweather\", serif;\n")
                .append("  font-size: 28px;\n")
                .append("}\n")
                .append("\n")
                .append("h3 {\n")
                .append("  font-family: \"Merriweather\", serif;\n")
                .append("  font-size: 24px;\n")
                .append("}");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(cssFile));
            writer.write(cssText.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
