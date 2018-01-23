package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateIngredientPanel extends JPanelKitchen {

    private JAutoComboBox nameComboBox;
    private JTextField unitField, countField;
    private JCheckBox hasCount, isAvailable;
    private JComboBox<Priority> priorityJComboBox;

    public UpdateIngredientPanel() {
        topLabel = new JLabel("Update Ingredient");
        JButton[] b = {new JButton("Update Ingredient"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new UpdateIngredientPanel.UpdateIngredient());
        int[] l = {-1, 2};
        locations = l;
        //size = new Dimension(500, 500);
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel nameLabel = new JLabel("Name: ");
        JLabel countLabel = new JLabel("Count: ");
        JLabel hasCountLabel = new JLabel("Has Count: ");
        JLabel unitLabel = new JLabel("Unit: ");
        JLabel priorityLabel = new JLabel("Priority: ");
        JLabel isAvailableLabel = new JLabel("Is Available");

        nameComboBox = new JAutoComboBox();

        countField = new JTextField();
        countField.setPreferredSize(new Dimension(150, 20));
        unitField = new JTextField();
        unitField.setPreferredSize(new Dimension(150, 20));
        hasCount = new JCheckBox();
        hasCount.setSelected(true);
        hasCount.addActionListener(new UpdateIngredientPanel.SwapCheckBox());
        isAvailable = new JCheckBox();
        isAvailable.setEnabled(false);

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
        add(nameComboBox, c);

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
        add(countLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(countField, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 0;
        c.gridy++;
        add(isAvailableLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(isAvailable, c);

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

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> ings = new ArrayList<>();
        ings.addAll(Main.getKitchen().getIngredients());
        nameComboBox.setList(ings);
        nameComboBox.addActionListener(new RecipeSelected());
    }

    private class SwapCheckBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            unitField.setEnabled(hasCount.isSelected());
            countField.setEnabled(hasCount.isSelected());
            isAvailable.setEnabled(!hasCount.isSelected());
            Ingredient ing = (Ingredient) nameComboBox.getSelectedItem();
            if (ing == null) return;
            isAvailable.setSelected(ing.getCount() >= 1 || ing.getCount() == -1);
        }
    }

    private class RecipeSelected implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Ingredient ing = (Ingredient) nameComboBox.getSelectedItem();
            if (ing == null) return;
            boolean c = ing.getUnit().equals("");
            hasCount.setSelected(!c);
            countField.setEnabled(!c);
            unitField.setEnabled(!c);
            isAvailable.setEnabled(c);
            unitField.setText(ing.getUnit());
            countField.setText((!c) ? ing.getCount() + "" : "");
            priorityJComboBox.setSelectedItem(ing.getPriority());
            isAvailable.setSelected(ing.getCount() >= 1 || ing.getCount() == -1);

        }
    }

    private class UpdateIngredient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Ingredient ing = (Ingredient) nameComboBox.getSelectedItem();
            String unit = unitField.getText().trim();
            boolean c = hasCount.isSelected();
            boolean a = isAvailable.isSelected();
            int count = 0;
            if (c) {
                try {
                    count = Integer.parseInt(countField.getText().trim());
                } catch (Exception NumberFormatException) {
                    failed("Count must be an integer");
                    return;
                }
            }
            if (ing == null || (c && unit.length() == 0))
                return; //Stop because they haven't finished
            else if ((c && !Main.isNameAcceptable(unit))) {
                failed("Unit can only have basic characters");
            } else if (c && count < 0) {
                failed("Count must be at least 0");
            } else {
                if (c) ing.setCount(count);
                else ing.setCount((a) ? -1 : -2);
                String u = (c) ? unit : "";
                ing.setUnit(u);
                ing.setPriority((Priority) priorityJComboBox.getSelectedItem());
                succeeded(ing.getName());
            }

        }

        private void failed(String errorMessage) {
            JOptionPane.showMessageDialog(getParent(),
                    errorMessage,
                    "Failed to update ingredient",
                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
            JOptionPane.showMessageDialog(getParent(),
                    "Ingredient " + n + " updated successfully",
                    "Ingredient created",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }


}
