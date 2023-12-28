package controller;

import model.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/**
 * This class is where the Win GUI are get created.
 * @author Andrew Hwang
 * @version 1
 */
public class WinGUI extends JPanel implements PropertyChangeListener {
    /**
     * myPlayerWinLabel is a JLabel and is the label for the player win count text field.
     */
    private final JLabel myPlayerWinLabel;
    /**
     * myHouseWinLabel is a JLabel and is the label for the house win count text field.
     */
    private final JLabel myHouseWinLabel;
    /**
     * myPlayerWinTextField is a text field and is where
     * the player win count gets updated.
     */
    private final JTextField myPlayerWinTextField;
    /**
     * myHouseWinTextField is a text field and is where
     * the house win count gets updated.
     */
    private final JTextField myHouseWinTextField;
    /**
     * myRoller is a GameLogic and is the instance if GameLogic.
     */
    private final GameLogic myRoller;
    /**
     * TEXT_SIZE is a Dimension and is the size of the text.
     */
    private static final Dimension TEXT_SIZE = new Dimension(80, 25);

    /**
     * WinGUI is the default constructor. This is where the text fields
     * and panel are instantiated.
     * */
    public WinGUI() {
        myRoller = GameLogic.getMyInstance();
        myRoller.addPropertyChangeListener(this);

        myPlayerWinLabel = new JLabel("Player Win Total: ");
        myPlayerWinTextField = new JTextField();
        myPlayerWinTextField.setText(String.valueOf(myRoller.getMyPlayerWinCount()));
        myPlayerWinTextField.setPreferredSize(TEXT_SIZE);
        myPlayerWinTextField.setEditable(false);
        myPlayerWinTextField.setFocusable(false);

        myHouseWinLabel = new JLabel("House Win Total: ");
        myHouseWinTextField = new JTextField();
        myHouseWinTextField.setText(String.valueOf(myRoller.getMyHouseWinCount()));
        myHouseWinTextField.setPreferredSize(TEXT_SIZE);
        myHouseWinTextField.setEditable(false);
        myHouseWinTextField.setFocusable(false);


        layoutComponents();
    }

    /**
     * layoutComponents is where the text fields are being added
     * into the panel. Then, the panel gets added into the frame.
     */
    public void layoutComponents(){
        add(myPlayerWinLabel);
        add(myPlayerWinTextField);
        add(myHouseWinLabel);
        add(myHouseWinTextField);
    }

    /**
     * propertyChange is where the text fields get updated when the
     * respective property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("playerGameWon")) {
            myPlayerWinTextField.setText(String.valueOf(myRoller.getMyPlayerWinCount()));
        }
        if(evt.getPropertyName().equals("humanGameWon")) {
            myHouseWinTextField.setText(String.valueOf(myRoller.getMyHouseWinCount()));
        }
        if(evt.getPropertyName().equals("resetActive")) {
            myPlayerWinTextField.setText(String.valueOf(0));
            myHouseWinTextField.setText(String.valueOf(0));
        }
    }

}
