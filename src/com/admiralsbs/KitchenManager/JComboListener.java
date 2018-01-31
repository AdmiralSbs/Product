package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class JComboListener extends KeyAdapter {

    private final JAutoComboBox comboBox;
    private final Vector<ObjectKitchen> items;

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

    private void kR(String text) {
        Vector<ObjectKitchen> newV = getFilteredList(text);
        comboBox.setModel(new DefaultComboBoxModel<>(newV));
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
        comboBox.showPopup();
    }

    private Vector<ObjectKitchen> getFilteredList(String text) {
        Vector<ObjectKitchen> v = new Vector();
        for (ObjectKitchen o : items) {
            if (o.getName().toLowerCase().startsWith(text.toLowerCase())) {
                v.add(o);
            }
        }
        return v;
    }


}