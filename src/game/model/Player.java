package game.model;

/**
 */
public class Player {
    String name, color;
    Race race;
    int money;
    int food;
    int energy;
    int ore;

    public Player() {
    }

    public Player(String name, String color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;
        if (race == Race.FLAPPER) {
            this.money = 1600;
        } else if (race == Race.HUMAN) {
            this.money = 600;
        } else {
            this.money = 1000;
        }
        this.food = 8;
        this.energy = 4;
        this.ore = 0;
    }

    public void setColor(String color) { this.color = color; }
    public String getColor() { return this.color; }

    public void setRace(Race race) { this.race = race; }
    public Race getRace() { return this.race; }

    public void setName(String name) { this.name = name; }
    public String getName() {return this.name; }

    public int getMoney() {
        return money;
    }

    public int getFood() {
        return food;
    }

    public int getEnergy() {
        return energy;
    }

    public int getOre() {
        return ore;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

}
