package byog.TileEngine;

import java.awt.Color;
import java.io.Serializable;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 * <p>
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 * <p>
 * Ex:
 * world[x][y] = Tileset.FLOOR;
 * <p>
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset implements Serializable {
    public static final int WIDTH = 75;
    public static final int HEIGHT = 35;
    public static final int TEXT_SPACE = 8;
    public static final int ACTUAL_HEIGHT = HEIGHT + TEXT_SPACE;

    public static final TETile WALL = new TETile('#', new Color(34, 139, 34),
            new Color(34, 139, 34), "wall");
    public static final TETile FLOOR = new TETile('·', new Color(255, 255, 0),
            new Color(128, 192, 128), "floor");
    public static final TETile RED_FLOOR = new TETile('·', new Color(255, 0, 0),
            new Color(255, 0, 0), "floor");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile DOOR = new TETile('█', Color.orange, Color.black,
            "door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");
    public static final TETile SNEK = new TETile('s', new Color(34, 139, 100),
            new Color(128, 192, 128), "snek", "byog//Core//Images//snek.jpg");
    public static final TETile BUSINESS_MAN = new TETile('b', new Color(128, 192, 128),
            new Color(128, 192, 128), "ellen", "byog//Core//Images//haas_student.png");
    public static final TETile MONIE = new TETile("\uD83D\uDCB0", Color.green, Color.black,
            "monie", "byog//Core//Images//cash.jpg");
    public static final TETile FLYER = new TETile('\u1F4F', Color.white, Color.black, "flyer");
    public static final TETile CAROL = new TETile("\uD83D\uDC75", Color.white,
            new Color(128, 192, 128), "Carol");
    public static final TETile KNUCKLES = new TETile('k', Color.white,
            new Color(128, 192, 128), "knuckles", "byog//Core//Images//ugandan_knuckles2.jpg");
    public static final TETile HELL_YEAH = new TETile('h', Color.white,
            new Color(128, 192, 128), "hell yeah", "byog//Core//Images//hell_yeah.jpg");
    public static final TETile FINANCE_GUY = new TETile('F', Color.white,
            new Color(128, 192, 128), "finance guy", "byog//Core//Images//finance.jpg");
}



