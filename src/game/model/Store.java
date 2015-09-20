package game.model;

import java.util.HashMap;

public class Store {
    HashMap<String, Integer> stock;

    private int money;
    private int smithoreCost, foodCost, energyCost, muleCost;

    public Store() {
        stock = new HashMap<String, Integer>();
        stock.put("food", 8);
        stock.put("energy", 8);
        stock.put("smithore", 8);
        stock.put("crystite", 8);
        stock.put("mule", 8);

        foodCost = 10;
        energyCost = 10;
        smithoreCost = 10;
        money = 1000;
    }

    public int getStock(String resource) {
        return stock.get(resource);
    }

    public void buy(String resource, int amount, Player player) {

    }

    public void sell(String resource, int amount, Player player) {

    }

    public int getMoney() {
        return money;
    }


}
