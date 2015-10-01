package game.model;

public class Game {
    Turn currentTurn;
    int roundCounter, passCounter, phase, timeLeft;
    String gameLog;

    Player[] players;
    int currentId;

    Map map;
    Store store;

    public Game(int playerCount, String mapType) {
        this.players = new Player[playerCount];
        store = new Store();
        this.map = new Map(mapType);
        map.setGame(this);
    }

    public void startGame() {
        roundCounter = 0;
        passCounter = 0;
        phase = 0;
        gameLog = "Welcome to MULE Game.\n";

        currentId = 0;
        currentTurn = new Turn(players[currentId], this);
    }

    public void newPlayer(int playerIndex, String name, String color,
                          Race race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(playerIndex, name, color, race);
        }
    }

    public void endTurn() {
        if (currentId < players.length - 1) {
            currentId++;
        } else {
            currentId = 0;
            roundCounter++;
        }

        if (roundCounter <= 1) {
            phase = 0;
        } else if ((roundCounter > 1) && (passCounter < players.length &&
                phase <= 1)) {
            phase = 1;
        } else {
            phase = 2;
        }

        if (currentId == 0) {
            passCounter = 0;
        }

        currentTurn = new Turn(players[currentId], this);
    }

    public void passTurn() {
        passCounter++;
        endTurn();
    }

    public int getPhase() {
        return phase;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Player getCurrentPlayer() {
        return players[currentId];
    }

    public Player getPlayer(int id) { return players[id]; }

    public Turn getTurn() {
        return currentTurn;
    }

    public Map getMap() {
        return map;
    }

    public int getPlayerCount() {
        return players.length;
    }

    public Store getStore() {
        return store;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getGameLog() {
        return gameLog;
    }

    public void logEvent(String event) {
        gameLog += event + "\n";
    }

    public String getLeaderBoard() {
        String leaderBoard = "";
        int index = 0;

        for (Player p : players) {
            leaderBoard += "Player " + (index + 1) + ": " + p.getScore() + "\n";
            index++;
        }

        return leaderBoard;
    }
}
