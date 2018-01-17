package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRecipePanel extends JPanelKitchen {

    private JTextField nameField;
    private JTextField categoryField;
    private JCheckBox[] mealSelections = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    private String[] meals = {"Breakfast", "Lunch", "Dinner", "Dessert", "Snack"};
    private JAutoComboBox ingredients, selectedIngredients, people, selectedPeople, parentRecipe;

    public AddRecipePanel() {
        topLabel = new JLabel("Add New Recipe");
        JButton[] b = {new JButton("Create Recipe"), new JButton("Back"), new JButton("Add Ingredient"),
                new JButton("Remove Ingredient"), new JButton("Add Person"), new JButton("Remove Person")};
        buttons = b;
        buttons[0].addActionListener(new AddRecipePanel.CreateRecipe());
        int[] l = {-1, 1, -1, -1, -1, -1};
        locations = l;
        //size = new Dimension(500, 500);
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel nameLabel = new JLabel("Name: ");
        JLabel categoryLabel = new JLabel("Category: ");
        JLabel parentLabel = new JLabel("Parent Recipe");
        nameField = new JTextField();
        //nameField.setColumns(50);
        nameField.setPreferredSize(new Dimension(200, 20));
        categoryField = new JTextField();
        //categoryField.setColumns(50);
        categoryField.setPreferredSize(new Dimension(200, 20));

        ingredients = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);
        selectedIngredients = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);
        people = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);
        selectedPeople = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);
        parentRecipe = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);

        buttons[2].addActionListener(new AddIngredient());
        buttons[3].addActionListener(new RemoveIngredient());
        buttons[4].addActionListener(new AddPerson());
        buttons[5].addActionListener(new RemovePerson());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 4; //Change this if necessary
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridy++;
        add(nameLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(nameField, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy++;
        add(categoryLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(categoryField, c);

        for (int i = 0; i < meals.length; i++) {
            c.anchor = GridBagConstraints.EAST;
            c.gridy++;
            c.gridx = 0;
            add(new JLabel(meals[i] + ": "), c);

            c.anchor = GridBagConstraints.WEST;
            c.gridx = 1;
            add(mealSelections[i], c);
        }

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy++;
        add(parentLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(parentRecipe, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy++;
        add(buttons[0], c);

        c.gridy++;
        add(buttons[1], c);

        c.anchor = GridBagConstraints.NORTH;
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 4;
        add(ingredients, c);

        c.gridx = 3;
        add(selectedIngredients, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 5;
        add(buttons[2], c);

        c.gridx = 3;
        add(buttons[3], c);

        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 2;
        c.gridy = 6;
        c.gridheight = 3;
        add(people, c);

        c.gridx = 3;
        add(selectedPeople, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 9;
        add(buttons[4], c);

        c.gridx = 3;
        add(buttons[5], c);
    }

//    private class SwapCheckBox implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            unitField.setEnabled(hasCount.isSelected());
//        }
//    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> ings = new ArrayList<>();
        ings.addAll(Main.getKitchen().getIngredients());
        ingredients.setList(ings);

        ArrayList<ObjectKitchen> peep = new ArrayList<>();
        peep.addAll(Main.getKitchen().getPeople());
        people.setList(peep);

        selectedIngredients.setList(new ArrayList<>());
        selectedPeople.setList(new ArrayList<>());

        ArrayList<ObjectKitchen> recs = new ArrayList<>();
        recs.addAll(Main.getKitchen().getRecipes());
        for (ObjectKitchen r : recs) {
            if (((Recipe) r).isSubRecipe())
                recs.remove(r);
        }
        parentRecipe.setList(recs);
        Person na = new Person("N/A");
        parentRecipe.addItem(na);
        parentRecipe.setSelectedItem(na);

        //System.out.println("Switched to happened");
    }

    private class AddIngredient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Ingredient ing = (Ingredient) ingredients.getSelectedItem();
            if (ing != null) {
                ingredients.removeItem(ing);
                selectedIngredients.addItem(ing);
            }
        }
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

    private class RemoveIngredient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Ingredient ing = (Ingredient) selectedIngredients.getSelectedItem();
            if (ing != null) {
                selectedIngredients.removeItem(ing);
                ingredients.addItem(ing);
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

    private class CreateRecipe implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String test = nameField.getText().trim();
            String cat = categoryField.getText().trim();
            boolean[] mls = new boolean[5];
            for (int i = 0; i < 5; i++)
                mls[i] = mealSelections[i].isSelected();
            Recipe parRec = (parentRecipe.getSelectedItem() instanceof Person) ? null : (Recipe) parentRecipe.getSelectedItem();
            if (test.length() == 0 || cat.length() == 0)
                return;
            else if (!(mls[0] || mls[1] || mls[2] || mls[3] || mls[4])) {
                failed("Select a time");
            }
            else if (Main.isNameAcceptable(test))
                failed("Name can only have basic characters");
            else if (Main.isNameAcceptable(cat))
                failed("Category can only have basic characters");
            else if (selectedIngredients.getBaseList().size() == 0)
                failed("Recipe must require at least one ingredient");
            else if (selectedPeople.getBaseList().size() == 0)
                failed("Recipe must require at least one person");
//            else if (Main.getKitchen().getPerson(test) == null) {
//                if (Main.getKitchen().addPerson(new Person(test)))
//                    succeeded(test);
//                else
//                    failed("Unknown error");
//            } else
//                failed("Recipe already exists");

        }

        private void failed(String errorMessage) {
            JOptionPane.showMessageDialog(getParent(),
                    errorMessage,
                    "Failed to create recipe",
                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
            JOptionPane.showMessageDialog(getParent(),
                    "Recipe for " + n + " created successfully",
                    "Recipe created",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
