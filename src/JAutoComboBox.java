import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

class JAutoComboBox extends JComboBox<ObjectKitchen> {

    private ArrayList<ObjectKitchen> baseList;
    private final Vector<ObjectKitchen> currentList = new Vector<>();
    private final JTextField text;

    JAutoComboBox() {
        super();
        setRenderer(new JListCellRenderer());
        text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        JComboListener listener = new JComboListener(this, currentList);
        text.addKeyListener(listener);
        setList(new ArrayList<>());
    }



    void setList(ArrayList<ObjectKitchen> l) {
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
        currentList.removeAllElements();
        currentList.addAll(baseList);
        setModel(new DefaultComboBoxModel<>(currentList));
        setSelectedIndex(-1);
        text.setText("");
    }

    public void removeItem(ObjectKitchen o) {
        baseList.remove(o);
        currentList.remove(o);
        setModel(new DefaultComboBoxModel<>(currentList));
        setSelectedIndex(-1);
        text.setText("");
    }

    public void addItem(ObjectKitchen o) {
        ObjectKitchen.addAlphabetically(baseList, o);
        setItemsToList();
    }

    public ArrayList<ObjectKitchen> getBaseList() {
        return baseList;
    }

}