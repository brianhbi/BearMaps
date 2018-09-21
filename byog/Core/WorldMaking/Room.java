package byog.Core.WorldMaking;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class Room implements Serializable {
    private Shape shape;
    private int xBias;
    private int yBias;
    private int width;
    private int height;

    public Room(Shape s, int x, int y) {
        this.shape = s;
        this.xBias = x;
        this.yBias = y;
        this.width = s.getWidth();
        this.height = s.getHeight();
    }

    public boolean inside(int x, int y) {
        return shape.inShape(x - xBias, y - yBias);
    }

    public boolean inRange(int x, int y, int w, int h) {
        for (int i = x; i < x + width; i++) {
            for (int j = y; j < y + height; j++) {
                if (inside(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setTile(TETile t, int x, int y) {
        shape.setTile(t, x - xBias, y - yBias);
    }

    private boolean between(int k, int x, int y) {
        return k >= x && k <= y;
    }

    public Shape getShape() {
        return shape;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int xBias() {
        return xBias;
    }

    public int yBias() {
        return yBias;
    }
}
