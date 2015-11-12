package game.model;

import java.util.Random;

/**
 * class representing turns.
 */
public class Turn implements java.io.Serializable {

    /**
     * the current player object.
     */
    private Player player;
    /**
     * the game object.
     */
    private Game game;
    /**
     * the random events object.
     */
    private RandomEvents randomEvents;
    /**
     * the message corresponding to the random event.
     */
    private String randomEventMessage;

    private static final String MONEY = "money";


    /**
     *
     * @param currPlayer the player object who has this turn
     * @param theGame the game object
     */
    public Turn(final Player currPlayer, final Game theGame) {
        this.player = currPlayer;
        this.game = theGame;
        randomEvents = new RandomEvents(game);
        randomEventMessage = "";

        player.updateProduction();
        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;
        if (randInt <= 27) {
            randomEventMessage = randomEvents.getRandomEvent(player);
        }
    }

    /**
     *
     * @param row the map row to place mule.
     * @param col the map column to place mule.
     */
    public final void placeMule(final int row, final int col) {
        game.getMap().getTile(row, col).setMule(player.getMule());
        game.endTurn();
    }

    /**
     * Method that ends turn when Mule is lost.
     */
    public final void loseMule() {
        game.endTurn();
    }

    /**
     *
     * @param row the row of the tile.
     * @param col the column of the tile.
     * @return boolean true if successful or false if unsuccessful.
     */
    public final boolean buyTile(final int row, final int col) {
        boolean boughtTile = false;
        if (row >= 0 && row <= 4 && col >= 0 && col <= 8) {
            if (game.getPhase() == 0) {
                game.getMap().getTile(row, col).setOwner(player);

                boughtTile = true;
                player.addProperty(game.getMap().getTile(row, col));
                game.getMap().removeTile();
                game.endTurn();
            } else if (game.getPhase() == 1 && player.get(MONEY) >= 300) {
                game.getCurrentPlayer().set(MONEY, game.getCurrentPlayer()
                        .get(MONEY) - 300);
                game.getMap().getTile(row, col).setOwner(player);

                boughtTile = true;
                player.addProperty(game.getMap().getTile(row, col));
                game.getMap().removeTile();
                game.endTurn();
            } else {
                game.logEvent("You don't have enough money to purchase land.");
            }
        }

        return boughtTile;
    }

    /**
     * Method that handles gambling at the pub.
     */
    public final void gamble() {
        int timeLeft = game.getTimeLeft();
        Random rand = new Random();
        int roundBonus, timeBonus, round = game.getRoundCounter() + 1;

        if (round >= 1 && round < 3) {
            roundBonus = 50;
        } else if (round >= 4 && round < 7) {
            roundBonus = 100;
        } else if (round >= 8 && round < 11) {
            roundBonus = 150;
        } else {
            roundBonus = 200;
        }

        if (timeLeft >= 37) {
            timeBonus = 200;
        } else if (timeLeft >= 25 && timeLeft < 37) {
            timeBonus = 150;
        } else if (timeLeft >= 12 && timeLeft < 25) {
            timeBonus = 100;
        } else {
            timeBonus = 50;
        }

        int winnings = roundBonus + rand.nextInt(
                timeBonus);
        if (winnings > 250) {
            winnings = 250;
        }

        player.set(MONEY, player.get(MONEY) + winnings);
        game.logEvent(player.getName() + " won " + winnings
                + " gold gambling.");
        game.endTurn();
    }

    /**
     *
     * @param resource the resource being bought
     * @param amount the amount to buy
     * @return true if successful false if unsuccessful
     */
    public final boolean buyStore(final String resource, final int amount) {
        if (game.getStore().getCost(resource) * amount > player.get("money")) {
            return false;
        } else if (amount < 0) {
            return false;
        } else {
            game.getStore().buy(resource, amount, player);
            return true;
        }
    }

    /**
     *
     * @param resource the resource being sold
     * @param amount the amount to sell
     * @return true if successful false if unsuccessful
     */
    public final boolean sellStore(final String resource, final int amount) {
        if (amount > player.get(resource)) {
            game.logEvent("You don't have enough " + resource + ".");
            return false;
        } else if (amount < 0) {
            return false;
        } else {
            game.getStore().sell(resource, amount, player);
            return true;
        }
    }

    /**
     *
     * @param muleType type of mule to buy
     * @return true if successful false if unsuccessful
     */
    public final boolean buyMuleStore(final int muleType) {
        if (player.getMule() == 0) {
            if (game.getStore().getCost("mule") > player.get(MONEY)) {
                game.logEvent("You don't have enough money.");
                return false;
            } else {
                game.getStore().buyMule(muleType, player);
                return true;
            }
        } else {
            game.logEvent("You already have a Mule.");
            return false;
        }
    }

    /**
     *
     * @return the string describing the random event
     */
    public final String getRandomEventMessage() {
        return randomEventMessage;
    }
}
