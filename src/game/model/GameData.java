package game.model;

public class GameData {
    Player[] players;
    Tile[][] tiles;

    public GameData(Game game) {
        players = game.getPlayers();
        tiles = game.getMap().getTiles();
    }
}
