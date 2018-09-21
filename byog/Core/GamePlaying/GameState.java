package byog.Core.GamePlaying;

import byog.Core.WorldMaking.CoordinateSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;

public class GameState {
    CoordinateSystem game;

    public static void saveState(CoordinateSystem game) {
        try {
            FileOutputStream fileOut = new FileOutputStream("game.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(game);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static CoordinateSystem loadState() {
        try {
            CoordinateSystem game = null;
            FileInputStream fileIn = new FileInputStream("game.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            game = (CoordinateSystem) in.readObject();
            in.close();
            fileIn.close();
            return game;
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
