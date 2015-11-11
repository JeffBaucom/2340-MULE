package game.test;
import game.model.Game;
import game.model.Store;
import game.model.Player;
import junit.framework.*;
//Sumit Patel
//testing buyMuleStore method of the Turn class

public class BuyMuleTest extends TestCase {
    protected Game game;
    protected Store theStore;

    protected void setUp() {
        game = new Game(2, 0, "standard");
        game.newPlayer(0, 0, "Player 1", "red", "Human");
        game.newPlayer(1, 0, "Player 2", "yellow", "Flapper");
        game.startGame();
        theStore = game.getStore();
    }

    public void testBuyMule() {
        Player p = game.getCurrentPlayer();
        int curPlayerMule = p.getMule();
        int curStoreMule = theStore.getStock("mule");
        boolean boughtMule = game.getTurn().buyMuleStore(1);
        assertTrue(p.getMule() == 1);
        assertTrue(theStore.getStock("mule") == curStoreMule - 1);
        assertTrue(boughtFood);

    }

    public void testAlreadyHasMule() {
        Player p = game.getCurrentPlayer();
        p.setMule(1);
        boolean boughtMule = game.getTurn().buyMuleStore(1);
        assertFalse(boughtMule);
    }

    public void testInsuffFunds() {
        Player p = game.getCurrentPlayer();
        p.set("money", 0);
        boolean boughtMule = game.getTurn().buyMuleStore(1);
        assertFalse(boughtMule);
    }
}
