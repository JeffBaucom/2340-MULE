package game.test;
import game.model.Game;
import game.model.Player;
import game.model.Store;
import junit.framework.*;

// Nathaniel Barnwell, CS 2340, Georgia Tech

public class BuyStoreTest extends TestCase {
    protected Game game;
    protected Store theStore;

    protected void setUp() {
        game = new Game(2, 0, "standard");
        game.newPlayer(0, 0, "Guy 1", "red", "Human");
        game.newPlayer(1, 0, "Guy 2", "yellow", "Flapper");
        game.startGame();
        theStore = game.getStore();
    }

    public void testBuyFood() {
        Player p = game.getCurrentPlayer();
        int curPlayerFood = p.get("food");
        int curStoreFood = theStore.getStock("food");
        boolean BoughtFood = game.getTurn().buyStore("food", 1);
        assertTrue(p.get("food") == curPlayerFood + 1);
        assertTrue(theStore.getStock("food") == curStoreFood - 1);
        assertTrue(BoughtFood);
    }

    public void testAllRemainingFood() {
        Player p = game.getCurrentPlayer();
        int curPlayerFood = p.get("food");
        int curStoreFood = theStore.getStock("food");
        boolean BoughtFood = game.getTurn().buyStore("food", curStoreFood);
        assertEquals(0, theStore.getStock("food"));
        assertEquals(curPlayerFood + curStoreFood, p.get("food"));
        assertTrue(BoughtFood);
    }

    public void testInsuffFunds() {
        Player p = game.getCurrentPlayer();
        p.set("money", 0);
        int money = p.get("money");
        boolean BoughtFood = game.getTurn().buyStore("food", 1);
        assertFalse(BoughtFood);
    }

    public void testOutOfBounds() {
        Player p = game.getCurrentPlayer();
        boolean BoughtFood = game.getTurn().buyStore("food", -2);
        assertFalse(BoughtFood);
    }
}
