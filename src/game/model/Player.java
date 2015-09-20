package game.model;

/**
 */
public class Player {
    String name;
    Color color;
    Race race;

    public Player() {
    }
    public Player(String name, Color color, Race race) {
        this.name = name;
        this.color = color;
        this.race = race;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return this.color;
    }
    public void setRace(Race race) {
        this.race = race;
    }
    public Race getRace() {
        return this.race;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
