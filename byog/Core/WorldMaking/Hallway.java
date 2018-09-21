
package byog.Core.WorldMaking;

import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Hallway extends Shape implements Serializable {
    private int thickness;
    private int[] ptr = new int[2];
    private int margin;

    public Hallway(int width, int height, int thickness) {
        super(width, height);
        this.thickness = thickness;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
        this.ptr[0] = width / 2;
        this.ptr[1] = height / 2;
        this.margin = thickness / 2 + thickness % 2 + 1;
    }

    public void setPointer(int x, int y) {
        if (x < margin) {
            this.ptr[0] = margin;
        } else if (x > width - margin) {
            this.ptr[0] = width - margin;
        } else {
            this.ptr[0] = x;
        }

        if (y < margin) {
            this.ptr[1] = margin;
        } else if (y > height - margin) {
            this.ptr[1] = height - margin;
        } else {
            this.ptr[1] = y;
        }
    }

    public int[] getPtr() {
        int[] output = new int[2];
        System.arraycopy(ptr, 0, output, 0, 2);
        return output;
    }

    public void extend(String direction, int length) {
        if (direction.equals("up")) {
            extendUp(length);
        } else if (direction.equals("down")) {
            extendDown(length);
        } else if (direction.equals("left")) {
            extendLeft(length);
        } else if (direction.equals("right")) {
            extendRight(length);
        }
    }

    private void extendUp(int length) {
        int startX = ptr[0] - thickness / 2;
        int endX = ptr[0] + thickness / 2 + thickness % 2;
        while (tiles[ptr[0]][ptr[1] - 1].character() != ' ' && ptr[1] > 0) {
            ptr[1] -= 1;
        }
        while (length > 0 && ptr[1] < height - margin) {
            for (int x = startX; x < endX; x++) {
                tiles[x][ptr[1]] = Tileset.FLOOR;
            }
            ptr[1]++;
            length--;
        }
        ptr[1]--;
    }

    private void extendDown(int length) {
        int startX = ptr[0] - thickness / 2;
        int endX = ptr[0] + thickness / 2 + thickness % 2;
        while (tiles[ptr[0]][ptr[1] + 1].character() != ' ' && ptr[1] < tiles[0].length - 1) {
            ptr[1] += 1;
        }
        while (length > 0 && ptr[1] > margin) {
            for (int x = startX; x < endX; x++) {
                tiles[x][ptr[1]] = Tileset.FLOOR;
            }
            ptr[1]--;
            length--;
        }
        ptr[1]++;
    }

    private void extendLeft(int length) {
        int startY = ptr[1] - thickness / 2;
        int endY = ptr[1] + thickness / 2 + thickness % 2;
        while (tiles[ptr[0] + 1][ptr[1]].character() != ' ' && ptr[0] < tiles.length - 1) {
            ptr[0] += 1;
        }
        while (length > 0 && ptr[0] > margin) {
            for (int y = startY; y < endY; y++) {
                tiles[ptr[0]][y] = Tileset.FLOOR;
            }
            ptr[0]--;
            length--;
        }
        ptr[0]++;
    }

    private void extendRight(int length) {
        int startY = ptr[1] - thickness / 2;
        int endY = ptr[1] + thickness / 2 + thickness % 2;
        while (tiles[ptr[0] - 1][ptr[1]].character() != ' ' && ptr[0] > 0) {
            ptr[0] -= 1;
        }
        while (length > 0 && ptr[0] < width - margin) {
            for (int y = startY; y < endY; y++) {
                tiles[ptr[0]][y] = Tileset.FLOOR;
            }
            ptr[0]++;
            length--;
        }
        ptr[0]--;
    }
}
