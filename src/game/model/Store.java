package game.model;

import java.util.HashMap;

public class Store {
    HashMap<String, Integer> stock, cost;

    private int money;

    public Store() {
        stock = new HashMap<String, Integer>();
        stock.put("food", 8);
        stock.put("energy", 8);
        stock.put("smithore", 0);
        stock.put("crystite", 0);
        stock.put("mule", 16);

        cost = new HashMap<String, Integer>();
        stock.put("food", 10);
        stock.put("energy", 10);
        stock.put("smithore", 10);
        stock.put("crystite", 10);
        stock.put("mule", 10);
        money = 1000;
    }

    public int getStock(String resource) { return stock.get(resource); }

    public int getCost(String resource) {
        return cost.get(resource);
    }

    public void buy(String resource, int amount, Player player) {
    }

    public void sell(String resource, int amount, Player player) {
    }

    public int getMoney() {
        return money;
    }


}
