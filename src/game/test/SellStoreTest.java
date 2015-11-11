package game.test;

import game.model.Game;
import game.model.Player;
import game.model.Store;
import junit.framework.*;
//Jeffrey Baucom

public class SellStoreTest extends TestCase{
    protected Game game;
    protected Store store;



    public void setUp() {
        game = new Game(2, 0, "standard");
        game.newPlayer(0, 0, "P1", "red", "Human");
        game.newPlayer(1, 0, "P2", "yellow", "Human");
        game.startGame();
        store = game.getStore();
    }

    public void testSellFood() {
        Player p = game.getCurrentPlayer();
        int currFood = p.get("food");
        int currMoney = p.get("money");
        int currStoreFood = store.getStock("food");
        int amount = 3;
        boolean test = game.getTurn().sellStore("food", amount);
        assertTrue(test);
        assertTrue(store.getStock("food") == currStoreFood + amount);
        assertTrue(p.get("money") == currMoney + (amount*store.getCost("food")));
        assertTrue(p.get("food") == currFood - amount);
    }

    public void testSellAllFood() {
        Player p = game.getCurrentPlayer();
        int currFood = p.get("food");
        int currMoney = p.get("money");
        int currStoreFood = store.getStock("food");
        int amount = currFood;
        boolean test = game.getTurn().sellStore("food", amount);
        assertTrue(test);
        assertTrue(store.getStock("food") == currStoreFood + amount);
        assertTrue(p.get("money") == currMoney + (amount*store.getCost("food")));
        assertTrue(p.get("food") == currFood - amount);
    }

    public void testSellInsuffFood() {
        Player p = game.getCurrentPlayer();
        int currFood = p.get("food");
        int currMoney = p.get("money");
        int currStoreFood = store.getStock("food");
        int amount = currFood + 1;
        boolean test = game.getTurn().sellStore("food", amount);
        assertFalse(test);
        assertTrue(store.getStock("food") == currStoreFood);
        assertTrue(p.get("money") == currMoney);
        assertTrue(p.get("food") == currFood);

    }

}
