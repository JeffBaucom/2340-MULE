/**
 */
public class Player2 {
    String name;
    String color;
    String race;

    public Player2() {
        name = "";
    }
    public Player2(String name, String race, String color) {
        this.name = name;
        this.color = color;
        this.race = race;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getRace() {
        return this.race;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
