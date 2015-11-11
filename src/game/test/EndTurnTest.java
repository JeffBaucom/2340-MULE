package game.test;
import game.model.Game;
//import game.model.Player;
import junit.framework.*;

// Joe Lesniak
public class EndTurnTest extends TestCase {
    protected Game game;

    protected void setUp(){
        game = new Game(3, 0, "standard");
        game.newPlayer(0, 0, "Player 1", "blue", "Flapper");
        game.newPlayer(1, 0, "Player 2", "red", "Flapper");
        game.newPlayer(2, 0, "Player 3", "green", "Flapper");
        game.startGame();
    }

    public void testRoundNotOver() {
        game.goToTurn(0, 4, 0, 2, 50, false);
        int prevCounter = game.getPlayerCounter();

        game.endTurn();
        assertTrue(game.getPlayerCounter() == prevCounter + 1);
    }

    public void testRoundOver() {
        game.goToTurn(2, 4, 0, 2, 50, false);
        int prevCounter = game.getPlayerCounter();

        game.endTurn();
        assertTrue(game.getPlayerCounter() == 0);
        assertTrue(game.getPlayerCounter() != prevCounter + 1);
    }

    public void testPhaseOne() {
        game.goToTurn(1, 0, 1, 0, 50, false);
        int curPhase = game.getPhase();

        game.endTurn();
        assertTrue(curPhase == game.getPhase());
    }

    public void testPhaseTwo() {
        game.goToTurn(2, 2, 2, 1, 50, false);
        int curPhase = game.getPhase();

        game.endTurn();
        assertTrue(curPhase == game.getPhase());
        assertTrue(game.getPhase() == 1);
    }

    public void testPhaseThreeAllPlayersPassed() {
        game.goToTurn(2, 2, 3, 1, 50, false);
        int curPhase = game.getPhase();

        game.endTurn();
        assertTrue(curPhase != game.getPhase());
        assertTrue(game.getPhase() == 2);
    }

    public void testPhaseThreePastPhaseTwo() {
        game.goToTurn(0, 2, 0, 2, 50, false);
        int curPhase = game.getPhase();

        game.endTurn();
        assertTrue(curPhase == game.getPhase());
        assertTrue(game.getPhase() == 2);
    }

    public void testResetPassCounter() {
        game.goToTurn(2, 0, 2, 0, 50, false);
        int curPassCounter = game.getPassCounter();

        game.endTurn();
        assertTrue(curPassCounter != game.getPassCounter());
        assertTrue(game.getPassCounter() == 0);
    }

    public void testNoResetPassCounter() {
        game.goToTurn(1, 0, 2, 0, 49, false);
        int curPassCounter = game.getPassCounter();

        game.endTurn();
        assertTrue(curPassCounter == game.getPassCounter());
    }
}
