package game.model;


public class TurnManager {

    int numPlayers;
    Turn currentTurn;

    public TurnManager() {
        this.numPlayers = 4;
        currentTurn = new Turn(0); // start with player 1
    }

    public TurnManager(int numPlayers) {
        this.numPlayers = numPlayers;
        currentTurn = new Turn(0); // start with player 1
    }

    public void changeTurn(int playerID) {
        currentTurn = new Turn(playerID);
    }


}
