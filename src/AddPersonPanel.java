import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AddPersonPanel extends JPanelKitchen {

    private final JTextField nameField;

    public AddPersonPanel() {
        topLabel = new JLabel("Add New Person");
        buttons = new JButton[]{new JButton("Create Person"), new JButton("Back")};
        buttons[0].addActionListener(new CreatePerson());
        locations = new int[]{-1, 1};
        buttonSize = new Dimension(150, 50);
        setUp();

        JLabel nameLabel = new JLabel("Name: ");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(200, 20));

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
            String test = nameField.getText().trim();
            if (test.length() == 0); //return
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