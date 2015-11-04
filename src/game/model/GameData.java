package game.model;

public class GameData {
    int difficulty;
    String mapType;

    int playerCounter, roundCounter, passCounter, phase, timeLeft;
    boolean turnover;

    Player[] players;
    Tile[][] tiles;

    String gameLog;

    public GameData() {
    }

    public GameData(Game game) {
        this.difficulty = game.getDifficulty();
        this.mapType = game.getMapType();

        this.playerCounter = game.getPlayerCounter();
        this.roundCounter = game.getRoundCounter();
        this.passCounter = game.getPassCounter();
        this.phase = game.getPhase();
        this.timeLeft = game.getTimeLeft();
        this.turnover = game.getTurnOver();

        players = game.getPlayers();
        tiles = game.getMap().getTiles();

        this.gameLog = game.getGameLog();
    }

    public String toString() {
        return players.toString();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getMapType() {
        return mapType;
    }

    public int getPlayerCounter() {
        return playerCounter;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public int getPassCounter() {
        return passCounter;
    }

    public int getPhase() {
        return phase;
    }

    public boolean isTurnover() {
        return turnover;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public String getGameLog() {
        return gameLog;
    }
}
