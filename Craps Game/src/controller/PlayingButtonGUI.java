package controller;

import model.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


/**
 * This class is where the roll dice and play again buttons are get created.
 * @author Andrew Hwang
 * @version 1
 */
public class PlayingButtonGUI extends JPanel implements PropertyChangeListener {
    /**
     * myRoller is a GameLogic and is the instance if GameLogic.
     */
    private final GameLogic myRoller;
    /**
     * myRollButton is a JButton and is where the user can roll the dice.
     */
    private final JButton myRollButton;
    /**
     * myPlayAgainButton is a JButton and is where the user can play again.
     */
    private final JButton myPlayAgainButton;
    /**
     * myRollButtonPanel is JPanel and is the panel where the buttons get added to.
     */
    private final JPanel myRollButtonPanel;
    /**
     * BUTTON_SIZE is a Dimension and is the Dimension is the size of the button.
     */
    private final static Dimension BUTTON_SIZE = new Dimension(90, 25);

    /**
     * PlayingButtonGUI is the default constructor. This is where buttons
     * and panel are instantiated. Then, all buttons are not focusable
     * and disabled, set their size, and set keybindings.
     */
    public PlayingButtonGUI() {
        myRoller = GameLogic.getMyInstance();
        myRoller.addPropertyChangeListener(this);

        myRollButton = new JButton("Roll Dice");
        myRollButton.setSize(BUTTON_SIZE);
        myRollButton.setFocusable(false);
        myRollButton.setEnabled(false);
        myRollButton.setMnemonic(KeyEvent.VK_O);

        myPlayAgainButton = new JButton("Play Again");
        myPlayAgainButton.setSize(BUTTON_SIZE);
        myPlayAgainButton.setFocusable(false);
        myPlayAgainButton.setEnabled(false);
        myPlayAgainButton.setMnemonic(KeyEvent.VK_P);


        myRollButtonPanel = new JPanel();
        layoutComponents();
        addListeners();
    }

    /**
     * actionListeners is where the buttons get assigned their jobs. Roll dice checks
     * if a bet has been placed. If not then it warns the user.
     */
    public void addListeners(){
            myRollButton.addActionListener(theE -> {
                if (myRoller.getMyBetAmount() > 0){
                    myRoller.rollDice();
                }else {
                    JOptionPane.showMessageDialog(null, "Enter a bet first!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            });

            myPlayAgainButton.addActionListener(theE -> {
                System.out.println(myRoller.getMyBank());
                myRoller.playAgain();
                myRollButton.setEnabled(true);
                myPlayAgainButton.setEnabled(false);
            });


    }

    /**
     * layoutComponents is where the buttons are being added
     * into the panel. Then, the panel gets added into the frame.
     */
    public void layoutComponents(){
        myRollButton.setSize(90,25);
        myRollButtonPanel.add(myRollButton);
        myRollButtonPanel.add(myPlayAgainButton);
        add(myRollButtonPanel);
    }

    /**
     * propertyChange is where the button get updated when the
     * respective property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("startActive")) {
            myRollButton.setEnabled(true);

        }

        if (evt.getPropertyName().equals("gameWon")) {
            myRollButton.setEnabled(false);
            myPlayAgainButton.setEnabled(true);
        }

        if (evt.getPropertyName().equals("resetActive")) {
            myRollButton.setEnabled(false);
            myPlayAgainButton.setEnabled(false);
        }
    }
}
