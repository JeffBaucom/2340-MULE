package game.model;

public class Game {
    Turn currentTurn;
    int roundCounter = 0;
    Player[] players;
    int currentPlayerId;
    int passCounter = 0;
    public static Map gameMap = new Map();

    Store store;

    public Game(int playerCount) {
        this.players = new Player[playerCount];
        currentPlayerId = 0;

        store = new Store();
    }

    public void newPlayer(int playerIndex, String name, Color color, Race
            race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(name, color, race);
        }
    }

    public void endTurn() {
        if (currentPlayerId < players.length - 1) {
            currentTurn = new Turn(++currentPlayerId);
        } else {
            currentTurn = new Turn(0);
            roundCounter++;
        }

    }

    public void passTurn() {
        if (passCounter < players.length) {
            passCounter++;
        }
        endTurn();
    }

    public String getPhase() {
        if (roundCounter == 0) {
            return "Unpaid Selection";
        } else if ((roundCounter > 0) && (passCounter < players.length)) {
            return "Paid Selection";
        } else {
            return "Game";
        }
    }

    public int getPlayerCount() {
        return players.length;
    }

    public Store getStore() {
        return store;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerId];
    }
}
