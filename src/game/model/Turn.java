package game.model;


public class Turn {

    private int playerID;
    protected int time;
    Game game;

    public Turn() {

    }

    public Turn(int playerID, Game game) {
        this.playerID = playerID;
        this.game = game;
    }

    public int getPlayer() {
        return this.playerID;
    }

    public void enterTown() {

    }

    public void placeMule(int r, int c) {

    }

    public void buyTile(int r, int c) {
        // TO DO: money handling for regular game phase
        game.getMap().getTile(r, c).setOwner(playerID);
        if (game.getPhase() == 1) {
            game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 300);
        }
        if (game.getPhase() <= 1) {
            game.endTurn();
        }

    }

}
