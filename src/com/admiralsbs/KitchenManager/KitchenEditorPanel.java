package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KitchenEditorPanel extends JPanelKitchen {

    public KitchenEditorPanel() {
        topLabel = new JLabel("Kitchen Manager");
        JButton[] b = {new JButton("Add New Ingredient"), new JButton("Add New Recipe"),
                new JButton("Add New Person"), new JButton("Remove Ingredient"),
                new JButton("Remove Recipe"), new JButton("Remove Person"), new JButton("Back")};
        buttons = b;
        int[] l = {7, 8, 9, 10, 11, 12, 0};
        locations = l;
        size = new Dimension(350, 390);
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

        c.gridy = 2;
        add(buttons[1], c);

        c.gridy = 3;
        add(buttons[2], c);

        c.gridx = 1;
        c.gridy = 1;
        add(buttons[3], c);

        c.gridy = 2;
        add(buttons[4], c);

        c.gridy = 3;
        add(buttons[5], c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(buttons[6], c);
    }

}
