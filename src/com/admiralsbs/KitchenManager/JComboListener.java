package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

public class JComboListener extends KeyAdapter {

    JAutoComboBox comboBox;
    Vector<ObjectKitchen> items;

    public JComboListener(JAutoComboBox jacb, Vector v) {
        super();
        comboBox = jacb;
        items = v;
    }

    @Override
    public void keyReleased(KeyEvent key) {
        String text = ((JTextField) key.getSource()).getText();
        kR(text);
    }

    public void kR (String text) {
        Vector<ObjectKitchen> newV = getFilteredList(text);
        comboBox.setModel(new DefaultComboBoxModel<>(newV));
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
        comboBox.showPopup();
    }

    public Vector<ObjectKitchen> getFilteredList(String text) {
        Vector<ObjectKitchen> v = new Vector();
        for (ObjectKitchen o : items) {
            if (o.getName().toLowerCase().startsWith(text.toLowerCase())) {
                v.add(o);
            }
        }
        return v;
    }


}
