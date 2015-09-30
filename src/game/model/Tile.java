package game.model;


public class Tile {

    private int ownerID, row, col;
    private String type;
    private int mule;

    public Tile() {
        this.ownerID = -1;
    }

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

    public void setMule(int mule) {
        this.mule = mule;
    }

    public int getOwner() {
        return this.ownerID;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getMule() { return mule; }
}
