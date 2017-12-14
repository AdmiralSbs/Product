package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class JAutoComboBox extends JComboBox<Object> {

    ArrayList<ObjectKitchen> baseList;
    Vector currentList = new Vector();

    public JAutoComboBox(ArrayList<ObjectKitchen> l) {
        baseList = l;
        Collections.addAll(currentList, baseList);

        setModel(new DefaultComboBoxModel(currentList));
        setSelectedIndex(-1);
        setEditable(true);

        JTextField text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        text.setText("");
        text.addKeyListener(new ComboListener(this, currentList));

        setItemsToList();
    }

    private void setItemsToList() {
        removeAllItems();
        Collections.addAll(currentList, baseList);
    }

    public void removeItem(ObjectKitchen o) {
        baseList.remove(o);
        super.removeItem(o);
    }

    public void addItem(ObjectKitchen o) {
        //Add alphabetically
        ObjectKitchen.addAlphabetically(baseList, o);
        //Replace ^ with correct stuff
        setItemsToList();
    }

    public ArrayList<ObjectKitchen> getBaseList() {
        return baseList;
    }


}
