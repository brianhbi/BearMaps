package byog.Core.Characters;

import byog.Core.WorldMaking.CoordinateSystem;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player implements Character {
    private int xPos;
    private int yPos;
    private int monie = 0;
    private TETile tile;
    private int speedBuff = 0;
    private int slow = 0;

    public Player() {
        this.tile = Tileset.BUSINESS_MAN;
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

    public void move(CoordinateSystem world, char input) {
        if (slow % 2 == 0) {
            if (input == 'w') {

                moveUp(world);
                if (speedBuff > 0) {
                    moveUp(world);
                    speedBuff--;
                }

            } else if (input == 's') {
                moveDown(world);
                if (speedBuff > 0) {
                    moveDown(world);
                    speedBuff--;
                }

            } else if (input == 'd') {
                moveRight(world);
                if (speedBuff > 0) {
                    moveRight(world);
                    speedBuff--;
                }

            } else if (input == 'a') {
                moveLeft(world);
                if (speedBuff > 0) {
                    moveLeft(world);
                    speedBuff--;
                }
            }
        }
        if (slow > 0) {
            slow--;
        }
    }

    private void moveUp(CoordinateSystem world) {
        if (!world.isWall(xPos, yPos + 1)) {
            updatePosition(world, xPos, yPos + 1);
        }
    }

    private void moveDown(CoordinateSystem world) {
        if (!world.isWall(xPos, yPos - 1)) {
            updatePosition(world, xPos, yPos - 1);
        }
    }

    private void moveLeft(CoordinateSystem world) {
        if (!world.isWall(xPos - 1, yPos)) {
            updatePosition(world, xPos - 1, yPos);
        }
    }

    private void moveRight(CoordinateSystem world) {
        if (!world.isWall(xPos + 1, yPos)) {
            updatePosition(world, xPos + 1, yPos);
        }
    }

    public int speedBuff() {
        return speedBuff;
    }

    public int monie() {
        return monie;
    }

    public void addMonie(int x) {
        this.monie += x;
    }

}
