import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class JPanelKitchen extends JPanel {
    //Standardizes the panels and handles repetitive code

    JLabel topLabel; //Title label for each panel
    JButton[] buttons;
    int[] locations; //Numbers correspond to locations in the main's panel array, -1 signals not to add a BPress
    Dimension buttonSize; //Allows for modification per panel

    class BPress implements ActionListener { //Calls for a panel switch
        private final int i;

        BPress(int i) {
            this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Main.setCurrentPanel(i);
        }
    }

    void setUp() { //Called in a subclass's constructor
        topLabel.setFont(Main.TOP_LABEL_FONT);
        for (int i = 0; i < buttons.length; i++) {
            if (locations[i] >= 0)
                buttons[i].addActionListener(new BPress(locations[i]));
            buttons[i].setPreferredSize(buttonSize);
        }
    }

    public void switchedTo() {
    } //Overridden if necessary

}