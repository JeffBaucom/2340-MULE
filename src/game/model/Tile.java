package game.model;


public class Tile implements java.io.Serializable{

    private int ownerID, row, col;
    private String type;
    private String mule;

    public Tile(Game game, String type, int row, int col) {
        this.type = type;
        this.ownerID = -1;
        this.row = row;
        this.col = col;
    }

    public String getType() {
        return this.type;
    }

    public void setOwner(Player owner) {
        this.ownerID = owner.getId();
    }

    public int getOwner() {
        return this.ownerID;
    }

    public void setMule(int mule) {
        if (mule == 1) {
            this.mule = "food";
        } else if (mule == 2) {
            this.mule = "energy";
        } else if (mule == 3) {
            this.mule = "smithore";
        }
    }

    public int getMule() {
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getProduction(String resource) {
        int production = 0;

        if (resource == mule) {
            if (resource == "food") {
                if (type == "R") production += 4;
                else if (type == "P") production += 2;
                else production += 1;
            } else if (resource == "energy"){
                if (type == "R") production += 2;
                else if (type == "P") production += 3;
                else production += 1;
            } else if (resource == "smithore") {
                if (type == "R") production += 0;
                else if (type == "P") production += 1;
                else if (type == "M1") production += 2;
                else if (type == "M2") production += 3;
                else production += 4;
            }
        }

        return production;
    }
}
