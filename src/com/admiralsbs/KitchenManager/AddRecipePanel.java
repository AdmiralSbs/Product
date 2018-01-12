package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRecipePanel extends JPanelKitchen {

    private JTextField nameField;
    private JTextField categoryField;
    private JCheckBox[] mealSelections = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    private String[] meals = {"Breakfast", "Lunch", "Dinner", "Desert", "Snack"};
    private JAutoComboBox ingredients, selectedIngredients, people, selectedPeople;

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

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 4; //Change this if necessary
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridy = 1;
        add(nameLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(nameField, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 2;
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
        add(ingredients ,c);

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
        add(people ,c);

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

    private class CreateRecipe implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
//            String test = nameField.getText().trim();
//            if (test.length() == 0)
//                return;
//            else if (Main.isNameAcceptable(test))
//                failed("Name can only have basic characters");
//            else if (Main.getKitchen().getPerson(test) == null) {
//                if (Main.getKitchen().addPerson(new Person(test)))
//                    succeeded(test);
//                else
//                    failed("Unknown error");
//            } else
//                failed("Person already exists");

        }

        private void failed(String errorMessage) {
//            JOptionPane.showMessageDialog(getParent(),
//                    errorMessage,
//                    "Failed to create person",
//                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
//            JOptionPane.showMessageDialog(getParent(),
//                    "Person " + n + " created successfully",
//                    "Person created",
//                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
