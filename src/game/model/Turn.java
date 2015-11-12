package game.model;

import java.util.Random;

/**
 * All information and actions relevant to a player's turn.
 */
public class Turn implements java.io.Serializable {
    /** The player passing the turn. */
    private Player player;
    /** The current instance of game. */
    private Game game;
    /** The random event message. */
    private String randomEventMessage;

    private static final String MONEY = "money";

    /**
     * Instantiates a Turn.
     * @param player the current player
     * @param game the isntance of game
     */
    public Turn(final Player player, final Game game) {
        this.player = player;
        this.game = game;
        RandomEvents randomEvents = new RandomEvents(game);
        randomEventMessage = "";

        player.updateProduction();
        Random rand = new Random();
        int randInt = rand.nextInt(100) + 1;
        if (randInt <= 27) {
            randomEventMessage = randomEvents.getRandomEvent(player);
        }
    }

    /**
     * Places a mule.
     * @param row the row of the tile
     * @param col the column of the tile
     */
    public final void placeMule(final int row, final int col) {
        game.getMap().getTile(row, col).setMule(player.getMule());
        game.endTurn();
    }

    /**
     * Ends the turn when a player loses a mule.
     */
    public final void loseMule() {
        game.endTurn();
    }

    /**
     * Buys a tile.
     * @param row the row of the tile
     * @param col the column of the tile
     * @return whether the tile was bought
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
     * Gambles and gives the player money.
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

        int winnings = roundBonus + rand.nextInt(timeBonus);
        if (winnings > 250) {
            winnings = 250;
        }

        player.set(MONEY, player.get(MONEY) + winnings);
        game.logEvent(player.getName() + " won " + winnings
                + " gold gambling.");
        game.endTurn();
    }

    /**
     * Buys from the store.
     * @param resource the desired resource
     * @param amount the amount of that resource
     * @return whether the resource was bought
     */
    public final boolean buyStore(final String resource, final int amount) {
        if (game.getStore().getCost(resource) * amount > player.get(MONEY)) {
            return false;
        } else if (amount < 0) {
            return false;
        } else {
            game.getStore().buy(resource, amount, player);
            return true;
        }
    }

    /**
     * Sells to teh store.
     * @param resource the desired resource
     * @param amount the amount
     * @return whether the resource was sold
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
     * Buys a mule from the store.
     * @param muleType type of mule
     * @return whether the mule was bought
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
     * @return the random event message
     */
    public final String getRandomEventMessage() {
        return randomEventMessage;
    }
}
