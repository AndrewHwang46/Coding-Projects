package controller;
import model.GameLogic;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is where the Bank GUI are get created.
 * @author Andrew Hwang
 * @version 1
 */
public class SetBankGUI extends JPanel implements PropertyChangeListener {
    /**
     * myBankPanel is a JPanel and is where the button and text field gets added into.
     */
    private final JPanel myBankPanel;
    /**
     * mySetBankText is a JTextField and is where the user inputs a bank amount.
     */
    private final JTextField mySetBankText;
    /**
     * myBankSetButton and is where the user submits the user's bank amount.
     */
    private final JButton myBankSetButton;
    /**
     * myRoller is a GameLogic and is the instance if GameLogic.
     */
    private final GameLogic myRoller;

    /**
     * SetBankGUI is the default constructor. This is where button, text field,
     * and panel are instantiated. Then, all button is not focusable,
     * set their size, and set keybindings.
     * */
    public SetBankGUI() {
        myRoller = GameLogic.getMyInstance();
        myRoller.addPropertyChangeListener(this);

        myBankPanel = new JPanel();
        myBankPanel.setLayout(new BoxLayout(myBankPanel, BoxLayout.PAGE_AXIS));


        mySetBankText = new JTextField();
        mySetBankText.setSize(50, 25);
        mySetBankText.setText("Enter Bank");

        myBankSetButton = new JButton("Set");
        myBankSetButton.setFocusable(false);
        myBankSetButton.setMnemonic(KeyEvent.VK_N);
        layoutComponents();
        actionListener();

    }

    /**
     * actionListeners is where the button submits the string in the text field. It first
     * checks if the game has not started and the text fields is not blank. Then, it
     * checks if the string is an int. If it goes through the if statement it submits the
     * bank then disables the text field and button. If not it warns the user.
     */
    public void actionListener(){
        myBankSetButton.addActionListener(theE -> {
            if((!myRoller.getMyGameStart() || !mySetBankText.getText().isBlank())
                    && isInteger(mySetBankText.getText())){
                myRoller.setMyBank(Integer.parseInt(mySetBankText.getText()));
                mySetBankText.setFocusable(false);
                myBankSetButton.setEnabled(false);
            }else {
                JOptionPane.showMessageDialog(null, "Enter an appropriate bank!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    /**
     * layoutComponents is where the button and text field are being added
     * into the panel. Then, the panel gets added into the frame.
     */
    public void layoutComponents(){
        
        myBankPanel.add(mySetBankText);
        myBankPanel.add(myBankSetButton);

        add(myBankPanel);
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
     * propertyChange is where the text field and button get updated when the
     * respective property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("resetActive") ) {
            mySetBankText.setFocusable(true);
            mySetBankText.setEnabled(true);
            myBankSetButton.setEnabled(true);
            mySetBankText.setText("Enter Bank");
        }
        if(evt.getPropertyName().equals("wonBet")){
            mySetBankText.setText(String.valueOf(myRoller.getMyBank()));
        }
        if(evt.getPropertyName().equals("loseBet")){
            mySetBankText.setText(String.valueOf(myRoller.getMyBank()));
        }
    }
}
