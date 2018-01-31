package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveIngredientPanel extends JPanelKitchen {

    private final JAutoComboBox ingredientBox;

    public RemoveIngredientPanel() {
        topLabel = new JLabel("Remove Ingredient");
        buttons = new JButton[]{new JButton("Remove"), new JButton("Back")};
        buttons[0].addActionListener(new RemoveIngredientPanel.RemoveIngredient());
        locations = new int[]{-1, 1};
        buttonSize = new Dimension(150, 50);
        setUp();

        ingredientBox = new JAutoComboBox();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(ingredientBox, c);

        c.gridx = 1;
        add(buttons[0], c);

        c.gridy = 2;
        add(buttons[1], c);
    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> peep = new ArrayList<>();
        peep.addAll(Main.getKitchen().getIngredients());
        ingredientBox.setList(peep);

    }

    private class RemoveIngredient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ObjectKitchen selected = (ObjectKitchen) ingredientBox.getSelectedItem();
            if (selected == null)
                return;
            else {
                String test = (selected).getName();
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + test + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    if (Main.getKitchen().removeIngredient((Ingredient) selected)) {
                        JOptionPane.showMessageDialog(null, "Removed " + test);
                        ingredientBox.removeItem(selected);
                    }
                    else
                        System.out.println("Failed to remove " + test);
                }
            }
        }
    }
    
}