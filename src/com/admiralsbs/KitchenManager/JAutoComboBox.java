package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class JAutoComboBox extends JComboBox<ObjectKitchen> {

    static final ArrayList<ObjectKitchen> ONE_ITEM_LIST = new ArrayList<>();
    private ArrayList<ObjectKitchen> baseList;
    private Vector<ObjectKitchen> currentList = new Vector<>();

    JAutoComboBox(ArrayList<ObjectKitchen> l) {
        super();
        setRenderer(new JListCellRenderer());
        setList(l);
    }

    void setList(ArrayList<ObjectKitchen> l) {
        setModel(new DefaultComboBoxModel<>(currentList));
        baseList = l;
        currentList.addAll(baseList);

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
        currentList.addAll(baseList);
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
