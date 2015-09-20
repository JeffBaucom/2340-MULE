package game.model;

public class Game {
    Player[] players;
    Player currentPlayer;

    public Game(int playerCount) {
        this.players = new Player[playerCount];
        currentPlayer = players[0];
    }

    public void newPlayer(int playerIndex, String name, Color color, Race
            race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(name, color, race);
        }
    }

    public int getPlayerCount() {
        return players.length;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
