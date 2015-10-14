package game.model;


public class Tile {

    private int ownerID, row, col;
    private String type;
    private int mule;

    public Tile(String type, int row, int col) {
        this.type = type;
        this.ownerID = -1;
        this.row = row;
        this.col = col;
        this.mule = 0;
    }

    public String getType() {
        return this.type;
    }

    public void setOwner(int playerID) {
        this.ownerID = playerID;
    }

    public int getOwner() {
        return this.ownerID;
    }

    public void setMule(int mule) {
        this.mule = mule;
    }

    public int getMule() { return mule; }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getProduction(String resource) {
        int production = 0;

        // TODO: Figure out production calculations
        return production;
    }
}
