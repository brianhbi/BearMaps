package byog.Core;

import byog.Core.GamePlaying.GamePlayer;
import byog.Core.GamePlaying.GameState;
import byog.Core.GamePlaying.Level;
import byog.Core.Utilities.ArrayDeque;
import byog.Core.WorldMaking.CoordinateSystem;
import byog.Core.WorldMaking.WorldMaker;
import byog.TileEngine.TETile;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {
    private CoordinateSystem world;
    /* Feel free to change the width and height. */
    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    long savedGame;
    private static boolean exitProgram;
    private static boolean savedWorld = false;

    public Game() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * a
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {

        ArrayDeque<java.lang.Character> inputCommand =
                addCharacterDequeSeed(input); //input converts to arraydeque
        char currentCommand = ' ';
        exitProgram = false;
        while (!inputCommand.isEmpty() && !exitProgram) {
            currentCommand = inputCommand.removeFirst();
            if (currentCommand == 'n' || currentCommand == 'N') {
                world = setUpEverything(new Random(finalParser(inputCommand)));
            } else if (currentCommand == 'l' || currentCommand == 'L' || savedWorld) {
                savedWorld = false;
                world = GameState.loadState();
                if (world == null) {
                    return null;
                }

                char c = ' ';
                boolean exitStatus = false;
                while (!exitStatus && !inputCommand.isEmpty()) {
                    if (!inputCommand.isEmpty()) {
                        c = inputCommand.removeFirst();
                        GamePlayer.movePlayer(world, c);
                        if (c == ':') {
                            if (inputCommand.get(0) == 'q' || inputCommand.get(0) == 'Q') {
                                exitStatus = true;
                                exitProgram = true;
                                GameState.saveState(world);
                            }
                        }
                    }
                }
            } else if (currentCommand == ':') {
                if (inputCommand.get(0) == 'q' || inputCommand.get(0) == 'Q') {
                    exitProgram = true;
                }
            }
        }
        if (world == null) {
            return null;
        }
        return world.getGraph();
    }

    public ArrayDeque<java.lang.Character> addCharacterDequeSeed(String s) {
        ArrayDeque<java.lang.Character> output = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            output.addLast(s.charAt(i));
        }
        return output;
    }


    public long finalParser(ArrayDeque<java.lang.Character> dequeSeed) {

        long output = 0;
        char c = 'a';
        while (c != 's' && c != 'S' && !dequeSeed.isEmpty()) {
            c = dequeSeed.removeFirst();
            if (java.lang.Character.isDigit(c)) {
                int n = java.lang.Character.getNumericValue(c);
                output = output * 10 + n;
            }
            if (c == ':') {
                if (dequeSeed.get(0) == 'q' || dequeSeed.get(0) == 'Q') {
                    exitProgram = true;
                    break;
                }
            }
            if (c == 's' || c == 'S') {
                savedWorld = true;
            }
        }
        return output;
    }

    public static CoordinateSystem setUpEverything(Random rng) {
        CoordinateSystem game = WorldMaker.setUpWorld(rng);
        game.setLevel(Level.getLevel(1));
        GamePlayer.initializeLevel(game, rng);
        GameState.saveState(game);
        return game;
    }

}
