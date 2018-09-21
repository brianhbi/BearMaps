package byog.Core.Characters;

import byog.Core.WorldMaking.CoordinateSystem;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public interface Character extends Serializable {
    default void updatePosition(CoordinateSystem c, int x, int y) {
        if (getXPos() == 0 && getYPos() == 0) {
            c.setTile(Tileset.NOTHING, getXPos(), getYPos());
        } else {
            c.setTile(Tileset.FLOOR, getXPos(), getYPos());
        }
        c.setTile(getTile(), x, y);
        setPos(x, y);
    }

    default void playSound() {
    }

    default void stopSound() {
    }

    int getXPos();

    int getYPos();

    void setPos(int x, int y);

    String getDescription();

    TETile getTile();
}
