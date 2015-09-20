package game.model;


public class Turn {

    private int playerID;
    protected int time;
    private int[] currentLocation; // where the player is right now

    public Turn() {

    }

    public Turn(int playerID) {
        this.playerID = playerID;
    }

    public int getPlayer() {
        return this.playerID;
    }

    public void enterTown() {

    }

    public void placeMule(int r, int c) {

    }

    public void buyTile(int r, int c) {
        Game.gameMap.getTile(r, c).setOwner(playerID);
    }

}
