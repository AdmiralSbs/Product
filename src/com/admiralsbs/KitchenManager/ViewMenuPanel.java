package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.List;

public class ViewMenuPanel extends JPanelKitchen {

    private JAutoComboBox people, selectedPeople;
    private JCheckBox[] timeCheckBoxes = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};


    public ViewMenuPanel() {
        topLabel = new JLabel("View Menu");
        JButton[] b = {new JButton("Create Menu"), new JButton("Back"), new JButton("Add Person"), new JButton("Remove Person")};
        buttons = b;
        int[] l = {-1, 0, -1, -1};
        locations = l;
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel[] timeLabels = {new JLabel("Breakfast"), new JLabel("Lunch"), new JLabel("Dinner"),
                new JLabel("Dessert"), new JLabel("Snack")};
        JLabel peopleLabel = new JLabel("Unselected People");
        JLabel selectedPeopleLabel = new JLabel("Selected People");

        people = new JAutoComboBox();
        selectedPeople = new JAutoComboBox();

        buttons[2].addActionListener(new AddPerson());
        buttons[3].addActionListener(new RemovePerson());
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMenu();
            }
        });

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
        //System.out.println("X: " + c.gridx + ", Y: " + c.gridy);
        add(buttons[0]);
    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> peep = new ArrayList<>();
        for (Person p : Main.getKitchen().getPeople()) {
            peep.add(p);
        }
        people.setList(peep);
        selectedPeople.setList(new ArrayList<>());
    }

    private class AddPerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Person peep = (Person) people.getSelectedItem();
            if (peep != null) {
                people.removeItem(peep);
                selectedPeople.addItem(peep);
            }
        }
    }

    private class RemovePerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Person peep = (Person) selectedPeople.getSelectedItem();
            if (peep != null) {
                selectedPeople.removeItem(peep);
                people.addItem(peep);
            }
        }
    }

    private void createMenu() {
        createCSS();

        if (selectedPeople.getBaseList().size() == 0)
            return;
        boolean[] times = new boolean[5];
        Vector<Integer> ints = new Vector<>();
        boolean b = false;
        for (int i = 0; i < 5; i++) {
            times[i] = timeCheckBoxes[i].isSelected();
            b = times[i] || b;
            if (times[i]) ints.add(i);
        }
        if (!b) return;

        ArrayList<Recipe> recipes = new ArrayList<>();
        //System.out.println(ints.toString());

        AllRecipes:
        for (Recipe r : Main.getKitchen().getRecipes()) {
            boolean[] tests = {false, false};
            for (Integer i : ints) {
                if (r.getMealTime(i)) {
                    tests[0] = true;
                }
            }
            for (Person p : r.getPeople()) {
                if (selectedPeople.getBaseList().contains(p)) {
                    tests[1] = true;
                }
            }
            for (Ingredient ing : r.getIngredients()) {
                if (!ing.isAvailable()) {
                    continue AllRecipes;
                }
            }
            if (tests[0] && tests[1])
                recipes.add(r);
            //else
                //System.out.println(r.getName() + ", " + Arrays.toString(tests));
        }

        //System.out.println(recipes.size());
        StringBuilder html = new StringBuilder();
        final String nL = "\n";
        html.append("<html>").append(nL).append("<head>").append(nL).append("<title>Menu</title>").append(nL);
        html.append("<link rel=\"stylesheet\" href=\"style.css\">").append(nL);
        html.append("</head>").append(nL).append("<body>").append(nL);
        final String[] timesStrings = {"Breakfast", "Lunch", "Dinner", "Dessert", "Snack"};
        String title = "";
        for (Integer i : ints) {
            title += timesStrings[i] + "/";
        }
        title = title.substring(0,title.length() - 1) + " for ";
        for (ObjectKitchen ok : selectedPeople.getBaseList()) {
            title += ok.getName() + "/";
        }
        title = title.substring(0,title.length() - 1);
        html.append("<h1>").append(title).append("</h1>");

        for (Integer i : ints) {

            ArrayList<Recipe> recs = new ArrayList<>();
            for (Recipe r : recipes)
                if (r.getMealTime(i))
                    recs.add(r);
            //System.out.println(recs.size());
            html.append("<h2>").append(timesStrings[i]).append("</h2>");
            HashSet<String> cats = new HashSet<>();
            for (Recipe r : recs) {
                cats.add(r.getCategory().toLowerCase());
            }

            ArrayList<String> categories = new ArrayList<>();
            categories.addAll(cats);
            //System.out.println(categories.size());
            //System.out.println(categories.get(0));
            ArrayList<Recipe>[] subGroups = new ArrayList[categories.size()];
            for (int j = 0; j < subGroups.length; j++) {
                subGroups[j] = new ArrayList<>();
                for (Recipe r : recs) {
                    if (r.getCategory().equalsIgnoreCase(categories.get(j)))
                        subGroups[j].add(r);
                }
            }

            for (ArrayList<Recipe> list : subGroups) {
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
        BufferedWriter writer = null;
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
        } catch (Exception e) {}
        String cssText = "@import url('https://fonts.googleapis.com/css?family=Open+Sans:400,700');\n" +
                "@import url('https://fonts.googleapis.com/css?family=Merriweather:400,700');\n" +
                "body {\n" +
                "  /* background-color: white; a*/\n" +
                "  background-color: #E98446;\n" +
                "  font-family: \"Open Sans\", sans-serif;\n" +
                "  padding: 5px 25px;\n" +
                "  font-size: 18px;\n" +
                "  margin: 0;\n" +
                "  color: #444;\n" +
                "}\n" +
                "\n" +
                "h1 {\n" +
                "text-align: center;" +
                "  font-family: \"Merriweather\", serif;\n" +
                "  font-size: 32px;\n" +
                "}\n" +
                "\n" +
                "h2 {\n" +
                "text-align: center;" +
                "  font-family: \"Merriweather\", serif;\n" +
                "  font-size: 28px;\n" +
                "}\n" +
                "\n" +
                "h3 {\n" +
                "  font-family: \"Merriweather\", serif;\n" +
                "  font-size: 24px;\n" +
                "}";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(cssFile));
            writer.write(cssText);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
