package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;

public class KitchenManagerPanel extends JPanelKitchen {

    public KitchenManagerPanel() {
        topLabel = new JLabel("Kitchen Manager");
        buttons = new JButton[]{new JButton("View Menu"), new JButton("Edit Kitchen"),
                new JButton("View Kitchen"), new JButton("Update Kitchen")};
        locations = new int[]{3, 1, 4, 2};
        buttonSize = new Dimension(120, 50);
        setUp();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(buttons[0], c);

        c.gridx = 0;
        c.gridy = 2;
        add(buttons[1], c);

        c.gridx = 1;
        c.gridy = 1;
        add(buttons[2], c);

        c.gridx = 1;
        c.gridy = 2;
        add(buttons[3], c);
    }

    @Override
    public void switchedTo() {
        buttons[0].setEnabled(Main.getKitchen().getRecipes().size() > 0);
        buttons[2].setEnabled(Main.getKitchen().getIngredients().size() > 0);
        buttons[3].setEnabled(Main.getKitchen().getIngredients().size() > 0 || Main.getKitchen().getRecipes().size() > 0);
    }
}