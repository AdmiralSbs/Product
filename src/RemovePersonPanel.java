import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePersonPanel extends JPanelKitchen {

    private final JAutoComboBox peopleBox;

    public RemovePersonPanel() {
        topLabel = new JLabel("Remove Person");
        buttons = new JButton[]{new JButton("Remove"), new JButton("Back")};
        buttons[0].addActionListener(new RemovePersonPanel.RemovePerson());
        locations = new int[]{-1, 1};
        buttonSize = new Dimension(150, 50);
        setUp();

        peopleBox = new JAutoComboBox();

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
        peopleBox.setList(Main.getKitchen().getPeople());
    }

    private class RemovePerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            ObjectKitchen selected = (ObjectKitchen) peopleBox.getSelectedItem();
            if (selected != null) { //Require a selected person
                String test = (selected).getName();
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove " + test + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    if (Main.getKitchen().removePerson((Person) selected)) {
                        JOptionPane.showMessageDialog(null, "Removed " + test);
                        peopleBox.removeItem(selected);
                    } else
                        System.out.println("Failed to remove " + test);
                }
            }
        }
    }

}