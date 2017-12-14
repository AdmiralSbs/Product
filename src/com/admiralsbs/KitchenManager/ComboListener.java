package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class ComboListener extends KeyAdapter {

    JAutoComboBox comboBox;
    Vector items;

    public ComboListener(JAutoComboBox jacb, Vector v) {
        super();
        comboBox = jacb;
        items = v;
    }

    public void keyReleased(KeyEvent key) {

        String text = ((JTextField) key.getSource()).getText();
        comboBox.setModel(new DefaultComboBoxModel(getFilteredList(text)));
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
        comboBox.showPopup();
    }

    public Vector getFilteredList(String text) {
        Vector v = new Vector();
        for (Object o : items) {
            if (o.toString().toLowerCase().startsWith(text.toLowerCase())) {
                v.add(o.toString());
            }
        }
        return v;
    }


}
