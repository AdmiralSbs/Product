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

    public AddRecipePanel() {
        topLabel = new JLabel("Add New Recipe");
        JButton[] b = {new JButton("Create Recipe"), new JButton("Back"), new JButton("Add Ingredient"),
                new JButton("Remove Ingredient"), new JButton("Add Person"), new JButton("Remove Person")};
        buttons = b;
        buttons[0].addActionListener(new AddRecipePanel.CreateRecipe());
        int[] l = {-1, 1, -1, -1, -1, -1};
        locations = l;
        size = new Dimension(500, 500);
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel nameLabel = new JLabel("Name: ");
        JLabel categoryLabel = new JLabel("Category: ");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));
        categoryField = new JTextField();
        categoryField.setPreferredSize(new Dimension(200, 20));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2; //Change this if necessary
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
            c.anchor = GridBagConstraints.WEST;
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
