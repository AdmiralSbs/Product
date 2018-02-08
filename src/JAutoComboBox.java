import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;

class JAutoComboBox extends JComboBox<ObjectKitchen> {

    private ArrayList<ObjectKitchen> baseList; //The items of the combo box
    private final Vector<ObjectKitchen> currentList = new Vector<>(); //The items currently shown
    private final JTextField text; //Its text field
    private JComboListener listener;

    JAutoComboBox() {
        super();
        setRenderer(new JListCellRenderer()); //Custom renders the names of the objects
        text = (JTextField) this.getEditor().getEditorComponent();
        text.setFocusable(true);
        listener = new JComboListener(this, currentList);
        text.addKeyListener(listener);
        setList(new ArrayList<>());
    }

    void setList(ArrayList<ObjectKitchen> l) {
        baseList = l;
        setItemsToList();

        setEditable(true);
        listener = new JComboListener(this, currentList);
    }

    private void setItemsToList() {
        currentList.removeAllElements();
        currentList.addAll(baseList); //Syncs currently shown with the list
        setModel(new DefaultComboBoxModel<>(currentList)); //Resets the model
        setSelectedIndex(-1);
        text.setText("");
    }

    public void removeItem(ObjectKitchen o) {
        baseList.remove(o);
        currentList.remove(o);
        setModel(new DefaultComboBoxModel<>(currentList));
        setSelectedIndex(-1);
        text.setText("");
        //Doesn't invoke setItemsToList (no need to removeAll and addAll)
    }

    public void addItem(ObjectKitchen o) {
        ObjectKitchen.addAlphabetically(baseList, o);
        setItemsToList(); //Cannot easily add alphabetically to a vector
    }

    public ArrayList<ObjectKitchen> getBaseList() {
        return baseList;
    }

}