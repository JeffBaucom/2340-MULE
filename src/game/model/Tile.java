package game.model;

/**
 * Class that holds information relevant to a tile.
 */
public class Tile implements java.io.Serializable {
    /** Integers that identify the tile. */
    private int ownerID, row, col;
    /** The type of tile. */
    private String type;
    /** The type of mule on the tile. */
    private String mule;

    /**
     * Constructs a tile.
     * @param game the current instance of game
     * @param type the type of tile
     * @param row the tile's row
     * @param col the tile's column
     */
    public Tile(final Game game, final String type, final int row, final int
            col) {
        this.type = type;
        this.ownerID = -1;
        this.row = row;
        this.col = col;
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
        }
    }

    /**
     * @return the type of mule
     */
    public final int getMule() {
        if (mule == null) {
            return 0;
        } else if (mule == "food") {
            return 1;
        } else if (mule == "energy") {
            return 2;
        } else {
            return 3;
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

        if (resource == mule) {
            if (resource == "food") {
                if (type == "R") {
                    production += 4;
                } else if (type == "P") {
                    production += 2;
                } else {
                    production += 1;
                }
            } else if (resource == "energy") {
                if (type == "R") {
                    production += 2;
                } else if (type == "P") {
                    production += 3;
                } else {
                    production += 1;
                }
            } else if (resource == "smithore") {
                if (type == "R") {
                    production += 0;
                } else if (type == "P") {
                    production += 1;
                } else if (type == "M1") {
                    production += 2;
                } else if (type == "M2") {
                    production += 3;
                } else {
                    production += 4;
                }
            }
        }

        return production;
    }
}
