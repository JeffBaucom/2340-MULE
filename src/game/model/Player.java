package game.model;

/**
 */
public class Player {
    String name, color;
    Race race;
    int money;
    int food;
    int energy;
    int smithore;
    int crystite;
    int mule;

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
        this.smithore = 0;
        this.crystite = 0;
        this.mule = 0;
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

    public int getSmithore() {
        return smithore;
    }

    public int getCrystite() { return crystite; }

    public int getMule() { return mule; }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setSmithore(int smithore) {
        this.smithore = smithore;
    }

    public void setCrystite(int crystite) { this.crystite = crystite;}

    public void setMule(int mule) { this.mule = mule; }
}
