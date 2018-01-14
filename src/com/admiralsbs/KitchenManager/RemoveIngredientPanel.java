package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveIngredientPanel extends JPanelKitchen {

    private JAutoComboBox ingredientBox;

    public RemoveIngredientPanel() {
        topLabel = new JLabel("Remove Ingredient");
        JButton[] b = {new JButton("Remove"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new RemoveIngredientPanel.RemoveIngredient());
        int[] l = {-1, 1};
        locations = l;
        //size = new Dimension(350, 390);
        buttonSize = new Dimension(150, 50);
        setUp();

        ingredientBox = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);

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
        for (Ingredient p : Main.getKitchen().getIngredients()) {
            peep.add(p);
            //System.out.println(p.getName());
        }
        ingredientBox.setList(peep);
        System.out.println("Switched to happened");

    }

    private class RemoveIngredient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //System.out.println("Did");
            ObjectKitchen selected = (ObjectKitchen) ingredientBox.getSelectedItem();
            String test = ((ObjectKitchen)ingredientBox.getSelectedItem()).getName();

        }

        private void failed(String errorMessage) {
            JOptionPane.showMessageDialog(getParent(),
                    errorMessage,
                    "Failed to",
                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
            JOptionPane.showMessageDialog(getParent(),
                    "Worked 1",
                    "Worked 2",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
    
}