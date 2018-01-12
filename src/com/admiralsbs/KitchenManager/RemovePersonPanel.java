package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class RemovePersonPanel extends JPanelKitchen {

    private JAutoComboBox peopleBox;

    public RemovePersonPanel() {
        topLabel = new JLabel("Remove Person");
        JButton[] b = {new JButton("Remove"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new RemovePersonPanel.RemovePerson());
        int[] l = {-1, 1};
        locations = l;
        //size = new Dimension(350, 390);
        buttonSize = new Dimension(150, 50);
        setUp();

        peopleBox = new JAutoComboBox(JAutoComboBox.ONE_ITEM_LIST);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridy = 1;
        add(peopleBox, c);

        c.gridx = 1;
        add(buttons[0], c);

        c.gridy = 2;
        add(buttons[1], c);
    }

    @Override
    public void switchedTo() {
        ArrayList<ObjectKitchen> peep = new ArrayList<>();
        for (Person p : Main.getKitchen().getPeople()) {
            peep.add(p);
            //System.out.println(p.getName());
        }
        peopleBox.setList(peep);
        System.out.println("Switched to happened");

    }

    private class RemovePerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //System.out.println("Did");
            ObjectKitchen selected = (ObjectKitchen) peopleBox.getSelectedItem();
            String test = ((ObjectKitchen)peopleBox.getSelectedItem()).getName();

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
