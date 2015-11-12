package game.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that contains all information relevant to a player.
 */
public class Player implements java.io.Serializable {
    /** Strings representing player information. */
    private String name, color, race;

    /**
     * ints representing the player's id, number of properties, and mule
     * respectively.
     */
    private int id, properties, mule;

    /** The player's resources and amounts. */
    private HashMap<String, Integer> resources;

    /** The player's owned tiles. */
    private ArrayList<Tile> tiles;

    /**
     * Constructs a player.
     * @param id the player's id
     * @param difficulty the game's difficulty
     * @param name the player's name
     * @param color the player's color
     * @param race the player's race
     */
    public Player(final int id, final int difficulty, final String name, final
                  String color, final String race) {
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

    /**
     * @return the player's id
     */
    public final int getId() {
        return id;
    }

    /**
     * @return the player's name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * @return the player's color
     */
    public final String getColor() {
        return this.color;
    }

    /**
     * @return the player's race
     */
    public final String getRace() {
        return this.race;
    }

    /**
     * Gets the amount of a desired resource.
     * @param resource the desired resource
     * @return the amounf of that resource
     */
    public final int get(final String resource) {
        return resources.get(resource);
    }

    /**
     * Sets the desired resource to the amount given.
     * @param resource the desired resource
     * @param amount the amount of that resource
     */
    public final void set(final String resource, final int amount) {
        resources.replace(resource, amount);
    }

    /**
     * Adds the amount give of a certain resource.
     * @param resource the desired resource
     * @param amount the amount given
     */
    public final void add(final String resource, final int amount) {
        resources.replace(resource, resources.get(resource) + amount);
    }

    /**
     * @return the player's mule
     */
    public final int getMule() {
        return mule;
    }

    /**
     * Sets the player's mule.
     * @param mule the mule type
     */
    public final void setMule(final int mule) {
        this.mule = mule;
    }

    /**
     * @return the player's score
     */
    public final int getScore() {
        return this.properties * 500 + resources.get("money")
                + resources.get("energy") * 25 + resources.get("smithore") * 50
                + resources.get("food") * 30;
    }

    /**
     * @return string listing the player's resources and their amounts
     */
    public final String getResourceString() {
        String resourceString = "";
        resourceString += "Money: " + resources.get("money")
                + "\t\tSmithore: " + resources.get("smithore") + "\n";
        resourceString += "Food: " + resources.get("food") + "\t\t\tCrystite: "
                + resources.get("crystite") + "\n";
        resourceString += "Energy: " + resources.get("energy") + "\t\t\tMule: "
                + mule + "\n";

        return resourceString;
    }

    /**
     * Gets the production of this player.
     */
    public final void updateProduction() {
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

    /**
     * Adds a property.
     * @param tile the property
     */
    public final void addProperty(final Tile tile) {
        tiles.add(tile);
        this.properties++;
    }

}
