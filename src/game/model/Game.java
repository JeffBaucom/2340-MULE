package game.model;

public class Game {
    TurnManager turnManager;
    Player[] players;
    int currentPlayerId;

    Store store;

    public Game(int playerCount) {
        this.players = new Player[playerCount];

        turnManager = new TurnManager();
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
            turnManager.changeTurn(++currentPlayerId);
        } else {
            turnManager.changeTurn(0);
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
