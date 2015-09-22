package game.model;


public class Map {

    private Tile[][] tiles;

    public Map() {
        tiles = new Tile[5][9];
        tiles[0][0] = new Tile("P");
        tiles[0][1] = new Tile("P");
        tiles[0][2] = new Tile("M1");
        tiles[0][3] = new Tile("P");
        tiles[0][4] = new Tile("R");
        tiles[0][5] = new Tile("P");
        tiles[0][6] = new Tile("M3");
        tiles[0][7] = new Tile("P");
        tiles[0][8] = new Tile("P");
        tiles[1][0] = new Tile("P");
        tiles[1][1] = new Tile("M1");
        tiles[1][2] = new Tile("P");
        tiles[1][3] = new Tile("P");
        tiles[1][4] = new Tile("R");
        tiles[1][5] = new Tile("P");
        tiles[1][6] = new Tile("P");
        tiles[1][7] = new Tile("P");
        tiles[1][8] = new Tile("M3");
        tiles[2][0] = new Tile("M3");
        tiles[2][1] = new Tile("P");
        tiles[2][2] = new Tile("P");
        tiles[2][3] = new Tile("P");
        tiles[2][4] = new Tile("Town");
        tiles[2][5] = new Tile("P");
        tiles[2][6] = new Tile("P");
        tiles[2][7] = new Tile("P");
        tiles[2][8] = new Tile("M1");
        tiles[3][0] = new Tile("P");
        tiles[3][1] = new Tile("M2");
        tiles[3][2] = new Tile("P");
        tiles[3][3] = new Tile("P");
        tiles[3][4] = new Tile("R");
        tiles[3][5] = new Tile("P");
        tiles[3][6] = new Tile("M2");
        tiles[3][7] = new Tile("P");
        tiles[3][8] = new Tile("P");
        tiles[4][0] = new Tile("P");
        tiles[4][1] = new Tile("P");
        tiles[4][2] = new Tile("M2");
        tiles[4][3] = new Tile("P");
        tiles[4][4] = new Tile("R");
        tiles[4][5] = new Tile("P");
        tiles[4][6] = new Tile("P");
        tiles[4][7] = new Tile("P");
        tiles[4][8] = new Tile("M2");
    }

    public Tile getTile(int r, int c) {
        return tiles[r][c];
    }




}
