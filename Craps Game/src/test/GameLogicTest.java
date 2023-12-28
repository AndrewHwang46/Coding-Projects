package test;

import model.GameLogic;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

    private GameLogic testingGameLogic;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testingGameLogic = GameLogic.getMyInstance();

    }

    @org.junit.jupiter.api.Test
    void rollDice() {
        testingGameLogic.rollDice();
        assertFalse(testingGameLogic.getMyFirstRoll(), "rollDice failed");
    }

    @org.junit.jupiter.api.Test
    void winOrLose() {
        testingGameLogic.setMyDiceTotal(7);
        testingGameLogic.winOrLose();
        assertEquals(1, testingGameLogic.getMyPlayerWinCount(), "winOrLose Player Win Failed");

        testingGameLogic.setMyFirstRoll(true);
        testingGameLogic.setMyDiceTotal(2);
        testingGameLogic.winOrLose();
        assertEquals(1, testingGameLogic.getMyHouseWinCount(), "winOrLose House Win Failed");
    }

    @org.junit.jupiter.api.Test
    void winBet() {
        testingGameLogic.setMyBank(100);
        testingGameLogic.setMyBetAmount(10);
        testingGameLogic.winBet();
        assertEquals(110, testingGameLogic.getMyBank(), "winBet Failed");
    }

    @org.junit.jupiter.api.Test
    void loseBet() {
        testingGameLogic.setMyBetAmount(10);
        testingGameLogic.setMyBank(100);
        testingGameLogic.loseBet();
        assertEquals(90, testingGameLogic.getMyBank(), "loseBet failed");
    }

    @org.junit.jupiter.api.Test
    void getMyBank() {
        testingGameLogic.setMyBank(100);
        assertEquals(100, testingGameLogic.getMyBank(), "getMyBank failed");
    }

    @org.junit.jupiter.api.Test
    void getMyBetAmount() {
        testingGameLogic.setMyBetAmount(10);
        assertEquals(10, testingGameLogic.getMyBetAmount(),"getMyBetAmount");
    }
}