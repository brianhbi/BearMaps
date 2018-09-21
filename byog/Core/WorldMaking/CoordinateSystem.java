package byog.Core.WorldMaking;

import byog.Core.Utilities.ArrayDeque;
import byog.Core.Characters.Player;
import byog.Core.Characters.Snek;
import byog.Core.Characters.Monie;
import byog.Core.Characters.Knuckles;
import byog.Core.Characters.HellYeah;
import byog.Core.Characters.FinanceGuy;
import byog.Core.Characters.Character;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.GamePlaying.Level;

import java.io.Serializable;

public class CoordinateSystem implements Serializable {
    private TETile[][] graph;
    private ArrayDeque<Room> rooms = new ArrayDeque<>();
    private ArrayDeque<Character> characters = new ArrayDeque<>();
    private Level level;
    private int width;
    private int height;

    public CoordinateSystem(int width, int height) {
        graph = new TETile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                graph[x][y] = Tileset.NOTHING;
            }
        }
        this.width = width;
        this.height = height;
    }

    public void addToCharacters(Character c) {
        characters.addLast(c);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public ArrayDeque<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room r) {
        rooms.addLast(r);

    }

    public boolean isAvailable(int x, int y, int w, int h) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).inRange(x, y, width, height)) {
                return false;
            }
        }
        return true;
    }

    public ArrayDeque<Character> getCharacters() {
        return characters;
    }

    public void renderGraph() {
        TETile[][] tiles;
        Room room;
        for (int r = 0; r < rooms.size(); r++) {
            room = rooms.get(r);
            tiles = room.getShape().getTileArray();
            for (int x = 0; x < room.width(); x++) {
                for (int y = 0; y < room.height(); y++) {
                    if (tiles[x][y].character() != ' ') {
                        graph[x + room.xBias()][y + room.yBias()] = tiles[x][y];
                    }
                }
            }
        }

        wallOffEdges(graph);
    }

    public TETile[][] getGraph() {
        return TETile.copyOf(graph);
    }

    private static void wallOffEdges(TETile[][] tiles) {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y].character() != ' ') {
                    if (x == 0 || y == 0 || x == tiles.length - 1 || y == tiles[0].length - 1) {
                        tiles[x][y] = Tileset.WALL;
                    } else {
                        for (int i = -1; i <= 1; i++) {
                            for (int j = -1; j <= 1; j++) {
                                if (tiles[x + i][y + j].character() == ' ') {
                                    tiles[x][y] = Tileset.WALL;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void setTile(TETile tile, int x, int y) {
        graph[x][y] = tile;
    }

    public TETile getTile(int x, int y) {
        return new TETile(graph[x][y], graph[x][y].getTextColor());
    }

    public boolean isWall(int x, int y) {
        return graph[x][y].description().equals("wall");
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean isEmpty(int x, int y) {
        return graph[x][y].description().equals("floor");
    }

    public ArrayDeque<Snek> getSneks() {
        ArrayDeque<Snek> output = new ArrayDeque<>();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getDescription().equals(Tileset.SNEK.description())) {
                output.addLast((Snek) characters.get(i));
            }
        }
        return output;
    }

    public ArrayDeque<Monie> getMonies() {
        ArrayDeque<Monie> output = new ArrayDeque<>();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getDescription() == Tileset.MONIE.description()) {
                output.addLast((Monie) characters.get(i));
            }
        }
        return output;
    }

    public ArrayDeque<Knuckles> getKnuckles() {
        ArrayDeque<Knuckles> output = new ArrayDeque<>();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getDescription() == Tileset.KNUCKLES.description()) {
                output.addLast((Knuckles) characters.get(i));
            }
        }
        return output;
    }

    public ArrayDeque<HellYeah> getHellYeahs() {
        ArrayDeque<HellYeah> output = new ArrayDeque<>();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getDescription() == Tileset.HELL_YEAH.description()) {
                output.addLast((HellYeah) characters.get(i));
            }
        }
        return output;
    }

    public ArrayDeque<FinanceGuy> getFinanceGuys() {
        ArrayDeque<FinanceGuy> output = new ArrayDeque<>();
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getDescription() == Tileset.FINANCE_GUY.description()) {
                output.addLast((FinanceGuy) characters.get(i));
            }
        }
        return output;
    }

    public Player getPlayer() {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).getDescription().equals(Tileset.BUSINESS_MAN.description())) {
                return (Player) characters.get(i);
            }
        }
        return null;
    }

    public Character getCharacterAt(int x, int y) {
        for (int i = 0; i < characters.size(); i++) {
            Character temp = characters.get(i);
            if (temp.getXPos() == x && temp.getYPos() == y && temp.getDescription()
                    != Tileset.BUSINESS_MAN.description()) {
                return temp;
            }
        }
        return null;
    }

    public void removeMonie(Monie cash) {
        characters.removeElem(cash);
    }

    public void removeHellYeah(HellYeah guy) {
        setTile(Tileset.BUSINESS_MAN, guy.getXPos(), guy.getYPos());
        characters.removeElem(guy);
    }

    public void removeFinanceGuy(FinanceGuy guy) {
        setTile(Tileset.BUSINESS_MAN, guy.getXPos(), guy.getYPos());
        characters.removeElem(guy);
    }

}
