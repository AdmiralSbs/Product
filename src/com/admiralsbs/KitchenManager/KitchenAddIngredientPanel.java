package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KitchenAddIngredientPanel extends JPanelKitchen {
    private JTextField nameField;
    private JTextField unitField;
    private JCheckBox hasCount;


    public KitchenAddIngredientPanel() {
        topLabel = new JLabel("Add New Ingredient");
        JButton[] b = {new JButton("Create Ingredient"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new CreateIngredient());
        int[] l = {-1, 1};
        locations = l;
        size = new Dimension(500, 500);
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel nameLabel = new JLabel("Name: ");
        JLabel hasCountLabel = new JLabel("Has Count: ");
        JLabel unitLabel = new JLabel("Unit: ");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));
        unitField = new JTextField();
        unitField.setPreferredSize(new Dimension(200, 20));
        hasCount = new JCheckBox();
        hasCount.setSelected(true);
        hasCount.addActionListener(new SwapCheckBox());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
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
        add(hasCountLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(hasCount, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy = 3;
        add(unitLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(unitField, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 4;
        add(buttons[0], c);

        c.gridy = 5;
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
            String test = nameField.getText().trim();
            if (test.length() == 0)
                return;
            else if (Main.isNameAcceptable(test))
                failed("Name can only have basic characters");
            else if (Main.getKitchen().getPerson(test) == null) {
                if (Main.getKitchen().addPerson(new Person(test)))
                    succeeded(test);
                else
                    failed("Unknown error");
            } else
                failed("Person already exists");

        }

        private void failed(String errorMessage) {
            JOptionPane.showMessageDialog(getParent(),
                    errorMessage,
                    "Failed to create person",
                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
            JOptionPane.showMessageDialog(getParent(),
                    "Person " + n + " created successfully",
                    "Person created",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
