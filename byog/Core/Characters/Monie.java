package byog.Core.Characters;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Monie implements Character {
    private int xPos;
    private int yPos;
    private int value;
    private TETile tile;

    public Monie(int value) {
        this.value = value;
        this.tile = Tileset.MONIE;
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

    public int value() {
        return value;
    }
}
