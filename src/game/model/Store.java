package game.model;

public class Store {

    private int smithoreStock, energyStock, foodStock, money
    private int smithoreCost, foodCost, energyCost;

    public Store() {
        smithoreStock = 100;
        energyStock = 100;
        foodStock = 100;
        foodCost = 10;
        energyCost = 10;
        smithoreCost = 10;
        money = 1000;
    }

    public void buySmithore(int amount, Player p) {
    }

    public void buyFood(int amount, Player p) {

    }
    public void buyEnergy(int amount, Player p) {

    }

    public void sellSmithore(int amount, Player p) {

    }
    public void sellFood(int amount, Player p) {

    }
    public void sellEnergy(int amount, Player p) {

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
