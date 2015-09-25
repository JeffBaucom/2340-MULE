package game.model;

import java.util.Random;


public class Map {
    private Game game;
    private Tile[][] tiles;
    private Tile selectedTile;
    private String [] rt; // random string tiles


    public int tilesRemaining;

    public Map(boolean isRandom) {
        tiles = new Tile[5][9];
        if (isRandom) {
            rt = randomizeMap();
            int k = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    tiles[i][j] = new Tile(rt[k], i, j);
                    k++;
                }
            }
            tiles[2][4] = new Tile("T", 2, 4);
        } else {
            standardizeMap();
        }

        this.tilesRemaining = 44;
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    public String[] randomizeMap() {
        String[] string = new String[45];
        for (int i = 0; i < 45; i++) {
            Random ran = new Random();
            switch (ran.nextInt(5)) {
                case 0:
                    string[i] = "P";
                    break;
                case 1:
                    string[i] = "R";
                    break;
                case 2:
                    string[i] = "M1";
                    break;
                case 3:
                    string[i] = "M2";
                    break;
                case 4:
                    string[i] = "M3";
                    break;
            }
        }
        return string;
    }

    public void standardizeMap() {
        tiles[0][0] = new Tile("P", 0, 0);
        tiles[0][1] = new Tile("P", 0, 1);
        tiles[0][2] = new Tile("M1", 0, 2);
        tiles[0][3] = new Tile("P", 0, 3);
        tiles[0][4] = new Tile("R", 0, 4);
        tiles[0][5] = new Tile("P", 0, 5);
        tiles[0][6] = new Tile("M3", 0, 6);
        tiles[0][7] = new Tile("P", 0, 7);
        tiles[0][8] = new Tile("P", 0, 8);
        tiles[1][0] = new Tile("P", 1, 0);
        tiles[1][1] = new Tile("M1", 1, 1);
        tiles[1][2] = new Tile("P", 1, 2);
        tiles[1][3] = new Tile("P", 1, 3);
        tiles[1][4] = new Tile("R", 1, 4);
        tiles[1][5] = new Tile("P", 1, 5);
        tiles[1][6] = new Tile("P", 1, 6);
        tiles[1][7] = new Tile("P", 1, 7);
        tiles[1][8] = new Tile("M3", 1, 8);
        tiles[2][0] = new Tile("M3", 2, 0);
        tiles[2][1] = new Tile("P", 2, 1);
        tiles[2][2] = new Tile("P", 2, 2);
        tiles[2][3] = new Tile("P", 2, 3);
        tiles[2][4] = new Tile("T", 2, 4);
        tiles[2][5] = new Tile("P", 2, 5);
        tiles[2][6] = new Tile("P", 2, 6);
        tiles[2][7] = new Tile("P", 2, 7);
        tiles[2][8] = new Tile("M1", 2, 8);
        tiles[3][0] = new Tile("P", 3, 0);
        tiles[3][1] = new Tile("M2", 3, 1);
        tiles[3][2] = new Tile("P", 3, 2);
        tiles[3][3] = new Tile("P", 3, 3);
        tiles[3][4] = new Tile("R", 3, 4);
        tiles[3][5] = new Tile("P", 3, 5);
        tiles[3][6] = new Tile("M2", 3, 6);
        tiles[3][7] = new Tile("P", 3, 7);
        tiles[3][8] = new Tile("P", 3, 8);
        tiles[4][0] = new Tile("P", 4, 0);
        tiles[4][1] = new Tile("P", 4, 1);
        tiles[4][2] = new Tile("M2", 4, 2);
        tiles[4][3] = new Tile("P", 4, 3);
        tiles[4][4] = new Tile("R", 4, 4);
        tiles[4][5] = new Tile("P", 4, 5);
        tiles[4][6] = new Tile("P", 4, 6);
        tiles[4][7] = new Tile("P", 4, 7);
        tiles[4][8] = new Tile("M2", 4, 8);
    }

    public Tile getSelectedTile() { return selectedTile; }

    public void setSelectedTile(int row, int col) {
        selectedTile = tiles[row][col];
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void removeTile() {
        this.tilesRemaining--;
    }

    public int getTilesRemaining() {
        return tilesRemaining;
    }

}
