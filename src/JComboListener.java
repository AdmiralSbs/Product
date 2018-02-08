import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

class JComboListener extends KeyAdapter {
    //Created specifically to handle automatic filtering when the user types into the textBox of a combo box

    private final JAutoComboBox comboBox;
    private final Vector<ObjectKitchen> items; //Always currentList of comboBox

    public JComboListener(JAutoComboBox jacb, Vector<ObjectKitchen> v) {
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
        Vector<ObjectKitchen> newV = getFilteredList(text); //Holds new filtered list of items
        comboBox.setModel(new DefaultComboBoxModel<>(newV));
        comboBox.setSelectedIndex(-1);
        ((JTextField) comboBox.getEditor().getEditorComponent()).setText(text);
        comboBox.showPopup();
    }

    private Vector<ObjectKitchen> getFilteredList(String text) {
        Vector<ObjectKitchen> v = new Vector<>();
        for (ObjectKitchen o : items) { //Filters out objects whose names don't fit the typed text
            if (o.getName().toLowerCase().startsWith(text.toLowerCase())) {
                v.add(o);
            }
        }
        return v;
    }


}