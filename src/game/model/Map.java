package game.model;

import java.util.Random;

/**
 * class representing the game map.
 */
public final class Map implements java.io.Serializable {
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

    private static final int WIDTH = 9;
    private static final int HEIGHT = 5;

    /**
     * Construct a new Map.
     * @param game the current instance of game
     * @param mapType the type of map
     */
    public Map(final String mapType) {
        tiles = new Tile[HEIGHT][WIDTH];
        if (mapType.equals("standard")) {
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    if (Math.random() < 0.5) {
                        tiles[i][j] = new Tile("P", i, j);
                    } else {
                        tiles[i][j] = new Tile("F", i, j);
                    }
                }
            }

            for (int i = 0; i < HEIGHT; i++) {
                tiles[i][4] = new Tile("R", i, 4);
            }

            tiles[2][4] = new Tile("T", 2, 4);
            tiles[0][2] = new Tile("M1", 0, 2);
            tiles[1][1] = new Tile("M1", 1, 1);
            tiles[2][8] = new Tile("M1", 2, 8);
            tiles[3][1] = new Tile("M2", 3, 1);
            tiles[3][6] = new Tile("M2", 3, 6);
            tiles[4][2] = new Tile("M2", 4, 2);
            tiles[4][8] = new Tile("M2", 4, 8);
            tiles[0][6] = new Tile("M3", 0, 6);
            tiles[1][8] = new Tile("M3", 1, 8);
            tiles[2][0] = new Tile("M3", 2, 0);
        } else {
            randTileString = randomizeMap();
            int k = 0;
            for (int i = 0; i < HEIGHT; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    tiles[i][j] = new Tile(randTileString[k], i, j);
                    k++;
                }
            }

            tiles[2][4] = new Tile("T", 2, 4);
        }

        this.tilesRemaining = 44;
    }

    /**
     * Constructs a new map given a set of tiles.
     * @param game the current instance of game
     * @param tiles the tiles in the new map
     */
    public Map(final Tile[][] tiles) {
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
                default:
                    string[i] = "P";
                    break;
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
