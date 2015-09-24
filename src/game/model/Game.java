package game.model;

public class Game {
    Turn currentTurn;
    int roundCounter, passCounter = 0;

    Player[] players;
    int currentId;

    Map map;

    Store store;

    public Game(int playerCount) {
        this.players = new Player[playerCount];

        store = new Store();
        this.map = new Map();
        map.setGame(this);
    }

    public void startGame() {
        roundCounter = 0;
        passCounter = 0;

        currentId = 0;
        currentTurn = new Turn(players[currentId], this);
    }

    public void newPlayer(int playerIndex, String name, String color, Race
            race) {
        if (playerIndex < getPlayerCount()) {
            players[playerIndex] = new Player(playerIndex, name, color, race);
        }
    }

    public void endTurn() {
        System.out.println("Turn End.");
        if (currentId < players.length - 1) {
            currentId++;
            currentTurn = new Turn(players[currentId], this);
        } else {
            currentId = 0;
            currentTurn = new Turn(players[currentId], this);
            roundCounter++;
            passCounter = 0;
        }

        map.updateMap();
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

    public Player getCurrentPlayer() {
        return players[currentId];
    }

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


}
