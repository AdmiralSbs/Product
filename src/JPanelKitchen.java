import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

abstract class JPanelKitchen extends JPanel {
    JLabel topLabel;
    JButton[] buttons;
    int[] locations;
    Dimension buttonSize;

    class BPress implements ActionListener {
        private final int i;

        BPress(int i) {
            this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Main.setCurrentPanel(i);
        }
    }

    void setUp() {
        topLabel.setFont(Main.TOP_LABEL_FONT);
        for (int i = 0; i < buttons.length; i++) {
            if (locations[i] >= 0)
                buttons[i].addActionListener(new BPress(locations[i]));
            buttons[i].setPreferredSize(buttonSize);
        }
    }

    public void switchedTo() { }

}