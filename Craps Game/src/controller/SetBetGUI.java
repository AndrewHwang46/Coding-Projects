package controller;

import model.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * This class is where the Bet GUI are get created.
 * @author Andrew Hwang
 * @version 1
 */
public class SetBetGUI extends JPanel implements PropertyChangeListener {
    /**
     * myRoller is a GameLogic and is the instance if GameLogic.
     */
    private final GameLogic myRoller;
    /**
     * BUTTON_SIZE is a Dimension and is the Dimension is the size of the button.
     */
    private static final Dimension BUTTON_SIZE = new Dimension(90, 25);
    /**
     * myBetPanel is a JPanel and is where the buttons and text field gets added into.
     */
    private final JPanel myBetPanel;
    /**
     * myBetText is a JTextField and is where the user inputs a bet amount.
     */
    private final JTextField myBetText;
    /**
     * my1BetButton is a JButton and where the user adds a $1 to their bet.
     */
    private final JButton my1BetButton;
    /**
     * my5BetButton is a JButton and where the user adds a $5 to their bet.
     */
    private final JButton my5BetButton;
    /**
     * my10BetButton is a JButton and where the user adds a $10 to their bet.
     */
    private final JButton my10BetButton;
    /**
     * my25BetButton is a JButton and where the user adds a $25 to their bet.
     */
    private final JButton my25BetButton;
    /**
     * my50BetButton is a JButton and where the user adds a $50 to their bet.
     */
    private final JButton my50BetButton;
    /**
     * my100BetButton is a JButton and where the user adds a $100 to their bet.
     */
    private final JButton my100BetButton;
    /**
     * my250BetButton is a JButton and where the user adds a $250 to their bet.
     */
    private final JButton my250BetButton;
    /**
     * my500BetButton is a JButton and where the user adds a $500 to their bet.
     */
    private final JButton my500BetButton;
    /**
     * myAllInBetButton is a JButton and where the user bets their whole bank amount.
     */
    private final JButton myAllInBetButton;

    /**
     * SetBetGUI is the default constructor. This is where button, text field,
     * and panel are instantiated. Then, all button is not focusable,
     * set their size, and set keybindings.
     * */
    public SetBetGUI() {
        myRoller = GameLogic.getMyInstance();
        myRoller.addPropertyChangeListener(this);

        myBetPanel = new JPanel();
        myBetPanel.setLayout(new BoxLayout(myBetPanel, BoxLayout.PAGE_AXIS));

        myBetText = new JTextField();
        myBetText.setFocusable(false);
        myBetText.setSize(80, 25);
        myBetText.setText("Enter Bet");
        myBetText.setEnabled(false);

        my1BetButton = new JButton("+ $1");
        my1BetButton.setFocusable(false);
        my1BetButton.setSize(BUTTON_SIZE);
        my1BetButton.setEnabled(false);
        my1BetButton.setMnemonic(KeyEvent.VK_1);

        my5BetButton = new JButton("+ $5");
        my5BetButton.setFocusable(false);
        my5BetButton.setSize(BUTTON_SIZE);
        my5BetButton.setEnabled(false);
        my5BetButton.setMnemonic(KeyEvent.VK_2);

        my10BetButton = new JButton("+ $10");
        my10BetButton.setFocusable(false);
        my10BetButton.setSize(BUTTON_SIZE);
        my10BetButton.setEnabled(false);
        my10BetButton.setMnemonic(KeyEvent.VK_3);

        my25BetButton = new JButton("+ $25");
        my25BetButton.setFocusable(false);
        my25BetButton.setSize(BUTTON_SIZE);
        my25BetButton.setEnabled(false);
        my25BetButton.setMnemonic(KeyEvent.VK_4);

        my50BetButton = new JButton("+ $50");
        my50BetButton.setFocusable(false);
        my50BetButton.setSize(BUTTON_SIZE);
        my50BetButton.setEnabled(false);
        my50BetButton.setMnemonic(KeyEvent.VK_5);

        my100BetButton = new JButton("+ $100");
        my100BetButton.setFocusable(false);
        my100BetButton.setSize(BUTTON_SIZE);
        my100BetButton.setEnabled(false);
        my100BetButton.setMnemonic(KeyEvent.VK_6);

        my250BetButton = new JButton("+ $250");
        my250BetButton.setFocusable(false);
        my250BetButton.setSize(BUTTON_SIZE);
        my250BetButton.setEnabled(false);
        my250BetButton.setMnemonic(KeyEvent.VK_7);

        my500BetButton = new JButton("+ $500");
        my500BetButton.setFocusable(false);
        my500BetButton.setSize(BUTTON_SIZE);
        my500BetButton.setEnabled(false);
        my500BetButton.setMnemonic(KeyEvent.VK_8);

        myAllInBetButton = new JButton("All in");
        myAllInBetButton.setFocusable(false);
        myAllInBetButton.setSize(BUTTON_SIZE);
        myAllInBetButton.setEnabled(false);
        myAllInBetButton.setMnemonic(KeyEvent.VK_A);

        actionListener();
        layoutComponents();
    }

    /**
     * actionListeners is where the buttons and text field get their logic assigned.
     * The text field checks whether if the bet being passed in is less than or
     * equal to the bank or if it is not an int. If it is then it sets the bet. If not it
     * sends a warning. The buttons also check if they can add the respective number to
     * the bet. If it can then it sets the bank. If not then it warns the user.
     */
    public void actionListener() {
        myBetText.addActionListener(theE -> {
            if(isInteger(myBetText.getText())) {
                if (Integer.parseInt(myBetText.getText()) <= myRoller.getMyBank()) {
                    myRoller.setMyBetAmount(Integer.parseInt(myBetText.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }else {
                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        my1BetButton.addActionListener(theE -> {

            if (myRoller.getMyBetAmount() + 1 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 1);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));
            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }


        });
        my5BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 5 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 5);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        my10BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 10 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 10);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        my25BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 25 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 25);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        my50BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 50 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 50);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        my100BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 100 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 100);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        my250BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 250 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 250);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        my500BetButton.addActionListener(theE -> {
            if (myRoller.getMyBetAmount() + 500 <= myRoller.getMyBank()) {
                myRoller.setMyBetAmount(myRoller.getMyBetAmount() + 500);
                myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));

            }else {

                JOptionPane.showMessageDialog(null, "Enter an appropriate bet!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }

        });
        myAllInBetButton.addActionListener(theE -> {
            myRoller.setMyBetAmount(myRoller.getMyBank());
            myBetText.setText(String.valueOf(myRoller.getMyBetAmount()));
        });

    }

    /**
     * layoutComponents is where the buttons and text field are being added
     * into the panel. Then, the panel gets added into the frame.
     */
    public void layoutComponents(){
        myBetPanel.add(myBetText);
        myBetPanel.add(my1BetButton);
        myBetPanel.add(my5BetButton);
        myBetPanel.add(my10BetButton);
        myBetPanel.add(my25BetButton);
        myBetPanel.add(my50BetButton);
        myBetPanel.add(my100BetButton);
        myBetPanel.add(my250BetButton);
        myBetPanel.add(my500BetButton);
        myBetPanel.add(myAllInBetButton);
        add(myBetPanel);

    }

    /**
     * isInteger checks if the string is an int or not.
     * @param str is a string and is the string being checked.
     * @return
     * is a boolean and is whether string is an int or not.
     */
    private static boolean isInteger(String str) {
        boolean flag;
        try {

            Integer.parseInt(str);
            flag = true;
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }

    /**
     * propertyChange is where the buttons and text field get updated when the
     * respective property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("startActive") ) {
            myBetText.setFocusable(true);
            myBetText.setEnabled(true);
            my1BetButton.setEnabled(true);
            my5BetButton.setEnabled(true);
            my10BetButton.setEnabled(true);
            my25BetButton.setEnabled(true);
            my50BetButton.setEnabled(true);
            my100BetButton.setEnabled(true);
            my250BetButton.setEnabled(true);
            my500BetButton.setEnabled(true);
            myAllInBetButton.setEnabled(true);
        }

        if(evt.getPropertyName().equals("resetActive")) {
            myBetText.setText("Enter Bet");
            myBetText.setFocusable(false);
            myBetText.setEnabled(false);
            my1BetButton.setEnabled(false);
            my5BetButton.setEnabled(false);
            my10BetButton.setEnabled(false);
            my25BetButton.setEnabled(false);
            my50BetButton.setEnabled(false);
            my100BetButton.setEnabled(false);
            my250BetButton.setEnabled(false);
            my500BetButton.setEnabled(false);
            myAllInBetButton.setEnabled(false);
        }

        if(evt.getPropertyName().equals("diceChanged")) {
            myBetText.setFocusable(false);
            my1BetButton.setEnabled(false);
            my5BetButton.setEnabled(false);
            my10BetButton.setEnabled(false);
            my25BetButton.setEnabled(false);
            my50BetButton.setEnabled(false);
            my100BetButton.setEnabled(false);
            my250BetButton.setEnabled(false);
            my500BetButton.setEnabled(false);
            myAllInBetButton.setEnabled(false);
        }

        if(evt.getPropertyName().equals("playAgain")) {
            myBetText.setText("Enter Bet");
            myBetText.setFocusable(true);
            my1BetButton.setEnabled(true);
            my5BetButton.setEnabled(true);
            my10BetButton.setEnabled(true);
            my25BetButton.setEnabled(true);
            my50BetButton.setEnabled(true);
            my100BetButton.setEnabled(true);
            my250BetButton.setEnabled(true);
            my500BetButton.setEnabled(true);
            myAllInBetButton.setEnabled(true);
        }
    }
}
