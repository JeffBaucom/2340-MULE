package game.model;

/**
 * Class representing the current status of the Game.
 */
public class Game implements java.io.Serializable {
    /** The current turn. */
    private Turn currentTurn;

    /** Ints representing the current player, the current round, the number
     * of passes, the current phase, and the time left, respectively. */
    private int playerCounter, roundCounter, passCounter, phase, timeLeft;

    /** Whether the turn is over. */
    private boolean turnover;

    /** The log of events in the game. */
    private String gameLog;

    /** Arrays representing the players and the order of players. */
    private Player[] players, playerOrder;

    /** The id of the current player. */
    private int currentId;

    /** The difficulty of the game. */
    private int difficulty;

    /** The type of map. */
    private String mapType;

    /** This game's instance of Map. */
    private Map map;

    /** This game's isntance of Store. */
    private Store store;

    /**
     * Constructs a new instance of Game.
     *
     * @param playerCount the number of players
     * @param difficulty the difficulty of the game
     * @param mapType the type of map
     */
    public Game(final int playerCount, final int difficulty, final String
            mapType) {
        this.players = new Player[playerCount];
        this.playerOrder = new Player[playerCount];
        this.difficulty = difficulty;
        this.store = new Store(this);

        this.mapType = mapType;
        this.map = new Map(this, mapType);
        map.setGame(this);
        gameLog = "";
    }

    /**
     * Puts this instance of Game into its starting state.
     */
    public final void startGame() {
        playerCounter = 0;
        roundCounter = 0;
        passCounter = 0;
        phase = 0;
        logEvent("Welcome to MULE Game.");

        reorderPlayers();
        currentId = playerOrder[0].getId();
        currentTurn = new Turn(players[currentId], this);
        turnover = false;
    }

    /**
     * Adds a new player to the game.
     * @param playerIndex the player's index in the player array
     * @param difficulty the difficulty of the game
     * @param name the name of the player
     * @param color the color of the player
     * @param race the race of the player
     */
    public final void newPlayer(final int playerIndex, final int difficulty,
                                final String name, final String color,
                                final String race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(playerIndex, difficulty, name,
                    color, race);
            playerOrder[playerIndex] = players[playerIndex];
        }
    }

    /**
     * Puts the game in the specified state.
     *
     * @param playerCounter the index of current player
     * @param roundCounter the current round
     * @param passCounter the cnumber of passes
     * @param phase the current phase
     * @param timeLeft the time left
     * @param turnover whether the turn is over
     */
    public final void goToTurn(final int playerCounter, final int roundCounter,
                               final int passCounter, final int phase, final
                               int timeLeft, final boolean turnover) {
        this.playerCounter = playerCounter;
        this.roundCounter = roundCounter;
        this.passCounter = passCounter;
        this.phase = phase;
        this.timeLeft = timeLeft;

        currentId = playerOrder[playerCounter].getId();
        currentTurn = new Turn(playerOrder[playerCounter], this);
    }

    /**
     * Ends the current turn and generates a new one.
     */
    public final void endTurn() {
        if (playerCounter < players.length - 1) {
            playerCounter++;
        } else {
            reorderPlayers();
            playerCounter = 0;
            roundCounter++;
        }

        if (roundCounter <= 1) {
            phase = 0;
        } else if ((roundCounter > 1) && (passCounter < players.length
                && phase <= 1)) {
            phase = 1;
        } else {
            phase = 2;
        }

        if (playerCounter == 0) {
            passCounter = 0;
        }

        currentId = playerOrder[playerCounter].getId();
        currentTurn = new Turn(playerOrder[playerCounter], this);
    }

    /**
     * Passes the current turn.
     */
    public final void passTurn() {
        passCounter++;
        endTurn();
    }

    /**
     * @return the current phase of the game
     */
    public final int getPhase() {
        return phase;
    }

    /**
     * @return the time left in this turn
     */
    public final int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Sets whether the turn is over or not.
     * @param turnover whether the turn is over
     */
    public final void setTurnover(final boolean turnover) {
        this.turnover = turnover;
    }

    /**
     * @return whether the turn is over
     */
    public final boolean getTurnOver() {
        return turnover;
    }

    /**
     * Sets the time left in the turn.
     * @param timeLeft the amount of time left in the game
     */
    public final void setTimeLeft(final int timeLeft) {
        this.timeLeft = timeLeft;
    }

    /**
     * @return the current player
     */
    public final Player getCurrentPlayer() {
        return playerOrder[playerCounter];
    }

    /**
     * Gets a player based on their id.
     * @param id the player's id
     * @return the player whose id was passed
     */
    public final Player getPlayer(final int id) {
        return players[id];
    }

    /**
     * @return the current turn
     */
    public final Turn getTurn() {
        return currentTurn;
    }

    /**
     * @return the map
     */
    public final Map getMap() {
        return map;
    }

    /**
     * @return the amount of players in the game
     */
    public final int getPlayerCount() {
        return players.length;
    }

    /**
     * @return the amount of passes
     */
    public final int getPassCounter() {
        return passCounter;
    }

    /**
     * @return the current Player this turn
     */
    public final int getPlayerCounter() {
        return playerCounter;
    }

    /**
     * @return the store
     */
    public final Store getStore() {
        return store;
    }

    /**
     * @return the current round
     */
    public final int getRoundCounter() {
        return roundCounter;
    }

    /**
     * @return the array of Players
     */
    public final Player[] getPlayers() {
        return players;
    }

    /**
     * @return a string representing the events of the game
     */
    public final String getGameLog() {
        return gameLog;
    }

    /**
     * @return the difficulty of the game
     */
    public final int getDifficulty() {
        return difficulty;
    }

    /**
     * @return the map type
     */
    public final String getMapType() {
        return mapType;
    }

    /**
     * Adds an event to the game log.
     * @param event the event to be added to teh game log
     */
    public final void logEvent(final String event) {
        gameLog += event + "\n";
    }

    /**
     * Sets the game log to a given string.
     * @param gameLog the string for the game log to be set to
     */
    public final void setGameLog(final String gameLog) {
        this.gameLog = gameLog;
    }

    /**
     * Generates the leaderboard for the game.
     * @return a leaderboard of Players and their scores
     */
    public final String getLeaderBoard() {
        String leaderBoard = "";

        for (Player p : playerOrder) {
            leaderBoard += p.getName() + ": " + p.getScore() + "\n";
        }

        return leaderBoard;
    }

    /**
     * Re-Orders Players based on their score.
     */
    public final void reorderPlayers() {
        for (int i = 0; i < playerOrder.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < playerOrder.length; j++) {

                if (playerOrder[i].getScore() < playerOrder[j].getScore()) {
                    maxIndex = j;
                }
            }
            Player temp = playerOrder[i];
            playerOrder[i] = playerOrder[maxIndex];
            playerOrder[maxIndex] = temp;

        }
    }

    /**
     * @return the Player in last place
     */
    public final Player getLosingPlayer() {
        Player lastPlace = playerOrder[0];
        for (Player p : playerOrder) {
            if (p.getScore() < lastPlace.getScore()) {
                lastPlace = p;
            }
        }
        return lastPlace;
    }

    /**
     * Sets the map to a new map with the given tiles.
     * @param tiles the tiles for the new map
     */
    public final void setMap(final Tile[][] tiles) {
        map = new Map(this, tiles);
    }

    /**
     * Sets the players to the given array of Players.
     * @param players the array of Players for the Players to be set as
     */
    public final void setPlayers(final Player[] players) {
        this.players = players;
        this.playerOrder = players;
    }
}
