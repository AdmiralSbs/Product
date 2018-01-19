package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

public class JAutoComboBox extends JComboBox<ObjectKitchen> {

    //static final ArrayList<ObjectKitchen> ONE_ITEM_LIST = new ArrayList<>();
    private ArrayList<ObjectKitchen> baseList;
    private Vector<ObjectKitchen> currentList = new Vector<>();
    private JTextField text;
    private JComboListener listener;

    JAutoComboBox() {
        super();
        setRenderer(new JListCellRenderer());
        //setModel(new DefaultComboBoxModel<>(currentList));
        text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        listener = new JComboListener(this, currentList);
        text.addKeyListener(listener);
        setList(new ArrayList<>());
    }

    JAutoComboBox(ArrayList<ObjectKitchen> l) {
        super();
        setRenderer(new JListCellRenderer());
        //setModel(new DefaultComboBoxModel<>(currentList));
        text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);

        setList(l);
    }

    void setList(ArrayList<ObjectKitchen> l) {
        //setModel(new DefaultComboBoxModel<>(currentList));
        currentList.removeAllElements();
        baseList = l;
        currentList.addAll(baseList);

        setSelectedIndex(-1);
        setEditable(true);

        text.setText("");
        text.addKeyListener(new JComboListener(this, currentList));
        setModel(new DefaultComboBoxModel<>(currentList));

        setItemsToList();
    }

    private void setItemsToList() {
        //removeAllItems();
        currentList.removeAllElements();
        currentList.addAll(baseList);
        setModel(new DefaultComboBoxModel<>(currentList));
        setSelectedIndex(-1);
        text.setText("");
    }

    public void removeItem(ObjectKitchen o) {
        System.out.println(baseList.remove(o));
        //super.removeItem(o);
        System.out.println(currentList.remove(o));
        setModel(new DefaultComboBoxModel<>(currentList));
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

    public Vector<ObjectKitchen> getCurrentList() { return currentList; }

}
