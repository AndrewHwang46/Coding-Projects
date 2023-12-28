package controller;

import model.GameLogic;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

/**
 * This class is where the menu bar GUI is being created.
 * @author Andrew Hwang
 * @version 1
 */
public class CrapsMenuBar extends JMenuBar {
    /**
     * myRoller is a GameLogic and is the instance if GameLogic.
     */
    private final GameLogic myRoller;
    /**
     * myStartGame is an Action is the start game tab in the menu bar.
     */
    private Action myStartGame;
    /**
     * myResetGame is an Action is the reset game tab in the menu bar.
     */
    private Action myResetGame;
    /**
     * myExitGame is an Action is the exit game tab in the menu bar.
     */
    private Action myExitGame;
    /**
     * myAbout is an Action is the about tab in the menu bar.
     */
    private Action myAbout;
    /**
     * myRules is an Action is the rules tab in the menu bar.
     */
    private Action myRules;
    /**
     * myShortcuts is an Action is the shortcuts in menu bar.
     */
    private Action myShortcuts;

    /**
     * CrapsMenuBar is a default constructor of CrapsMenuBar and is where
     * the menu bar is instantiated.
     * @param crapsFrame is a JFrame and where the menu bar get attached to.
     */
    public CrapsMenuBar(JFrame crapsFrame) {
        myRoller = GameLogic.getMyInstance();

        JMenuBar myMenuBar = new JMenuBar();

        JMenu myGameMenu = new JMenu("Game");
        JMenu myHelpMenu = new JMenu("Help");

        actionListener(crapsFrame);
        myGameMenu.add(myStartGame);
        myGameMenu.add(myResetGame);
        myResetGame.setEnabled(false);
        myGameMenu.add(myExitGame);

        myHelpMenu.add(myAbout);
        myHelpMenu.add(myRules);
        myHelpMenu.add(myShortcuts);

        myMenuBar.add(myGameMenu);
        myMenuBar.add(myHelpMenu);

        crapsFrame.setJMenuBar(myMenuBar);
    }

    /**
     * actionListener is where all the Actions get assigned their jobs.
     * @param crapsFrame is a JFrame and where the menu bar get attached to.
     */
    public void actionListener(JFrame crapsFrame) {

        myStartGame = new AbstractAction("Start") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myRoller.getMyBank() > 0){
                    myRoller.startGame();
                    myStartGame.setEnabled(false);
                    myResetGame.setEnabled(true);

                }else {
                    JOptionPane.showMessageDialog(null, "You must set Bank first!",
                            "Warning", JOptionPane.WARNING_MESSAGE);

                }
            }
        };
        myStartGame.putValue(Action.SHORT_DESCRIPTION, "Start's Game");
        myStartGame.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('S',
                        InputEvent.CTRL_DOWN_MASK));

        myResetGame = new AbstractAction("Reset") {
            @Override
            public void actionPerformed(ActionEvent e) {
                myRoller.reset();
                myStartGame.setEnabled(true);
                myResetGame.setEnabled(false);
            }
        };
        myResetGame.putValue(Action.SHORT_DESCRIPTION, "Reset's Game");
        myResetGame.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('R',
                        InputEvent.CTRL_DOWN_MASK));

        myExitGame = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userInput = JOptionPane.showConfirmDialog(null,  "Are you sure you want to exit?"
                        , "Exit Game", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(userInput == 0) {
                    crapsFrame.dispose();
                }
            }
        };
        myExitGame.putValue(Action.SHORT_DESCRIPTION, "Exit's Game");
        myExitGame.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('E',
                        InputEvent.CTRL_DOWN_MASK));

        myAbout = new AbstractAction("About") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authorMessage = "<html>"
                        + "<div style='text-align:center;'>"
                        + "<img src='file:Images/Author.gif' width='480' height='600'><br>"
                        + "</div>"
                        + "<b>Application:</b> Craps Game<br>"
                        + "<b>Version:</b> 1.0.0<br>"
                        + "<b>Author:</b> Andrew Hwang<br>"
                        + "<b>Java Version:</b> " + System.getProperty("java.version") + "<br>"
                        + "<b>Description:</b> This application is a Craps game.<br>";


                JOptionPane.showMessageDialog(null, authorMessage, "About this application" , JOptionPane.INFORMATION_MESSAGE);
            }
        };
        myAbout.putValue(Action.SHORT_DESCRIPTION, "About Game");
        myAbout.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('B',
                        InputEvent.CTRL_DOWN_MASK));

        myRules = new AbstractAction("Rules") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, """
                        Rules:\s
                         You roll two dices. If the sum of the two dices are 7 or 11 then\s
                         the player wins. If it is 2,3, or 12 then the House wins. If it is 4, 5, 6, 7, 8, or 10\s
                        then the player must roll the 'point' total before they roll a 7.\s
                        If the player rolls 7 before the point value the Player loses and theHouse wins.""",
                        "Rules of Craps", JOptionPane.INFORMATION_MESSAGE);

            }
        };
        myRules.putValue(Action.SHORT_DESCRIPTION, "Rules of Craps");
        myRules.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('U',
                        InputEvent.CTRL_DOWN_MASK));
        myShortcuts = new AbstractAction("Shortcuts") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, """
                                Shortcuts:\s
                                 Start: Ctrl + S\s
                                 Reset: Ctrl + R\s
                                 Exit: Ctrl + E\s
                                About: Ctrl + B\s
                                 Rules: Ctrl + U\s
                                Shortcuts Ctrl: + O\s
                                 Set Bank: Alt + N\s
                                 Roll Dice: Alt + O\s
                                 Play Again: Alt + P\s
                                Bet 1: Alt + 1\s
                                 Bet 5: Alt + 2\s
                                 Bet 10: Alt + 3\s
                                Bet 25: Alt + 4\s
                                 Bet 50:  Alt + 5\s
                                 Bet 100: Alt + 6\s
                                 Bet 250: Alt + 7\s
                                 Bet 500: Alt + 8\s
                                 Bet All In: Alt + A"""
                        , "List of Shortcuts", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        myShortcuts.putValue(Action.SHORT_DESCRIPTION, "List of Shortcuts");
        myShortcuts.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke('O',
                        InputEvent.CTRL_DOWN_MASK));
    }

}
