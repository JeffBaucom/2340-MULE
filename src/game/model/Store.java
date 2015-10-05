package game.model;

import java.util.HashMap;

public class Store {
    Game game;
    HashMap<String, Integer> stock, cost;

    private int money;

    public Store(Game game) {
        stock = new HashMap<String, Integer>();
        stock.put("food", 8);
        stock.put("energy", 8);
        stock.put("smithore", 0);
        stock.put("crystite", 0);
        stock.put("mule", 16);

        cost = new HashMap<String, Integer>();
        cost.put("food", 10);
        cost.put("energy", 10);
        cost.put("smithore", 10);
        cost.put("crystite", 10);
        cost.put("mule", 100);
        money = 1000;

        this.game = game;
    }

    public int getStock(String resource) { return stock.get(resource); }

    public int getCost(String resource) {
        return cost.get(resource);
    }

    public void buy(String resource, int amount, Player player) {
        if (amount <= stock.get(resource)) {
            int initialAmt = stock.get(resource);
            player.set(resource, player.get(resource) + amount);
            stock.replace(resource, initialAmt - amount);
            player.set("money", player.get("money") - amount * cost.get
                    (resource));
        } else {
            game.logEvent("The store does not have enough " + resource + ".");
        }
    }

    public void sell(String resource, int amount, Player player) {
        int initialAmt = stock.get(resource);
        player.set(resource, player.get(resource) - amount);
        stock.replace(resource, initialAmt + amount);
        player.set("money", player.get("money") + amount * cost.get(resource));
    }

    public void buyMule(int muleType, Player player) {
            if (stock.get("mule") > 0 && player.getMule() == 0) {
                player.setMule(muleType);
                stock.replace("mule", stock.get("mule") - 1);
                int muleCost = cost.get("mule") + muleType*25;
                player.set("money", player.get("money") - muleCost);
            }
    }

    public int getMoney() {
        return money;
    }


}
