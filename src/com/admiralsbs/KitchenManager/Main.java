package com.admiralsbs.KitchenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private static Kitchen kitchen;
    private static JPanel currentPanel;
    private static final JPanelKitchen[] panels = new JPanelKitchen[13];
    private static Main main;
    static Font TOP_LABEL_FONT;
    @SuppressWarnings("FieldCanBeLocal")
    private static final boolean DEBUG = false;
    private static final int widthBuffer = 50;
    private static final int heightBuffer = 50;
    static final Person NA = new Person("N/A");

    private static final String ACCEPTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "-_ &";

    private Main() {
        //getBuffer();
        JLabel j = new JLabel();
        j.setFont(new Font(j.getFont().getName(), Font.BOLD, 36));
        TOP_LABEL_FONT = j.getFont();
        panels[0] = new KitchenManagerPanel();
        panels[1] = new KitchenEditorPanel();
        panels[2] = new UpdateKitchenPanel();
        panels[3] = new ViewMenuPanel();
        panels[4] = new ViewKitchenPanel();
        panels[5] = new UpdateIngredientPanel();
        panels[6] = new EditRecipePanel();
        panels[7] = new AddIngredientPanel();
        panels[8] = new AddRecipePanel();
        panels[9] = new AddPersonPanel();
        panels[10] = new RemoveIngredientPanel();
        panels[11] = new RemoveRecipePanel();
        panels[12] = new RemovePersonPanel();
        sCP(0);
    }

    public static void main(String[] args) {
        findFile();
        //JAutoComboBox.ONE_ITEM_LIST.add(new Person("!@#"));

        main = new Main();
        //main.setLocation(100, 100);
        main.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowClose());
        main.pack();
        main.setVisible(true);
        //noinspection ConstantConditions
        do {
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
        } while (DEBUG);
    }

    static void setCurrentPanel(int i) {
        main.sCP(i);
        //Other static stuff
    }

    private void sCP(int i) {
        int xLoc, yLoc;
        if (isVisible()) {
            xLoc = getLocationOnScreen().x + (int) getSize().getWidth() / 2;
            yLoc = getLocationOnScreen().y + (int) getSize().getHeight() / 2;
        } else {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            xLoc = dim.width / 2;
            yLoc = dim.height / 2;
        }
        panels[i].switchedTo();
        currentPanel = panels[i];
        setContentPane(currentPanel);
//        setMinimumSize(new Dimension(((JPanelKitchen) getContentPane()).getBaseWidth() + widthBuffer,
//                ((JPanelKitchen) getContentPane()).getBaseHeight() + heightBuffer));
//        setSize(getMinimumSize());
        setMinimumSize(new Dimension(0, 0));
        pack();
        setMinimumSize(new Dimension((int) getSize().getWidth() + widthBuffer,
                (int) getSize().getHeight() + heightBuffer));
        setSize(getMinimumSize());
        //if (isVisible()) {
        setLocation(xLoc - (int) getSize().getWidth() / 2, yLoc - (int) getSize().getHeight() / 2);
        //}
    }

//    private void getBuffer() {
//        pack();
//        widthBuffer = getWidth() - getContentPane().getWidth();
//        heightBuffer = getHeight() - getContentPane().getHeight();
//    }

    private static void findFile() {
        new File("Kitchens").mkdir();
        File[] arrayOfFiles = (new File("Kitchens")).listFiles();
        ArrayList<String> listOfFiles = new ArrayList<>();
        if (arrayOfFiles != null) {
            for (File f : arrayOfFiles) {
                if (f.isFile()) {
                    String name = f.toString();
                    if (name.length() > 8) {
                        if (name.substring(name.length() - 8).equals(".kitchen")) {
                            int index = name.indexOf(File.separator);
                            name = name.substring(index + 1);
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
                int i = JOptionPane.showOptionDialog(null, panel,
                        "Create New Kitchen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                choice = txt.getText();
                //if (choice == null) continue;
                if (!Main.isNameAcceptable(choice.trim()) || choice.trim().equals("Create New Kitchen")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter an acceptable name",
                            "Invalid",
                            JOptionPane.ERROR_MESSAGE);
                    choice = null;
                }
            }
            kitchen = new Kitchen(choice.trim());
        } else {
            ObjectInputStream reader;
            try {
                File file = new File("Kitchens" + File.separator + choice + ".kitchen");
                FileInputStream r1 = new FileInputStream(file);
                BufferedInputStream r2 = new BufferedInputStream(r1);
                reader = new ObjectInputStream(r1);
                kitchen = (Kitchen) reader.readObject();
            } catch (Exception e) {
                System.err.println("Issue reading file");
                e.printStackTrace();
                System.exit(1);
            }
        }
        //sendKitchenInformation();
    }

//    private static void sendKitchenInformation() {
//
//    }


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

    static Kitchen getKitchen() {
        return kitchen;
    }

    private static class WindowClose implements WindowListener {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            File file = new File("Kitchens" + File.separator + kitchen.getOwner() + ".kitchen");

            ObjectOutputStream writer = null;
            try {
                if (file.exists())
                    file.delete();
                file.createNewFile();
                writer = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            } catch (FileNotFoundException e1) {
                System.err.println("File not found");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                writer.writeObject(kitchen);
                writer.close();
            } catch (IOException e1) {
                System.err.println("IOException");
            } catch (Exception e2) {
                System.err.println("OOps");
            }
            System.exit(0);
        }

        @Override
        public void windowOpened(WindowEvent windowEvent) {
        }

        @Override
        public void windowClosed(WindowEvent windowEvent) {
        }

        @Override
        public void windowIconified(WindowEvent windowEvent) {
        }

        @Override
        public void windowDeiconified(WindowEvent windowEvent) {
        }

        @Override
        public void windowActivated(WindowEvent windowEvent) {
        }

        @Override
        public void windowDeactivated(WindowEvent windowEvent) {
        }
    }
}
