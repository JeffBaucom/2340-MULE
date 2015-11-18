package game.model;

import java.util.Random;

/**
 * Class that holds information relevant to a tile.
 */
public class Tile implements java.io.Serializable {
    /** Integers that identify the tile. */
    private int ownerID, row, col, crystite;
    /** The type of tile. */
    private String type;
    /** The type of mule on the tile. */
    private String mule;

    /**
     * Constructs a tile.
     * @param type the type of tile
     * @param row the tile's row
     * @param col the tile's column
     */
    public Tile(final String type, final int row, final int
            col) {
        this.type = type;
        this.ownerID = -1;
        this.row = row;
        this.col = col;

        if (type.equals("R")) {
            crystite = 0;
        } else {
            Random rand = new Random();
            crystite = rand.nextInt(5);
            // crystite = 1;
        }
    }

    /**
     * @return the type of tile
     */
    public final String getType() {
        return this.type;
    }

    /**
     * @param owner the id of the owner, -1 if unowned
     */
    public final void setOwner(final Player owner) {
        this.ownerID = owner.getId();
    }

    /**
     * @return the id of the owner, -1 if unowned
     */
    public final int getOwner() {
        return this.ownerID;
    }

    /**
     * Sets the tile's mule.
     * @param mule sets the mule type
     */
    public final void setMule(final int mule) {
        if (mule == 1) {
            this.mule = "food";
        } else if (mule == 2) {
            this.mule = "energy";
        } else if (mule == 3) {
            this.mule = "smithore";
        } else {
            this.mule = "crystite";
        }
    }

    /**
     * @return the type of mule
     */
    public final int getMule() {
        if (mule == null) {
            return 0;
        } else if (mule.equals("food")) {
            return 1;
        } else if (mule.equals("energy")) {
            return 2;
        } else if (mule.equals("smithore")) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * @return the row of the tile
     */
    public final int getRow() {
        return row;
    }

    /**
     * @return the column of the tile
     */
    public final int getCol() {
        return col;
    }

    /**
     * Gets the desired resource's production values for the tile.
     * @param resource the desired resource
     * @return the amount the tile produces
     */
    public final int getProduction(final String resource) {
        int production = 0;

        if (resource.equals(mule)) {
            if (resource.equals("food")) {
                if (type.equals("R")) {
                    production += 4;
                } else if (type.equals("P")) {
                    production += 2;
                } else {
                    production += 1;
                }
            } else if (resource.equals("energy")) {
                if (type.equals("R")) {
                    production += 2;
                } else if (type.equals("P")) {
                    production += 3;
                } else {
                    production += 1;
                }
            } else if (resource.equals("smithore")) {
                if (type.equals("R")) {
                    production += 0;
                } else if (type.equals("P")) {
                    production += 1;
                } else if (type.equals("M1")) {
                    production += 2;
                } else if (type.equals("M2")) {
                    production += 3;
                } else {
                    production += 4;
                }
            } else if (resource.equals("crystite")) {
                production += crystite;
            }
        }

        return production;
    }

    public int getCrystite() {
        return crystite;
    }
}
