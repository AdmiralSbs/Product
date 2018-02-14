import javax.swing.*;
import java.awt.*;

public class KitchenManagerPanel extends JPanelKitchen {
    //As the first panel, this will contain all the comments related to repeated code segments

    public KitchenManagerPanel() {
        //Assigns inherited variables
        topLabel = new JLabel("Kitchen Manager");
        buttons = new JButton[]{new JButton("View Menu"), new JButton("Edit Kitchen"),
                new JButton("View Kitchen"), new JButton("Update Kitchen")};
        locations = new int[]{3, 1, 4, 2};
        buttonSize = new Dimension(120, 50);
        setUp(); //Handles inherited variables

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Establishes constraints for each object and adds them
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(topLabel, c);

        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        add(buttons[0], c);

        c.gridx = 0;
        c.gridy = 2;
        add(buttons[1], c);

        c.gridx = 1;
        c.gridy = 1;
        add(buttons[2], c);

        c.gridx = 1;
        c.gridy = 2;
        add(buttons[3], c);
    }

    @Override
    public void switchedTo() { //Disables areas that aren't yet usable
        buttons[0].setEnabled(Main.getKitchen().getRecipes().size() > 0);
        buttons[2].setEnabled(Main.getKitchen().getIngredients().size() > 0);
        buttons[3].setEnabled(Main.getKitchen().getIngredients().size() > 0 || Main.getKitchen().getRecipes().size() > 0);
    }
}