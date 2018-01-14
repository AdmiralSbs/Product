package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;

public class UpdateKitchenPanel extends JPanelKitchen {

    public UpdateKitchenPanel() {
        topLabel = new JLabel("Update Kitchen");
        JButton[] b = {new JButton("Update Ingredient"), new JButton("Edit Recipe"), new JButton("Back")};
        buttons = b;
        int[] l = {5, 6, 0};
        locations = l;
//        size = new Dimension(350, 390);
        buttonSize = new Dimension(150, 50);
        setUp();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(buttons[0], c);

        c.gridx = 1;
        add(buttons[1], c);

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        add(buttons[2], c);

    }

    @Override
    public void switchedTo() {
        buttons[0].setEnabled(Main.getKitchen().getIngredients().size() > 0);
        buttons[1].setEnabled(Main.getKitchen().getRecipes().size() > 0);
    }
}
