package game.model;


public class TurnManager {

    int numPlayers;

    public TurnManager() {
        this.numPlayers = 4;
    }

    public TurnManager(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void startTurn(int playerID) {
        Turn newTurn = new Turn(playerID);
        while (newTurn.time != 0) {

        }
    }


}
