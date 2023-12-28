package model;

import controller.WinGUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is where all the logic for the game and data is stored
 *
 * @author Andrew Hwang
 * @version 1
 */

public class GameLogic {
    /**
     *myChanges is a PropertyChangeSupport and is the Property Change is hooked up to.
     */
    private final PropertyChangeSupport myChanges = new PropertyChangeSupport(this);
    /**
     * myRandomDice is a Random and is what assigns the dice a random number.
     */
    private final Random myRandomDice = new Random();
    /***
     * myGameStart is a boolean and is whether the game has started or not.
     */
    private boolean myGameStart;
    /**
     * myFirstRoll is a boolean and is whether it is the first roll.
     */
    private boolean myFirstRoll = true;
    /**
     * myWonGame is a boolean and is whether the game has been won.
     */
    private boolean myWonGame = false;
    /***
     * myPlayerWinCount is an int and is the number of times the player has won.
     */
    private int myPlayerWinCount;
    /***
     * myHouseWinCount is an int and is the number of times the house has won.
     */
    private int myHouseWinCount;
    /**
     * myDiceNumber1 is an int and is the number the dice1 lands on.
     */
    private int myDiceNumber1;
    /**
     * myDiceNumber2 is an int and is the number the dice2 lands on.
     */
    private int myDiceNumber2;
    /**
     * myDiceTotal is an int and is the total of dice1 and dice2.
     */
    private int myDiceTotal;
    /**
     * myBank is an int and is the amount of money in the bank.
     */
    private int myBank;
    /**
     * myBetAmount is an int and is the amount the player bet.
     */
    private int myBetAmount;
    /**
     * myPoint is an int and is the Points of the dice.
     */
    private int myPoints;
    /**
     * myInstance is a GameLogic and is the instance of the game logic/
     */
    private static final GameLogic myInstance = new GameLogic();

    /**
     * GameLogic is a default constructor
     */
    public GameLogic() {
        startGame();
    }

    /**
     * getMyInstance gets the myInstance
     * @return
     * is a GameLogic and is myInstance
     */
    public static GameLogic getMyInstance() {
        return myInstance;
    }

    /**
     * rollDice assigns myDiceNumber 1 and 2 a random int in the bounds of 1-6. Then
     * assigns the dice total. It also checks if it is the first roll or not. If it is
     * then it assigns myPoint the dice total. Then calls the winOrLose method and alerts
     * that the property has changed.
     */
    public void rollDice() {
        myDiceNumber1 = myRandomDice.nextInt(6) + 1;
        myDiceNumber2 = myRandomDice.nextInt(6) + 1;
        myDiceTotal = myDiceNumber1  + myDiceNumber2;
        if(myFirstRoll) {
            myPoints = myDiceTotal;
        }
        winOrLose();
        myChanges.firePropertyChange("diceChanged", null, true);
    }

    /**
     * winOrLose checks if the player has won or not. If the first roll total is a
     * 7 or 11, then they win. If it is a 2, 3, or 12, then they lose. If the total
     * is neither than it checks if the dice total equals the point or 7. If it is
     * equals to 7 the player lose and if it equals the total the player wins.
     */
    public void winOrLose() {
        if(myFirstRoll) {
            if (myDiceTotal == 7 || myDiceTotal == 11) {
                winBet();
                myPlayerWinCount++;
                myFirstRoll = false;
                setWin(true);

            }else if (myDiceTotal == 2 || myDiceTotal == 3 || myDiceTotal == 12){
                loseBet();
                myHouseWinCount++;
                myFirstRoll = false;
                setWin(true);

            }else {
                myFirstRoll = false;
            }
        } else {
            if (myDiceTotal == myPoints) {
                myPlayerWinCount++;
                winBet();
                myFirstRoll = true;
                setWin(true);

            } else if (myDiceTotal == 7) {
                loseBet();
                myHouseWinCount++;
                myFirstRoll = true;
                setWin(true);

            }
        }
    }

    /**
     * winBet updates the bank for when the player wins. It also alerts that
     * the property has changed.
     */
    public void winBet(){
        myBank += myBetAmount;
        myChanges.firePropertyChange("wonBet", null, true);
    }

    /**
     * loseBet updates the bank for when the player loses. It also alerts that
     * the property has changed.
     */
    public void loseBet(){
        myBank -= myBetAmount;
        myChanges.firePropertyChange("loseBet", null, true);

    }

    /**
     * playAgain sets the dice numbers, total, points, and bet amount to 0. Which allows
     * you to play again. It also alerts that the property has changed.
     */
    public void playAgain(){
        myDiceNumber1 = 0;
        myDiceNumber2 = 0;
        myDiceTotal = 0;
        myPoints = 0;
        myBetAmount = 0;
        myChanges.firePropertyChange("playAgain", null, true);

    }

    /**
     * startGame sets gameActive to true. Then, sets points, house and player win count
     * to 0, and set myFirstRoll to true to start the game.
     */
    public void startGame () {
        setGameActive(true);
        myPoints = 0;
        myHouseWinCount = 0;
        myPlayerWinCount = 0;
        myFirstRoll = true;
    }

    /**
     * setGameActive sets myGameActive with the passed in boolean and alerts that
     * the property has changed.
     * @param theGameActive is a boolean and is whether the game has started.
     */
    public void setGameActive(final boolean theGameActive) {
        myGameStart = theGameActive;
        myChanges.firePropertyChange("startActive", null, true);
    }

    /**
     * startGame sets gameActive to false. Then, sets points, house and player win count
     * to 0, and set myFirstRoll to true to end the game.
     */
    public void reset () {
        setResetActive(false);
        myPoints = 0;
        myHouseWinCount = 0;
        myPlayerWinCount = 0;
        myBank =0;
        myFirstRoll = true;
    }

    /**
     * setResetActive sets myGameActive with the passed in boolean and alerts that
     * the property has changed
     * @param theGameActive is a boolean and is whether the game has ended.
     */
    public void setResetActive(final boolean theGameActive){
        myGameStart = theGameActive;
        myChanges.firePropertyChange("resetActive", null, false);

    }

    /**
     * getMyGameStart gets whether the game is started or ended
     * @return
     * is a boolean and is myGameStart
     */
    public boolean getMyGameStart() {
        return myGameStart;
    }

    /**
     * getMyDiceTotal gets the dice total
     * @return
     * is a boolean and is myDiceTotal
     */
    public int getMyDiceTotal() {
        return myDiceTotal;
    }

    /**
     * getMyDiceNumber1 gets the dice 1 number
     * @return
     * is a int and is myDiceNumber1
     */
    public int getMyDiceNumber1() {
            return myDiceNumber1;
    }

    /**
     * getMyDiceNumber2 gets the dice 2 number
     * @return
     * is a int and is myDiceNumber2
     */
    public int getMyDiceNumber2() {
        return myDiceNumber2;
    }

    /**
     * getMyHouseWinCount gets the House win count
     * @return
     * is a int and is the myHouseCount
     */
    public int getMyHouseWinCount() {
        return myHouseWinCount;
    }

    /**
     * setWin resets point to 0 and sets myWonGame to true. Then, it alerts that
     * the property has changed
     * @param gameWon is a boolean and whether the game has been won.
     */
    public void setWin(boolean gameWon){
        myPoints = 0;
        myWonGame = gameWon;
        myChanges.firePropertyChange("gameWon", null, true);
        myChanges.firePropertyChange("playerGameWon", null, true);
        myChanges.firePropertyChange("humanGameWon", null, true);
    }

    /**
     * getMyPlayerWinCount gets the amount of games the player has won.
     * @return
     * is an int and is myPlayerWinCount.
     */
    public int getMyPlayerWinCount() {
        return myPlayerWinCount;
    }

    /**
     * geyMyBank gets the amount in the bank.
     * @return
     * is an int and is myBank.
     */
    public int getMyBank() {
        return myBank;
    }

    /**
     * getMyBetAmount gets the amount of the bet.
     * @return
     * is an int and is myBetAmount.
     */
    public int getMyBetAmount() {
        return myBetAmount;
    }

    /**
     * setMyBetAmount sets myBetAmount with the parameter.
     * @param betAmount is an int and is the bet amount.
     */
    public void setMyBetAmount(int betAmount) {
        myBetAmount = betAmount;
        myChanges.firePropertyChange("bankUpdate", null, myBank);
    }

    /**
     * setMyDiceTotal sets myDiceTotal with the parameter.
     * @param theDiceTotal is an int and is the dice total.
     */
    public void setMyDiceTotal(int theDiceTotal) {
        myDiceTotal = theDiceTotal;
    }

    /**
     * getMyPoints gets the points.
     * @return
     * is an int and is myPoints.
     */
    public int getMyPoints() {
        return myPoints;
    }

    /**
     * setMyBank sets the bank with the parameter.
     * @param theBank is an int and is the amount the user wants to bank.
     */
    public void setMyBank(int theBank) {
        myBank = theBank;
    }

    /**
     * setMyFirstRoll sets myFirstRoll with the parameter.
     * @param theFirstRoll is a boolean and is whether it is the first roll.
     */
    public void setMyFirstRoll(boolean theFirstRoll) {
        myFirstRoll = theFirstRoll;
    }

    /**
     * getMyFirstRoll gets the myFirstRoll.
     * @return
     * is a boolean and is myFirstRoll.
     */
    public boolean getMyFirstRoll() {
        return myFirstRoll;
    }

    /**
     * addPropertyChangeListener lets the PropertyChangeListener's in other classes
     * hear in this class.
     * @param l is a PropertyChangeListener and is the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        myChanges.addPropertyChangeListener(l);
    }
}
