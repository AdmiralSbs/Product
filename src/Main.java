import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

class Main extends JFrame {
    private static Kitchen kitchen; //Kitchen object for the entire program
    private static final JPanelKitchen[] panels = new JPanelKitchen[13]; //Contains reference to each panel
    private static Main main; //"Main object" to circumvent various issues
    static Font TOP_LABEL_FONT;
    //private static final boolean DEBUG = false;
    private static final int widthBuffer = 50;
    private static final int heightBuffer = 50;
    public static final Person NA = new Person("N/A"); //Used in the program as a N/A choice

    private static final String ACCEPTABLE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "-_ &";

    private Main() {
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
        main = new Main();
        main.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowClose());
        main.pack();
        main.setVisible(true);


        /*do {
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
        } while (DEBUG);*/


    }

    static void setCurrentPanel(int i) {
        main.sCP(i);
    }

    private void sCP(int i) {
        int xLoc, yLoc;
        if (isVisible()) { //Get current location (middle)
            xLoc = getLocationOnScreen().x + (int) getSize().getWidth() / 2;
            yLoc = getLocationOnScreen().y + (int) getSize().getHeight() / 2;
        } else { //Set location to middle of screen
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            xLoc = dim.width / 2;
            yLoc = dim.height / 2;
        }

        ObjectKitchen.sort(kitchen.getIngredients());
        ObjectKitchen.sort(kitchen.getRecipes());
        ObjectKitchen.sort(kitchen.getPeople());

        panels[i].switchedTo(); //Prepare panel to be shown
        setContentPane(panels[i]);

        setMinimumSize(new Dimension(0, 0)); //Don't restrict pack method
        pack();
        setMinimumSize(new Dimension((int) getSize().getWidth() + widthBuffer,
                (int) getSize().getHeight() + heightBuffer)); //Increase size by buffer
        setSize(getMinimumSize());
        setLocation(xLoc - (int) getSize().getWidth() / 2, yLoc - (int) getSize().getHeight() / 2);
    }


    private static void findFile() {
        new File("Kitchens").mkdir(); //Create Kitchens folder if not previously existent

        File[] arrayOfFiles = (new File("Kitchens")).listFiles();
        ArrayList<String> listOfFiles = new ArrayList<>();
        if (arrayOfFiles != null) {
            for (File f : arrayOfFiles) { //Find correctly named kitchen files and add their names to listOfFiles
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
        while (choice == null) { //Select kitchen file
            choice = (String) JOptionPane.showInputDialog(main,
                    "Kitchen to open:\n",
                    "Customized Dialog",
                    JOptionPane.PLAIN_MESSAGE, null, listOfFiles.toArray(new Object[]{}),
                    startValue);
        }
        if (choice.equals("Create New Kitchen")) { //Have user input name of new kitchen
            String[] options = {"OK"};
            JPanel panel = new JPanel();
            JLabel lbl = new JLabel("Name of Kitchen Owner: ");
            JTextField txt = new JTextField(10);
            panel.add(lbl);
            panel.add(txt);
            choice = null;
            while (choice == null) {
                JOptionPane.showOptionDialog(null, panel,
                        "Create New Kitchen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                choice = txt.getText();
                if (!Main.isNameAcceptable(choice.trim()) || choice.trim().equals("Create New Kitchen")) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter an acceptable name",
                            "Invalid",
                            JOptionPane.ERROR_MESSAGE);
                    choice = null;
                }
            }
            kitchen = new Kitchen(choice.trim());
        } else { //Read selected kitchen file
            ObjectInputStream reader;
            try {
                File file = new File("Kitchens" + File.separator + choice + ".kitchen");
                reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                kitchen = (Kitchen) reader.readObject();
            } catch (Exception e) {
                System.err.println("Issue reading file");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    static boolean isNameAcceptable(String name) { //Check if a string contains only acceptable characters
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

            //Write kitchen object to file, close program
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
                assert writer != null;
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