package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class JAutoComboBox extends JComboBox<ObjectKitchen> {

    //static final ArrayList<ObjectKitchen> ONE_ITEM_LIST = new ArrayList<>();
    private ArrayList<ObjectKitchen> baseList;
    private Vector<ObjectKitchen> currentList = new Vector<>();
    private JTextField text;

    JAutoComboBox() {
        super();
        setRenderer(new JListCellRenderer());
        setModel(new DefaultComboBoxModel<>(currentList));
        text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        text.addKeyListener(new ComboListener(this, currentList));
        setList(new ArrayList<>());
    }

    JAutoComboBox(ArrayList<ObjectKitchen> l) {
        super();
        setRenderer(new JListCellRenderer());
        setModel(new DefaultComboBoxModel<>(currentList));
        text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        text.addKeyListener(new ComboListener(this, currentList));
        setList(l);
    }

    void setList(ArrayList<ObjectKitchen> l) {
        currentList.removeAllElements();
        baseList = l;
        currentList.addAll(baseList);

        setSelectedIndex(-1);
        setEditable(true);

        text.setText("");

        setItemsToList();
    }

    private void setItemsToList() {
        removeAllItems();
        currentList.addAll(baseList);
        setSelectedIndex(-1);
        text.setText("");
    }

    public void removeItem(ObjectKitchen o) {
        baseList.remove(o);
        super.removeItem(o);
        setSelectedIndex(-1);
        text.setText("");
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
