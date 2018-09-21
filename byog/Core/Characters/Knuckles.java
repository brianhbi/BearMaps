package byog.Core.Characters;

import byog.Core.Utilities.ArrayDeque;

import byog.Core.WorldMaking.CoordinateSystem;
import byog.Core.WorldMaking.WorldMaker;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;

public class Knuckles implements Character, Serializable {
    private int xPos;
    private int yPos;
    private int targetxPos;
    private int targetyPos;
    private TETile tile;
    private ArrayDeque<Integer[]> path = new ArrayDeque<>();
    private int timer = 0;
    private boolean foundDaWei = false;

    public Knuckles(CoordinateSystem c, Random r) {
        this.tile = Tileset.KNUCKLES;
        this.xPos = 0;
        this.yPos = 0;
        generateTargetPos(c, r);
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

    public void generateTargetPos(CoordinateSystem c, Random r) {
        int x, y;
        for (int i = 0; i < 100; i++) {
            x = WorldMaker.rng(r, 0, Tileset.WIDTH - 1);
            y = WorldMaker.rng(r, 0, Tileset.HEIGHT - 1);
            if (c.isEmpty(x, y) && WorldMaker.getDistance(xPos, yPos, x, y) >= 45) {
                targetxPos = x;
                targetyPos = y;
                return;
            }
        }
    }
}
