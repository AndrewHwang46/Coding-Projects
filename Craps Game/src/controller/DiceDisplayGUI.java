
package controller;

import model.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * This class is where the dice, total, and point values are get created.
 * @author Andrew Hwang
 * @version 1
 */
public class DiceDisplayGUI extends JPanel implements PropertyChangeListener {
    /**
     * myDice1 is a JTextField and where the first dice value get displayed.
     */
    private final JTextField myDice1;
    /**
     * myDice2 is a JTextField and where the second dice value get displayed.
     */
    private final JTextField myDice2;
    /**
     * myDiceTotal is a JTextField and where the dice total value get displayed.
     */
    private final JTextField myDiceTotal;
    /**
     * myPoint is a JTextField and where the point value get displayed.
     */
    private final JTextField myPoint;
    /**
     * myDice1Label is a JLabel and the label for first dice.
     */
    private final JLabel myDice1Label;
    /**
     * myDice2Label is a JLabel and the label for second dice.
     */
    private final JLabel myDice2Label;
    /**
     * myDiceTotalLabel is a JLabel and the label for dice total.
     */
    private final JLabel myDiceTotalLabel;
    /**
     * myPointLabel is a JLabel and the label for point.
     */
    private final JLabel myPointLabel;
    /**
     * myDicePanel is a JPanel is a where all the labels and text fields get added into.
     */
    private final JPanel myDicePanel;
    /**
     * myRoller is a GameLogic and is the instance if GameLogic.
     */
    private final GameLogic myRoller;
    /**
     * TEXT_SIZE is a Dimension and is the Dimension is the size of the text field.
     */
    private static final Dimension TEXT_SIZE = new Dimension(80, 25);

    /**
     * DiceDisplayGUI is the default constructor. This is where all the labels, text
     * fields, and panel are instantiated. Then, all text fields are not focusable
     * and editable, and set their size.
     */
    public DiceDisplayGUI() {
        myRoller = GameLogic.getMyInstance();
        myRoller.addPropertyChangeListener(this);

        myDicePanel = new JPanel();

        myDice1Label = new JLabel("Dice 1: ");
        myDice1 = new JTextField();
        myDice1.setText(String.valueOf(myRoller.getMyDiceNumber1()));
        myDice1.setPreferredSize(TEXT_SIZE);
        myDice1.setEditable(false);
        myDice1.setFocusable(false);

        myDice2Label = new JLabel("Dice 2: ");
        myDice2 = new JTextField();
        myDice2.setText(String.valueOf(myRoller.getMyDiceNumber2()));
        myDice2.setPreferredSize(TEXT_SIZE);
        myDice2.setEditable(false);
        myDice2.setFocusable(false);

        myDiceTotalLabel = new JLabel("Total : ");
        myDiceTotal = new JTextField();
        myDiceTotal.setText(String.valueOf(myRoller.getMyDiceTotal()));
        myDiceTotal.setPreferredSize(TEXT_SIZE);
        myDiceTotal.setEditable(false);
        myDiceTotal.setFocusable(false);

        myPointLabel = new JLabel("Point : ");
        myPoint = new JTextField();
        myPoint.setText(String.valueOf(myRoller.getMyPoints()));
        myPoint.setPreferredSize(TEXT_SIZE);
        myPoint.setEditable(false);
        myPoint.setFocusable(false);


        layoutComponents();
    }

    /**
     * layoutComponents is where all the labels and text fields are being added
     * into the panel. Then, the panel gets added into the frame.
     */
    public void layoutComponents() {
        myDicePanel.setSize(350, 50);
        myDicePanel.setLayout(new FlowLayout());
        myDicePanel.add(myDice1Label);
        myDicePanel.add(myDice1);
        myDicePanel.add(myDice2Label);
        myDicePanel.add(myDice2);
        myDicePanel.add(myDiceTotalLabel);
        myDicePanel.add(myDiceTotal);
        myDicePanel.add(myPointLabel);
        myDicePanel.add(myPoint);
        add(myDicePanel);

    }

    /**
     * propertyChange is where the text fields get updated when the
     * respective property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("diceChanged") ) {
            myDice1.setText(String.valueOf(myRoller.getMyDiceNumber1()));
            myDice2.setText(String.valueOf(myRoller.getMyDiceNumber2()));
            myDiceTotal.setText(String.valueOf(myRoller.getMyDiceTotal()));
            myPoint.setText(String.valueOf(myRoller.getMyPoints()));
        }
        if(evt.getPropertyName().equals("resetActive")){
            myDice1.setText(String.valueOf(0));
            myDice2.setText(String.valueOf(0));
            myDiceTotal.setText(String.valueOf(0));
            myPoint.setText(String.valueOf(0));
        }
    }



}
