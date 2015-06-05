package infdta01.pkg1;

/*
 * TabDemo.java
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TabDemo implements ActionListener{
    final static String USERITEMTAB = "User - Item";
    final static String ITEMITEMTAB = "Item - Item";
    protected JButton calculateButton;
    protected JTextArea resultTextArea;
    UserPreference uP;
    JComboBox comboBox;
    JComboBox methodBox;

    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();

        //Create the "cards".
        JPanel card1 = new JPanel() {
            //Make the panel wider than it really needs, so
            //the window's wide enough for the tabs to stay
            //in one row.
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                size.width = 700;
                size.height = 250;
                return size;
            }
        };

        /**
         * Dataset dropdown
         */
        String workingDir = System.getProperty("user.dir");
        File folder = new File(workingDir);
        File[] listOfFiles = folder.listFiles();

        card1.add(new JLabel("Dataset: "));
        DefaultComboBoxModel fileNameDropdown = new DefaultComboBoxModel();

        fileNameDropdown.addElement(null);
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (listOfFiles[i].getName().endsWith(".txt") || listOfFiles[i].getName().endsWith(".data")) {
                    fileNameDropdown.addElement(listOfFiles[i].getName());
                }
            }
        }

        comboBox = new JComboBox(fileNameDropdown);
        card1.add(comboBox);
        comboBox.addActionListener(this);

        /**
         * Method dropdown
         */
        JLabel methodLabel = new JLabel("Method: ");
        DefaultComboBoxModel methodDropdown = new DefaultComboBoxModel();
        String[] listOfMethods = {null, "Pearson", "Euclidean", "Cosine", "Project Rating", "Nearest Neighbor"};

        for (int i = 0; i < listOfMethods.length; i++) {
            methodDropdown.addElement(listOfMethods[i]);
        }

        methodBox = new JComboBox(methodDropdown);
        card1.add(methodBox);
        methodBox.addActionListener(this);

        calculateButton = new JButton("Calculate");
        calculateButton.setMnemonic(KeyEvent.VK_M);
        card1.add(calculateButton);
        calculateButton.addActionListener(this);

        Font font = new Font("Verdana", Font.BOLD, 20);
        resultTextArea = new JTextArea(10, 20);
        resultTextArea.setEditable(false);
        resultTextArea.setFont(font);
        card1.add(resultTextArea);

        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));

        tabbedPane.addTab(USERITEMTAB, card1);
        tabbedPane.addTab(ITEMITEMTAB, card2);

        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == comboBox) {
            try {
                String fileName = (String) comboBox.getSelectedItem();
                uP = new UserPreference(fileName);
                System.out.println("file: " + fileName);
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }

        if (event.getSource() == calculateButton) {
            if (uP == null) {
                JOptionPane.showMessageDialog(null, "Select a file to calculate with");
                return;
            } else {
                // calculate here
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() throws FileNotFoundException {
        //Create and set up the window.
        JFrame frame = new JFrame("Recommendation Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TabDemo demo = new TabDemo();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}