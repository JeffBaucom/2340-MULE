package game.test;
import game.model.Game;
import game.model.Player;
import junit.framework.*;

// Written by Matthew Cuevas
public class BuyTileTest extends TestCase {
    protected Game game;

    protected void setUp(){
        game = new Game(3, 0, "standard");
        game.newPlayer(0, 0, "Player 1", "blue", "Flapper");
        game.newPlayer(1, 0, "Player 2", "red", "Flapper");
        game.newPlayer(2, 0, "Player 3", "green", "Flapper");
        game.startGame();
    }

    public void testBuyFirstTile(){
        Player player = game.getCurrentPlayer();
        boolean tileBought = game.getTurn().buyTile(0, 0);

        assertTrue(player.equals(game.getPlayer(game.getMap()
                .getTile(0, 0).getOwner())));
        assertTrue(tileBought == true);
    }

    public void testBuyLastTile(){
        Player player = game.getCurrentPlayer();
        boolean tileBought = game.getTurn().buyTile(4, 8);

        assertTrue(player.equals(game.getPlayer(game.getMap()
                .getTile(4, 8).getOwner())));
        assertTrue(tileBought == true);
    }

    public void testBuyTileOutofBounds() {
        boolean tileBought = game.getTurn().buyTile(-1, -1);
        assertTrue(tileBought == false);
    }

    public void testBuyTilePhase0() {
        game.goToTurn(0, 0, 0, 0, 50, false);
        Player player = game.getCurrentPlayer();
        int money = player.get("money");
        boolean tileBought = game.getTurn().buyTile(0, 0);

        assertTrue(tileBought == true);
        assertTrue(player.get("money") == money);
    }

    public void testBuyTilePhase1HasMoney() {
        game.goToTurn(0, 0, 0, 1, 50, false);
        Player player = game.getCurrentPlayer();
        int money = player.get("money");
        boolean tileBought = game.getTurn().buyTile(0, 0);

        assertTrue(tileBought == true);
        assertTrue(player.get("money") == money - 300);
    }

    public void testBuyTilePhase1HasNoMoney() {
        game.goToTurn(0, 0, 0, 1, 50, false);
        Player player = game.getCurrentPlayer();
        player.set("money", 0);
        int money = player.get("money");
        boolean tileBought = game.getTurn().buyTile(0, 0);

        assertTrue(tileBought == false);
        assertTrue(player.get("money") == money);
    }

    public void testBuyTilePhase2() {
        game.goToTurn(0, 0, 0, 2, 50, false);
        boolean tileBought = game.getTurn().buyTile(0, 0);

        assertTrue(tileBought == false);
    }
}
