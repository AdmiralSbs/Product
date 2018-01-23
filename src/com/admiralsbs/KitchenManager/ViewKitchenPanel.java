package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewKitchenPanel extends JPanelKitchen {

    JCheckBox[] checkBoxes = {new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox(), new JCheckBox()};
    JListKitchen ingredientsList = new JListKitchen();
    JLabel status = new JLabel();

    public ViewKitchenPanel() {
        topLabel = new JLabel("View Kitchen");
        JButton[] b = {new JButton("Back")};
        buttons = b;
        int[] l = {0};
        locations = l;
        //size = new Dimension(500, 500);
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel lowLabel = new JLabel("Low priority: ");
        JLabel mediumLabel = new JLabel("Medium priority: ");
        JLabel highLabel = new JLabel("High priority: ");
        JLabel availableLabel = new JLabel("Available: ");
        JLabel missingLabel = new JLabel("Missing: ");
        JLabel ingredientsLabel = new JLabel("Ingredients");

        for (int i = 0; i < 5; i++)
            checkBoxes[i].addActionListener(new BoxChanged(i));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(ingredientsLabel, c);

        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridy++;
        c.gridheight = 5;
        add(ingredientsList, c);

        c.gridheight = 1;
        c.gridy = 0;

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy++;
        add(lowLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        add(checkBoxes[0], c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy++;
        add(mediumLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        add(checkBoxes[1], c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy++;
        add(highLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        add(checkBoxes[2], c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy++;
        add(availableLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        add(checkBoxes[3], c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy++;
        add(missingLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 2;
        add(checkBoxes[4], c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy++;
        add(buttons[0], c);

    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> ings = new ArrayList<>();
        ings.addAll(Main.getKitchen().getIngredients());
        ingredientsList.setList(ings);
        for (JCheckBox jCheckBox : checkBoxes)
            jCheckBox.setSelected(true);
    }

    private class BoxChanged implements ActionListener {
        int code;

        public BoxChanged(int i) { code = i; }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (code == 3 && !checkBoxes[3].isSelected()) {
                checkBoxes[4].setSelected(true);
            } else if (code == 4 && !checkBoxes[4].isSelected()) {
                checkBoxes[3].setSelected(true);
            }
            updateList();
        }
    }

    private void updateList() {
        boolean[] checks = new boolean[5];
        for (int i = 0; i < 5; i++) {
            checks[i] = checkBoxes[i].isSelected();
        }
        ArrayList<ObjectKitchen> ings = new ArrayList<>();
        for (ObjectKitchen ok : Main.getKitchen().getIngredients()) {
            Ingredient ing = (Ingredient) ok;
            if (worksWithFilters(ing)) {
                ings.add(ing);
            }
        }
        ingredientsList.setList(ings);
    }

    private boolean worksWithFilters(Ingredient ing) {
        Priority p = ing.getPriority();
        int i = 0;
        switch (p) {
            case LOW:
                i = 0;
                break;
            case MEDIUM:
                i = 1;
                break;
            case HIGH:
                i = 2;
                break;
        }
        if (!checkBoxes[i].isSelected())
            return false;
        i = (ing.isAvailable()) ? 3 : 4;
        return checkBoxes[i].isSelected();
    }
}
