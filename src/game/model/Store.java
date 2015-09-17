package game.model;

public class Store {

    private int smithoreStock, energyStock, foodStock, muleStock, money
    private int smithoreCost, foodCost, energyCost, muleCost;

    public Store() {
        smithoreStock = 100;
        energyStock = 100;
        foodStock = 100;
        foodCost = 10;
        energyCost = 10;
        smithoreCost = 10;
        money = 1000;
    }

    public void buySmithore(int amount, Player player) {

    }

    public void buyFood(int amount, Player player) {

    }

    public void buyEnergy(int amount, Player player) {

    }

    public void buyMule(int amount, Player player) {

    }

    public void sellSmithore(int amount, Player player) {

    }

    public void sellFood(int amount, Player player) {

    }

    public void sellEnergy(int amount, Player player) {

    }

    public void sellMule(int amount, Player player) {

    }
    
    public int getFood() {
        return foodStock;
    }

    public int getEnergy() {
        return energyStock;
    }

    public int getSmithore() {
        return smithoreStock;
    }

    public int getMoney() {
        return money;
    }


}
