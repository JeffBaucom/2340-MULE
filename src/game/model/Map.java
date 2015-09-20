package game.model;


public class Map {

    private Tile[][] tiles;

    public Map() {

    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }




}
