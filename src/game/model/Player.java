package game.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 */
public class Player implements java.io.Serializable{
    private String name, color, race;
    private int id, properties, mule;
    private HashMap<String, Integer> resources;
    private ArrayList<Tile> tiles;

    public Player(int id, int difficulty, String name, String color, String
            race) {
        resources = new HashMap<String, Integer>();
        tiles = new ArrayList<Tile>();

        this.id = id;
        this.name = name;
        this.color = color;
        this.race = race;

        if (race == "flapper") {
            resources.put("money", 1600);
        } else if (race == "human") {
            resources.put("money", 600);
        } else {
            resources.put("money", 1000);
        }
        if (difficulty == 0) {
            resources.put("food", 8);
            resources.put("energy", 4);
            resources.put("smithore", 0);
            resources.put("crystite", 0);
        } else {
            resources.put("food", 4);
            resources.put("energy", 2);
            resources.put("smithore", 0);
            resources.put("crystite", 0);
        }
    }

    public int getId() { return id; }

    public String getName() {return this.name; }

    public String getColor() { return this.color; }

    public String getRace() { return this.race; }

    public int get(String resource) {
        return resources.get(resource);
    }

    public void set(String resource, int amount) {
        resources.replace(resource, amount);
    }

    public void add(String resource, int amount) {
        resources.replace(resource, resources.get(resource) + amount);
    }

    public int getMule() { return mule; }

    public void setMule(int mule) { this.mule = mule; }

    public int getScore() {
        return this.properties * 500 + resources.get("money") + resources.get("energy") * 25 +
                resources.get("smithore") * 50 + resources.get("food") * 30;
    }

    public String getResourceString() {
        String resourceString = "";
        resourceString += "Money: " + resources.get("money") +
                "\t\tSmithore: " + resources.get("smithore") + "\n";
        resourceString += "Food: " + resources.get("food") + "\t\t\tCrystite: " + resources.get("crystite") +
                "\n";
        resourceString += "Energy: " + resources.get("energy") + "\t\t\tMule: " + mule +
                "\n";

        return resourceString;
    }

    public void updateProduction() {
        for (Tile t : tiles) {
            if (get("energy") > 0) {
                add("food", t.getProduction("food"));
                add("energy", t.getProduction("energy"));
                add("crystite", t.getProduction("crystite"));
                add("smithore", t.getProduction("smithore"));

                if (!(t.getMule() == 0)) {
                    add("energy", -1);
                }
            }
        }
    }

    public void addProperty(Tile tile) {
        tiles.add(tile);
        this.properties++;
    }

}
