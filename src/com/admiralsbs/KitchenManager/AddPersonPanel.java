package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPersonPanel extends JPanelKitchen {

    private JTextField nameField;

    public AddPersonPanel() {
        topLabel = new JLabel("Add New Person");
        JButton[] b = {new JButton("Create Person"), new JButton("Back")};
        buttons = b;
        buttons[0].addActionListener(new CreatePerson());
        int[] l = {-1, 1};
        locations = l;
        size = new Dimension(350, 390);
        buttonSize = new Dimension(150, 50);
        setUp();


        //JPanel namePanel = new JPanel();
        //namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Name: ");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));
        //nameField.setSize(new Dimension(100,20));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridwidth = 2;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        add(topLabel, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridwidth = 1;
        c.gridy = 1;
        add(nameLabel, c);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 1;
        add(nameField, c);

        c.anchor = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        add(buttons[0], c);

        c.gridy = 3;
        add(buttons[1], c);
    }

    private class CreatePerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            //System.out.println("Did");
            String test = nameField.getText().trim();
            if (test.length() == 0)
                return;
            else if (!Main.isNameAcceptable(test))
                failed("Name can only have basic characters");
            else if (Main.getKitchen().getPerson(test) == null) {
                if (Main.getKitchen().addPerson(new Person(test)))
                    succeeded(test);
                else
                    failed("Unknown error");
            } else
                failed("Person already exists");

        }

        private void failed(String errorMessage) {
            JOptionPane.showMessageDialog(getParent(),
                    errorMessage,
                    "Failed to create person",
                    JOptionPane.ERROR_MESSAGE);
        }

        private void succeeded(String n) {
            JOptionPane.showMessageDialog(getParent(),
                    "Person " + n + " created successfully",
                    "Person created",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}
