package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddIngredientPanel extends JPanelKitchen {
    private JTextField nameField;
    private JTextField unitField;
    private JCheckBox hasCount;
    private JComboBox<Priority> priorityJComboBox;

    public AddIngredientPanel() {
        topLabel = new JLabel("Add New Ingredient");
        JButton[] b = {new JButton("Create Ingredient"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new CreateIngredient());
        int[] l = {-1, 1};
        locations = l;
        //size = new Dimension(500, 500);
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel nameLabel = new JLabel("Name: ");
        JLabel hasCountLabel = new JLabel("Has Count: ");
        JLabel unitLabel = new JLabel("Unit: ");
        JLabel priorityLabel = new JLabel("Priority: ");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));
        unitField = new JTextField();
        unitField.setPreferredSize(new Dimension(200, 20));
        hasCount = new JCheckBox();
        hasCount.setSelected(true);
        hasCount.addActionListener(new SwapCheckBox());

        Priority[] pris = {Priority.LOW, Priority.MEDIUM, Priority.HIGH};
        priorityJComboBox = new JComboBox<>(pris);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
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
        add(hasCountLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(hasCount, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy++;
        add(unitLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(unitField, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy++;
        add(priorityLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(priorityJComboBox, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy++;
        add(buttons[0], c);

        c.gridy++;
        add(buttons[1], c);
    }

    private class SwapCheckBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            unitField.setEnabled(hasCount.isSelected());
        }
    }

    private class CreateIngredient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String name = nameField.getText().trim();
            String unit = unitField.getText().trim();
            boolean count = hasCount.isSelected();
            if (name.length() == 0 || (count && unit.length() == 0))
                return; //Stop because they haven't finished
            else if (!Main.isNameAcceptable(name))
                failed("Name can only have basic characters");
            else if ((count && !Main.isNameAcceptable(unit))) {
                failed("Unit can only have basic characters");
            } else if (Main.getKitchen().getIngredient(name) == null) {
                String u = (count) ? unit : "";
                if (Main.getKitchen().addIngredient(new Ingredient(name, 0, u, (Priority) priorityJComboBox.getSelectedItem())))
                    succeeded(name);
                else
                    failed("Unknown error");
            } else
                failed("Ingredient already exists");

        }

        private void failed(String errorMessage) {
            JOptionPane.showMessageDialog(getParent(),
                    errorMessage,
                    "Failed to create ingredient",
                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
            JOptionPane.showMessageDialog(getParent(),
                    "Ingredient " + n + " created successfully",
                    "Ingredient created",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
