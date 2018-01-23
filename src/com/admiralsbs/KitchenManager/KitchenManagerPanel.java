package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;

public class KitchenManagerPanel extends JPanelKitchen {

    public KitchenManagerPanel() {
        topLabel = new JLabel("Kitchen Manager");
        buttons = new JButton[]{new JButton("View Menu"), new JButton("Edit Kitchen"),
                new JButton("View Kitchen"), new JButton("Update Kitchen")};
        locations = new int[]{3, 1, 4, 2};
        //size = new Dimension(320, 240);
        buttonSize = new Dimension(120, 50);
        setUp();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(topLabel, c);

        //c.weightx = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(buttons[0], c);

        //c.weightx = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(buttons[1], c);

        //c.weightx = 1;
        c.gridx = 1;
        c.gridy = 1;
        add(buttons[2], c);

        //c.weightx = 1;
        c.gridx = 1;
        c.gridy = 2;
        add(buttons[3], c);

        /*button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        add(button, c);

        button = new JButton("5");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        add(button, c);*/
    }

    @Override
    public void switchedTo() {
        buttons[0].setEnabled(Main.getKitchen().getRecipes().size() > 0);
        buttons[2].setEnabled(Main.getKitchen().getIngredients().size() > 0);
        buttons[3].setEnabled(Main.getKitchen().getIngredients().size() > 0 || Main.getKitchen().getRecipes().size() > 0);
    }
}
