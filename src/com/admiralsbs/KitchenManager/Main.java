package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main extends JFrame {
    private static Kitchen kitchen;
    private static JPanel currentPanel;
    private static JPanelKitchen[] panels = new JPanelKitchen[13];
    private static Main main;
    static Font TOP_LABEL_FONT;
    private static boolean DEBUG = false;
    private static int widthBuffer;
    private static int heightBuffer;

    private static final String ACCEPTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "-_ ";

    private Main() {
        getBuffer();
        JLabel j = new JLabel();
        j.setFont(new Font(j.getFont().getName(), Font.BOLD, 36));
        TOP_LABEL_FONT = j.getFont();
        panels[0] = new KitchenManagerPanel();
        panels[1] = new KitchenEditorPanel();
        panels[2] = new UpdateKitchenPanel();

        panels[7] = new AddIngredientPanel();

        panels[9] = new AddPersonPanel();

        panels[12] = new RemovePersonPanel();
        sCP(0);
    }

    public static void main(String[] args) {
        findFile();
        JAutoComboBox.ONE_ITEM_LIST.add(new Person("!@#"));

        main = new Main();
        main.setLocation(100, 100);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
        while (DEBUG) {
            System.out.print(currentPanel.getWidth() + ", " + currentPanel.getHeight() + "; ");
            System.out.println(main.getWidth() + ", " + main.getHeight());
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
        }
    }

    static void setCurrentPanel(int i) {
        main.sCP(i);
        //Other static stuff
    }

    private void sCP(int i) {
        System.out.println("i: " + i);
        panels[i].switchedTo();

        currentPanel = panels[i];
        //System.out.println("Main is null: " + (main == null));
        setContentPane(currentPanel);
        setMinimumSize(new Dimension(((JPanelKitchen) getContentPane()).getBaseWidth() + widthBuffer,
                ((JPanelKitchen) getContentPane()).getBaseHeight() + heightBuffer));
        setSize(getMinimumSize());
    }

    private void getBuffer() {
        pack();
        widthBuffer = getWidth() - getContentPane().getWidth();
        heightBuffer = getHeight() - getContentPane().getHeight();
    }

    private static void findFile() {
        File[] arrayOfFiles = (new File("Kitchens")).listFiles();
        ArrayList<String> listOfFiles = new ArrayList<>();
        if (arrayOfFiles != null) {
            for (File f : arrayOfFiles) {
                if (f.isFile()) {
                    String name = f.toString();
                    if (name.length() > 8) {
                        if (name.substring(name.length() - 8).equals(".kitchen")) {
                            listOfFiles.add(name.substring(0, name.length() - 8));
                        }
                    }
                }
            }
        }
        listOfFiles.add("Create New Kitchen");
        Object startValue = listOfFiles.get(0);
        String choice = null;
        while (choice == null) {
            choice = (String) JOptionPane.showInputDialog(main,
                    "Kitchen to open:\n"
                            + "",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE, null, listOfFiles.toArray(new Object[]{}),
                    startValue);
        }
        if (choice.equals("Create New Kitchen")) {
            String[] options = {"OK"};
            JPanel panel = new JPanel();
            JLabel lbl = new JLabel("Name of Kitchen Owner: ");
            JTextField txt = new JTextField(10);
            panel.add(lbl);
            panel.add(txt);
            choice = null;
            while (choice == null) {
                choice = Integer.toString(JOptionPane.showOptionDialog(null, panel,
                        "Create New Kitchen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]));
            }
            kitchen = new Kitchen(choice);
        } else {
            ObjectInputStream reader;
            try {
                reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(
                        "Kitchens" + File.separator + choice + ".kitchen")));
                kitchen = (Kitchen) reader.readObject();
            } catch (Exception e) {
                System.err.println("Issue reading file");
                //System.err.println(e);
                System.exit(2);
            }
        }
        sendKitchenInformation();
    }

    private static void sendKitchenInformation() {

    }

    static boolean isNameAcceptable(String name) {
        name = name.trim();
        if (name.length() == 0)
            return false;
        for (char c : name.toCharArray()) {
            if (!ACCEPTABLE_CHARACTERS.contains(c + "")) {
                return false;
            }
        }
        return true;
    }

    static Kitchen getKitchen() { return kitchen; }
}
