package game.model;


public class Tile {

    private int ownerID;
    private String type;
    
    public Tile() {
        this.ownerID = -1;
    }

    public Tile(String type) {
        this.type = type;
        this.ownerID = -1;
    }

    public void setOwner(int playerID) {
        this.ownerID = playerID;
    }

    public int getOwner() {
        return this.ownerID;
    }

    public String getType() {
        return this.type;
    }

}
