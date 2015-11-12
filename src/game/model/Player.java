package game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, Integer> resources;

    /** The player's owned tiles. */
    private List<Tile> tiles;

    private static final String MONEY = "money";
    private static final String FOOD = "food";
    private static final String ENERGY = "energy";
    private static final String SMITHORE = "smithore";
    private static final String CRYSTITE = "crystite";

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

        if (race.equals("flapper")) {
            resources.put(MONEY, 1600);
        } else if (race.equals("human")) {
            resources.put(MONEY, 600);
        } else {
            resources.put(MONEY, 1000);
        }
        if (difficulty == 0) {
            resources.put(FOOD, 8);
            resources.put(ENERGY, 4);
            resources.put(SMITHORE, 0);
            resources.put(CRYSTITE, 0);
        } else {
            resources.put(FOOD, 4);
            resources.put(ENERGY, 2);
            resources.put(SMITHORE, 0);
            resources.put(CRYSTITE, 0);
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
        return this.properties * 500 + resources.get(MONEY)
                + resources.get(ENERGY) * 25 + resources.get("smithore") * 50
                + resources.get(FOOD) * 30;
    }

    /**
     * @return string listing the player's resources and their amounts
     */
    public final String getResourceString() {
        String resourceString = "";
        resourceString += "Money: " + resources.get(MONEY)
                + "\t\tSmithore: " + resources.get(SMITHORE) + "\n";
        resourceString += "Food: " + resources.get(FOOD) + "\t\t\tCrystite: "
                + resources.get(CRYSTITE) + "\n";
        resourceString += "Energy: " + resources.get(ENERGY) + "\t\t\tMule: "
                + mule + "\n";

        return resourceString;
    }

    /**
     * Gets the production of this player.
     */
    public final void updateProduction() {
        for (Tile t : tiles) {
            if (get(ENERGY) > 0) {
                add(FOOD, t.getProduction(FOOD));
                add(ENERGY, t.getProduction(ENERGY));
                add(CRYSTITE, t.getProduction(CRYSTITE));
                add(SMITHORE, t.getProduction(SMITHORE));

                if (!(t.getMule() == 0)) {
                    add(ENERGY, -1);
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
