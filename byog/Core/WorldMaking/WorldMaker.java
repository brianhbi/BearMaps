package byog.Core.WorldMaking;

import byog.Core.Utilities.ArrayDeque;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldMaker {
    public static CoordinateSystem setUpWorld(Random rng) {
        CoordinateSystem world = new CoordinateSystem(Tileset.WIDTH, Tileset.HEIGHT);
        double threshold = Tileset.HEIGHT * Tileset.WIDTH * .35;
        WorldMaker.setUpAllRooms(world, rng, (int) threshold, Tileset.WIDTH, Tileset.HEIGHT, 8, 20);
        WorldMaker.connectAllRooms(world, rng);
        world.renderGraph();
        return world;
    }

    public static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static double getNorm(int x1, int y1, int x2, int y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }

    public static void placeRandomRoom(CoordinateSystem c, Random r,
                                       int x, int y, int min, int max) {
        int counter = 0;
        while (counter < 100) {
            int roomWidth = rng(r, min, max);
            int roomHeight = rng(r, min, max);
            int xCoor = rng(r, 0, x - roomWidth);
            int yCoor = rng(r, 0, y - roomHeight);

            if (c.isAvailable(xCoor, yCoor, roomWidth, roomHeight)) {
                Shape rect = new Rectangle(roomWidth, roomHeight);
                c.addRoom(new Room(rect, xCoor, yCoor));
                counter = 100;
            }
            counter++;
        }
    }

    public static void setUpAllRooms(CoordinateSystem c, Random r,
                                     int areaThreshold, int x, int y, int min, int max) {
        int areaScore = 0;

        while (areaScore < areaThreshold) {
            placeRandomRoom(c, r, x, y, min, max);
            areaScore += c.getRooms().get(c.getRooms().size() - 1).getShape().area();
        }
    }

    public static void setUpAllRoomsLimit(CoordinateSystem c, Random r,
                                          int count, int x, int y, int min, int max) {
        int roomCount = 0;

        while (roomCount < count) {
            placeRandomRoom(c, r, x, y, min, max);
            roomCount++;
        }
    }


    public static int rng(Random r, int lowerBound, int upperBound) {
        int mod = upperBound - lowerBound + 1;
        return (Math.abs(r.nextInt()) % mod) + lowerBound;
    }

    public static int[] getMiddle(Room r) {

        int[] midpoint = new int[2];
        midpoint[0] = r.xBias() + r.width() / 2;
        midpoint[1] = r.yBias() + r.height() / 2;

        return midpoint;
    }

    public static void connectAllRooms(CoordinateSystem c, Random r) {
        int roomCount = c.getRooms().size();
        Room r1, r2;
        ArrayDeque<Room> excludes = new ArrayDeque<>();

        for (int i = 0; i < roomCount; i++) {
            r1 = c.getRooms().get(i % roomCount);
            r2 = c.getRooms().get((i + 1) % roomCount);
            c.addRoom(makeConnection(r1, r2, r));
        }
    }


    public static Room makeConnection(Room r1, Room r2, Random r) {
        int[] middle1 = getMiddle(r1);
        int[] middle2 = getMiddle(r2);
        HallwayMaker maker = new HallwayMaker(Tileset.WIDTH, Tileset.HEIGHT, rng(r, 3, 4));
        if (middle1[0] <= middle2[0] && middle1[1] <= middle2[1]) {
            return pathUpRight(maker, r1, r2, r);
        } else if (middle1[0] >= middle2[0] && middle1[1] <= middle2[1]) {
            return pathUpLeft(maker, r1, r2, r);
        } else if (middle1[0] <= middle2[0] && middle1[1] >= middle2[1]) {
            return pathDownRight(maker, r1, r2, r);
        } else {
            return pathDownLeft(maker, r1, r2, r);
        }
    }


    private static Room pathUpRight(HallwayMaker maker, Room r1, Room r2, Random r) {
        int[] middle = getMiddle(r1);
        int startX = middle[0];
        int startY = middle[1];
        middle = getMiddle(r2);
        int midX = middle[0];
        int midY = middle[1];
        maker.setPtr(startX, startY);
        int[] ptr = maker.getPtr();
        while (ptr[0] < midX || ptr[1] < midY) {
            ptr = maker.getPtr();
            if (ptr[0] < midX && ptr[1] < midY) {
                if (rng(r, 0, 1) == 0) {
                    maker.extendRight();
                } else {
                    maker.extendUp();
                }
            } else if (ptr[0] < midX) {
                maker.extendRight();
            } else {
                maker.extendUp();
            }
        }

        return new Room(maker.getHallway(), 0, 0);
    }

    private static Room pathUpLeft(HallwayMaker maker, Room r1, Room r2, Random r) {
        int[] middle = getMiddle(r1);
        int startX = middle[0];
        int startY = middle[1];
        middle = getMiddle(r2);
        int midX = middle[0];
        int midY = middle[1];
        maker.setPtr(startX, startY);
        int[] ptr = maker.getPtr();
        while (ptr[0] > midX || ptr[1] < midY) {
            ptr = maker.getPtr();
            if (ptr[0] > midX && ptr[1] < midY) {
                if (rng(r, 0, 1) == 0) {
                    maker.extendLeft();
                } else {
                    maker.extendUp();
                }
            } else if (ptr[0] > midX) {
                maker.extendLeft();
            } else {
                maker.extendUp();
            }
        }

        return new Room(maker.getHallway(), 0, 0);
    }

    private static Room pathDownLeft(HallwayMaker maker, Room r1, Room r2, Random r) {
        int[] middle = getMiddle(r1);
        int startX = middle[0];
        int startY = middle[1];
        middle = getMiddle(r2);
        int midX = middle[0];
        int midY = middle[1];
        maker.setPtr(startX, startY);
        int[] ptr = maker.getPtr();
        while (ptr[0] > midX || ptr[1] > midY) {
            ptr = maker.getPtr();
            if (ptr[0] > midX && ptr[1] > midY) {
                if (rng(r, 0, 1) == 0) {
                    maker.extendLeft();
                } else {
                    maker.extendDown();
                }
            } else if (ptr[0] > midX) {
                maker.extendLeft();
            } else {
                maker.extendDown();
            }
        }

        return new Room(maker.getHallway(), 0, 0);
    }


    private static Room pathDownRight(HallwayMaker maker, Room r1, Room r2, Random r) {
        int[] middle = getMiddle(r1);
        int startX = middle[0];
        int startY = middle[1];
        middle = getMiddle(r2);
        int midX = middle[0];
        int midY = middle[1];
        maker.setPtr(startX, startY);
        int[] ptr = maker.getPtr();
        while (ptr[0] < midX || ptr[1] > midY) {
            ptr = maker.getPtr();
            if (ptr[0] < midX && ptr[1] > midY) {
                if (rng(r, 0, 1) == 0) {
                    maker.extendRight();
                } else {
                    maker.extendDown();
                }
            } else if (ptr[0] < midX) {
                maker.extendRight();
            } else {
                maker.extendDown();
            }
        }

        return new Room(maker.getHallway(), 0, 0);
    }
}


