import javax.swing.*;
import java.util.ArrayList;

class JListKitchen extends JList<ObjectKitchen> {
    //Largely structured off JAutoComboBox
    //Utilizes the model directly due to no need to check its contents

    private final DefaultListModel<ObjectKitchen> defaultListModel = new DefaultListModel<>();

    JListKitchen() {
        super();
        setCellRenderer(new JListCellRenderer());
        setModel(defaultListModel);
        setList(new ArrayList<>());
    }

    void setList(ArrayList<ObjectKitchen> l) {
        defaultListModel.removeAllElements();
        for (ObjectKitchen ok : l)
            defaultListModel.addElement(ok);
        setSelectedIndex(-1);
    }

}