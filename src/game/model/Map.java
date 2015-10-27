package game.model;

import java.util.Random;


public class Map implements java.io.Serializable{
    private Game game;
    private Tile[][] tiles;
    private Tile selectedTile;

    private String[] randTileString;
    public int tilesRemaining;

    public Map(Game game, String mapType) {
        this.game = game;
        tiles = new Tile[5][9];
        if (mapType.equals("standard")) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    tiles[i][j] = new Tile(game, "P", i, j);
                }
            }

            for (int i = 0; i < 5; i++) {
                tiles[i][4] = new Tile(game, "R", i, 4);
            }

            tiles[2][4] = new Tile(game, "T", 2, 4);
            tiles[0][2] = new Tile(game, "M1", 0, 2);
            tiles[1][1] = new Tile(game, "M1", 1, 1);
            tiles[2][8] = new Tile(game, "M1", 2, 8);
            tiles[3][1] = new Tile(game, "M2", 3, 1);
            tiles[3][6] = new Tile(game, "M2", 3, 6);
            tiles[4][2] = new Tile(game, "M2", 4, 2);
            tiles[4][8] = new Tile(game, "M2", 4, 8);
            tiles[0][6] = new Tile(game, "M3", 0, 6);
            tiles[1][8] = new Tile(game, "M3", 1, 8);
            tiles[2][0] = new Tile(game, "M3", 2, 0);
        } else {
            randTileString = randomizeMap();
            int k = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    tiles[i][j] = new Tile(game, randTileString[k], i, j);
                    k++;
                }
            }

            tiles[2][4] = new Tile(game, "T", 2, 4);
        }

        this.tilesRemaining = 44;
    }

    public String[] randomizeMap() {
        String[] string = new String[45];
        for (int i = 0; i < 45; i++) {
            Random rand = new Random();
            switch (rand.nextInt(6)) {
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
                case 5:
                    string[i] = "F";
            }
        }

        return string;
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
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
