package byog.Core.WorldMaking;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;


public class Shape implements Serializable {
    protected TETile[][] tiles;
    protected int width;
    protected int height;

    public Shape(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new TETile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TETile[][] getTileArray() {
        return TETile.copyOf(tiles);
    }

    public TETile getTile(int x, int y) {
        return new TETile(tiles[x][y], tiles[x][y].getTextColor());
    }

    public void setTile(TETile t, int x, int y) {
        tiles[x][y] = t;
    }

    public void extend(String direction, int length) {
        System.out.println("can only extend up on Hallway");
    }

    public void setPointer(int x, int y) {
        System.out.println("can only set pointer on Hallway");
    }

    public int[] getPtr() {
        System.out.println("only hallway has ptr");
        return new int[2];
    }

    public boolean inShape(int x, int y) {
        if (x < 0 || x >= tiles.length) {
            return false;
        }

        if (y < 0 || y >= tiles[0].length) {
            return false;
        }

        if (tiles[x][y].character() == ' ') {
            return false;
        }
        return true;
    }

    public int area() {
        return width * height;
    }

}
