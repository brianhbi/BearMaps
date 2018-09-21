package byog.Core.Characters;

import byog.Core.Utilities.ArrayDeque;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class FinanceGuy implements Character, Serializable {
    private int xPos;
    private int yPos;
    private TETile tile;
    private ArrayDeque<Integer[]> path = new ArrayDeque<>();

    public FinanceGuy() {
        this.tile = Tileset.FINANCE_GUY;
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
