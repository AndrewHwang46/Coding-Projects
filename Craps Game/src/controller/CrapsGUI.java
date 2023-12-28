package controller;

import java.awt.*;
import javax.swing.*;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;

/**
 * This class creates the main JFrame where all the other GUI gets added.
 * @author Andrew Hwang
 * @version 1
 */
public class CrapsGUI extends JPanel{
    /**
     * CRAPS_LENGTH is the length of the frame.
     */
    private static int CRAPS_LENGTH = 500;
    /**
     * CRAPS_WIDTH is the width of the frame.
     */
    private static int CRAPS_WIDTH = 750;

    /**
     * CrapsGUI is the default constructor
     */
    public CrapsGUI() {
        layoutComponents();
    }

    /**
     * GUICreation is where the frame, an instance of itself, and menu bar is created.
     */
    public static void GUICreation() {

        JFrame startFrame = new JFrame("Craps");
        CrapsGUI crapsUI = new CrapsGUI();
        crapsUI.setOpaque(true);
        startFrame.setContentPane(crapsUI);
        CrapsMenuBar menuBar = new CrapsMenuBar(startFrame);
        startFrame.pack();
        startFrame.setSize(CRAPS_WIDTH,CRAPS_LENGTH);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setLocationRelativeTo(null);
        startFrame.setVisible(true);
    }

    /**
     * layoutComponents is where all the other GUI is being added into the frame
     * with their respective positions on the frame.
     */
    public void layoutComponents(){
        setLayout(new BorderLayout());
        add(new PlayingButtonGUI(), BorderLayout.SOUTH);
        add(new DiceDisplayGUI(), BorderLayout.NORTH);
        add(new WinGUI(), BorderLayout.CENTER);
        add(new SetBankGUI(), BorderLayout.EAST);
        add(new SetBetGUI(), BorderLayout.WEST);

    }


}
