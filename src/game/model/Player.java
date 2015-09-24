package game.model;

/**
 */
public class Player {
    String name, color;
    Race race;
    int id, money, food, energy, smithore, crystite, mule, score, properties;

    public Player() {
    }

    public Player(int id, String name, String color, Race race) {
        this.id = id;
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
        this.score = this.money + this.food*30 + this.energy*25 + this.smithore*50;
        this.properties = 0;
    }

    public int getId() { return id; }
    public String getName() {return this.name; }
    public String getColor() { return this.color; }
    public Race getRace() { return this.race; }

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

    public int getScore() {return this.score; }

    public void addProperty() {
        this.properties++;
    }

}
