package byog.Core.Characters;

import byog.Core.Utilities.ArrayDeque;
import byog.Core.WorldMaking.CoordinateSystem;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;

public class Snek implements Character, Serializable {
    private int xPos;
    private int yPos;
    private int targetxPos;
    private int targetyPos;
    private TETile tile;
    private ArrayDeque<Integer[]> path = new ArrayDeque<>();
    private int timer = 0;

    public Snek(CoordinateSystem c, Random r) {
        this.tile = Tileset.SNEK;
        this.xPos = 0;
        this.yPos = 0;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setPos(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }

    public TETile getTile() {
        return new TETile(this.tile, this.tile.getTextColor());
    }

    public String getDescription() {
        return tile.description();
    }

}
