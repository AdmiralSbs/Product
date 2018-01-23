package com.admiralsbs.KitchenManager;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;

public class JListKitchen extends JList<ObjectKitchen> {

    private ArrayList<ObjectKitchen> baseList;
    private DefaultListModel<ObjectKitchen> defaultListModel = new DefaultListModel<>();

    JListKitchen() {
        super();
        setCellRenderer(new JListCellRenderer());
        setModel(defaultListModel);
        setList(new ArrayList<>());
    }

    void setList(ArrayList<ObjectKitchen> l) {
        defaultListModel.removeAllElements();
        baseList = l;
        for (ObjectKitchen ok : baseList)
            defaultListModel.addElement(ok);
        setSelectedIndex(-1);
    }

    public ArrayList<ObjectKitchen> getBaseList() {
        return baseList;
    }

}
