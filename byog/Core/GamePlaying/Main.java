package byog.Core.GamePlaying;

import byog.Core.Game;
import byog.TileEngine.TETile;

/**
 * This is the main entry point for the program. This class simply parses
 * the command line inputs, and lets the byog.Core.Game class take over
 * in either keyboard or input string mode.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        if (args.length > 1) {
            System.out.println("Can only have one argument - the input string");
            System.exit(0);
        } else if (args.length == 1) {
            Game game = new Game();
            TETile[][] worldState1 = game.playWithInputString("n12414swwddsaasddssd");
            TETile[][] worldState2 = game.playWithInputString("ls");
            if (worldState1 != null) {
                System.out.println(TETile.toString(worldState1));
            }

        } else {
            Game game = new Game();
            //game.playWithKeyboard();
        }
       /* TERenderer ter = new TERenderer();
        ter.initialize(100, 30);
        ter.drawText("hi", 20, new Color(255,255,255), 10, 10);
        ter.showFrame();*/
    }
}
