package byog.Core.WorldMaking;

public class HallwayMaker {
    private String lastExtension;
    private Shape hallway;
    private int count = 0;
    private int width;
    private int height;
    private int thickness;
    private int length;

    public HallwayMaker(int width, int height, int thickness) {
        this.hallway = new Hallway(width, height, thickness);
        this.lastExtension = " ";
        this.width = width;
        this.height = height;
        this.length = thickness + 1;
        this.thickness = thickness;
    }

    public int[] getPtr() {
        return hallway.getPtr();
    }

    public int[][] getRecentCoordinates(int xBias, int yBias) {
        int[][] output = new int[thickness][2];
        int[] ptr = hallway.getPtr();
        if (lastExtension.equals("up") || lastExtension.equals("down")) {
            int lowerBound = ptr[0] - thickness / 2;
            for (int i = 0; i < thickness; i++) {
                output[i][0] = i + lowerBound + xBias;
                output[i][1] = ptr[1] + yBias;
            }
        } else {
            int lowerBound = ptr[1] - thickness / 2;
            for (int i = 0; i < thickness; i++) {
                output[i][1] = i + lowerBound + yBias;
                output[i][0] = ptr[0] + xBias;
            }
        }
        return output;
    }

    public void extendUp() {
        if (lastExtension.equals("up")) {
            hallway.extend("up", length + count);
        } else {
            hallway.extend("up", length);
            count = 0;
        }
        count += length;
        lastExtension = "up";
    }

    public void extendDown() {
        if (lastExtension.equals("down")) {
            hallway.extend("down", length + count);
        } else {
            hallway.extend("down", length);
            count = 0;
        }
        count += length;
        lastExtension = "down";
    }

    public void extendLeft() {
        if (lastExtension.equals("left")) {
            hallway.extend("left", length + count);
        } else {
            hallway.extend("left", length);
            count = 0;
        }
        count += length;
        lastExtension = "left";
    }

    public void extendRight() {
        if (lastExtension.equals("right")) {
            hallway.extend("right", length + count);
        } else {
            hallway.extend("right", length);
            count = 0;
        }
        count += length;
        lastExtension = "right";
    }

    public void setPtr(int x, int y) {
        if (x <= thickness / 2) {
            x = thickness / 2 + 1;
        } else if (x >= width - thickness / 2) {
            x = width - thickness / 2 - 1;
        }

        if (y <= thickness / 2) {
            y = thickness / 2 + 1;
        } else if (y >= height - thickness / 2) {
            y = height - thickness / 2 - 1;
        }
        hallway.setPointer(x, y);
    }

    public Shape getHallway() {
        return hallway;
    }
}
