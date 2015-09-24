package game.model;

public class Game {
    Turn currentTurn;
    int roundCounter = 0;
    Player[] players;
    int currentPlayerId;
    int passCounter = 0;
    Map gameMap;

    Store store;

    public Game(int playerCount) {
        this.players = new Player[playerCount];
        currentPlayerId = 0;

        store = new Store();
        this.gameMap = new Map();
    }

    public void newPlayer(int playerIndex, String name, String color, Race
            race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(name, color, race);
        }
    }

    public void endTurn() {
        if (currentPlayerId < players.length - 1) {
            currentPlayerId++;
            currentTurn = new Turn(currentPlayerId, this);
        } else {
            currentPlayerId = 0;
            currentTurn = new Turn(currentPlayerId, this);
            roundCounter++;
            passCounter = 0;
        }
    }

    public void passTurn() {
        if ((roundCounter > 1) && (passCounter < players.length)) {
            passCounter++;
        }
        endTurn();
    }

    public int getPhase() {
        if (roundCounter <= 1) {
            return 0;
        } else if ((roundCounter > 1) && (passCounter < players.length)) {
            return 1;
        } else {
            return 2;
        }
    }

    public Map getMap() {
        return gameMap;
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
