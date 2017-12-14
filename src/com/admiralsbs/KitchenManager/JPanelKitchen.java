package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class JPanelKitchen extends JPanel {
    protected JLabel topLabel;
    protected JButton[] buttons;
    protected int[] locations;
    protected Dimension size;
    protected Dimension buttonSize;

    protected class BPress implements ActionListener {
        private int i;

        protected BPress(int i) {
            this.i = i;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Main.setCurrentPanel(i);
        }
    }

    protected void setUp() {
        topLabel.setFont(Main.TOP_LABEL_FONT);
        for (int i = 0; i < buttons.length; i++) {
            if (locations[i] >= 0)
                buttons[i].addActionListener(new BPress(locations[i]));
            buttons[i].setPreferredSize(buttonSize);
        }
        setSize(size);
    }

    public Dimension getBaseSize() {
        return size;
    }

    public int getBaseWidth() {
        return (int) size.getWidth();
    }

    public int getBaseHeight() {
        return (int) size.getHeight();
    }

}
