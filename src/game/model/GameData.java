package game.model;

/**
 * Class that aggregates game information for saving.
 */
public class GameData {
    /**
     * difficulty of the game as an int.
     */
    private int difficulty;
    /**
     * String of mapType i.e. "standard".
     */
    private String mapType;

    /**
     * integer representation of game data variables.
     */
    private int playerCounter, roundCounter, passCounter, phase, timeLeft;
    /**
     * boolean referring to the current turn status.
     */
    private boolean turnover;

    /**
     * player object array.
     */
    private  Player[] players;
    /**
     * tile object array.
     */
    private Tile[][] tiles;

    /**
     * string of the game console log.
     */
    private String gameLog;

    /**
     *
     */
    public GameData() {
    }

    /**
     *
     * @param game the game object
     */
    public GameData(final Game game) {
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

    /**
     *
     * @return integer representation of the game difficulty
     */
    public final int getDifficulty() {
        return difficulty;
    }

    /**
     *
     * @return string representation of the game map type
     */
    public final String getMapType() {
        return mapType;
    }

    /**
     *
     * @return integer representing the current player
     */
    public final int getPlayerCounter() {
        return playerCounter;
    }

    /**
     *
     * @return integer representation of the current round
     */
    public final int getRoundCounter() {
        return roundCounter;
    }

    /**
     *
     * @return integer representation of the number of current number of passes
     */
    public final int getPassCounter() {
        return passCounter;
    }

    /**
     *
     * @return integer representing the current game phase
     */
    public final int getPhase() {
        return phase;
    }

    /**
     *
     * @return boolean representing if the current turn is already over
     */
    public final boolean isTurnover() {
        return turnover;
    }

    /**
     *
     * @return int representing the time left in the turn timer
     */
    public final int getTimeLeft() {
        return timeLeft;
    }

    /**
     *
     * @return array of player objects
     */
    public final Player[] getPlayers() {
        return players;
    }

    /**
     *
     * @return 2D array of the tile objects in map
     */
    public final Tile[][] getTiles() {
        return tiles;
    }

    /**
     *
     * @return string of current game log info
     */
    public final String getGameLog() {
        return gameLog;
    }
}
