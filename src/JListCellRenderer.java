import javax.swing.*;
import java.awt.*;

class JListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if(value instanceof ObjectKitchen){
            ObjectKitchen person = (ObjectKitchen) value;
            setText(person.getName());
        }
        return this;
    }
}