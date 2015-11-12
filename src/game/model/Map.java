package game.model;

import java.util.Random;

/**
 * class representing the game map.
 */
public final class Map implements java.io.Serializable {
    /**
     * the game object.
     */
    private Game game;

    /**
     * 2D array of tile objects.
     */
    private Tile[][] tiles;

    /**
     * the currently selected tile as a tile object.
     */
    private Tile selectedTile;

    /** The string used to generate a random map. */
    private String[] randTileString;
    /** The number of tiles remaining on the map. */
    private int tilesRemaining;

    /**
     * Construct a new Map.
     * @param game the current instance of game
     * @param mapType the type of map
     */
    public Map(final Game game, final String mapType) {
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

    /**
     * Constructs a new map given a set of tiles.
     * @param game the current instance of game
     * @param tiles the tiles in the new map
     */
    public Map(final Game game, final Tile[][] tiles) {
        this.game = game;
        this.tiles = tiles;
    }

    /**
     * Generates a string used to generate a random map.
     * @return a string to be used to generate a map
     */
    public String[] randomizeMap() {
        String[] string = new String[45];
        for (int i = 0; i < 45; i++) {
            Random rand = new Random();
            switch (rand.nextInt(6)) {
                default:
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

    /**
     * Get a specified tile from the map.
     * @param row the tile's row
     * @param col the tile's column
     * @return the desired tile
     */
    public Tile getTile(final int row, final int col) {
        return tiles[row][col];
    }

    /**
     * @return the selected tile
     */
    public Tile getSelectedTile() {
        return selectedTile;
    }

    /**
     * Sets the selected tile.
     * @param row the row of the desired tile
     * @param col the column of the desired tile
     */
    public void setSelectedTile(final int row, final int col) {
        selectedTile = tiles[row][col];
    }

    /**
     * Sets the current instance of game.
     * @param game the current instance of game
     */
    public void setGame(final Game game) {
        this.game = game;
    }

    /**
     * Decrement the tiles remaining.
     */
    public void removeTile() {
        this.tilesRemaining--;
    }

    /**
     * @return the number of remaining tiles
     */
    public int getTilesRemaining() {
        return tilesRemaining;
    }

    /**
     * @return the array of tiles in the map
     */
    public Tile[][] getTiles() {
        return tiles;
    }

}
