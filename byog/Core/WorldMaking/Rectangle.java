package byog.Core.WorldMaking;

import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Rectangle extends Shape implements Serializable {
    public Rectangle(int width, int height) {
        super(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.FLOOR;
            }
        }
    }
}
