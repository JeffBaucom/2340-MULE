package game.model;

public class Game {
    Turn currentTurn;

    Player[] players;
    int currentPlayerId;
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
