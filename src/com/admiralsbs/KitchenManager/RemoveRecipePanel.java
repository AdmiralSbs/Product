package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RemoveRecipePanel extends JPanelKitchen {

    private JAutoComboBox recipeBox;

    public RemoveRecipePanel() {
        topLabel = new JLabel("Remove Recipe");
        JButton[] b = {new JButton("Remove"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new RemoveRecipePanel.RemoveRecipe());
        int[] l = {-1, 1};
        locations = l;
        //size = new Dimension(350, 390);
        buttonSize = new Dimension(150, 50);
        setUp();

        recipeBox = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(recipeBox, c);

        c.gridx = 1;
        add(buttons[0], c);

        c.gridy = 2;
        add(buttons[1], c);
    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> peep = new ArrayList<>();
        for (Recipe p : Main.getKitchen().getRecipes()) {
            peep.add(p);
            //System.out.println(p.getName());
        }
        recipeBox.setList(peep);
        System.out.println("Switched to happened");

    }

    private class RemoveRecipe implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //System.out.println("Did");
            ObjectKitchen selected = (ObjectKitchen) recipeBox.getSelectedItem();
            String test = ((ObjectKitchen)recipeBox.getSelectedItem()).getName();

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
