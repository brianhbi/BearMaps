package byog.Core.Characters;

import byog.Core.WorldMaking.CoordinateSystem;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Door implements Character, Serializable {
    private int xPos;
    private int yPos;
    private TETile tile;

    public Door(int x, int y) {
        this.tile = Tileset.DOOR;
        this.xPos = x;
        this.yPos = y;
    }

    public void updatePosition(CoordinateSystem c, int x, int y) {
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
