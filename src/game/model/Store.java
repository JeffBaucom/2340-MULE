package game.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Store class.
 */
public class Store implements java.io.Serializable {
    private static final String MONEY = "money";
    private static final String MULE = "mule";

    /**
     * The Game this Store will be part of.
     */
    private final Game game;

    /**
     * Holds the stock and unit price of each item.
     */
    private Map<String, Integer> stock, cost;

    /**
     * The amount of money.
     */
    private int money;

    /**
     * Sets up the Store.
     * @param game The game this Store is a part of.
     */
    public Store(final Game game) {
        this.game = game;

        stock = new HashMap<String, Integer>();
        if (this.game.getDifficulty() == 0) {
            stock.put("food", 16);
            stock.put("energy", 16);
            stock.put("smithore", 0);
            stock.put("crystite", 0);
            stock.put(MULE, 25);
        } else {
            stock.put("food", 8);
            stock.put("energy", 8);
            stock.put("smithore", 8);
            stock.put("crystite", 0);
            stock.put(MULE, 16);
        }

        cost = new HashMap<String, Integer>();
        cost.put("food", 10);
        cost.put("energy", 10);
        cost.put("smithore", 10);
        cost.put("crystite", 10);
        cost.put(MULE, 100);
        money = 1000;

    }

    /**
     * Gets the amount of a resource currently in stock.
     * @param resource The resource sold by the Store.
     * @return The amount of this resource left in stock.
     */
    public final int getStock(final String resource) {
        return stock.get(resource);
    }

    /**
     * Gets the cost of a resource currently in stock.
     * @param resource The resource sold by the Store.
     * @return The unit price of this resource.
     */
    public final int getCost(final String resource) {
        return cost.get(resource);
    }

    /**
     * Allows a player to buy a specific amount of a specific resource.
     * @param resource The resource to be bought.
     * @param amount The amount of the resource to be bought.
     * @param player The player buying the resource.
     */
    public final void buy(final String resource, final int amount,
                          final Player player) {
        if (amount <= stock.get(resource)) {
            if (amount > 0) {
                int initialAmt = stock.get(resource);
                player.set(resource, player.get(resource) + amount);
                stock.replace(resource, initialAmt - amount);
                player.set(MONEY, player.get(MONEY)
                        - amount * cost.get(resource));
                game.logEvent(player.getName() + " has purchased "
                        + amount + " " + resource + ".");
            }
        } else {
            game.logEvent("The store does not have enough " + resource + ".");
        }
    }

    /**
     * Sells a specific amount of a resource back to the Store.
     * @param resource The resource to be sold back to the Store.
     * @param amount The amount of the resource to be sold back.
     * @param player The player selling the resource to the Store.
     */
    public final void sell(final String resource, final int amount,
                           final Player player) {
        int initialAmt = stock.get(resource);
        player.set(resource, player.get(resource) - amount);
        stock.replace(resource, initialAmt + amount);
        player.set(MONEY, player.get(MONEY) + amount * cost.get(resource));
    }

    /**
     * Buys a Mule.
     * @param muleType The type of MULE being bought.
     * @param player The player buying the mule.
     */
    public final void buyMule(final int muleType, final Player player) {
        if (stock.get(MULE) > 0 && player.getMule() == 0) {
            player.setMule(muleType);
            stock.replace(MULE, stock.get(MULE) - 1);
            int muleCost = cost.get(MULE) + muleType * 25;
            player.set(MONEY, player.get(MONEY) - muleCost);
        }
    }

    /**
     * Returns the amount of money the Store has.
     * @return The amount of money the Store has.
     */
    public final int getMoney() {
        return money;
    }
}
