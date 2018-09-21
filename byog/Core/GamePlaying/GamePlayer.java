package byog.Core.GamePlaying;


import byog.Core.Characters.Player;

import byog.Core.Characters.Character;
import byog.Core.WorldMaking.CoordinateSystem;


import java.util.Random;

public class GamePlayer {

    public static void placePlayer(Character player, CoordinateSystem c) {
        for (int i = 0; i < 40; i++) {
            for (int x = 0; x < i; x++) {
                for (int y = 0; y < i; y++) {
                    if (c.isEmpty(x, y)) {
                        player.updatePosition(c, x, y);
                        c.addToCharacters(player);
                        return;
                    }
                }
            }
        }
    }

    public static void movePlayer(CoordinateSystem world, char input) {
        Player prospectiveHaasMajor = world.getPlayer();
        prospectiveHaasMajor.move(world, input);
    }


    public static void setUpLevel(CoordinateSystem world, Random rng) {
        Character prospectiveHaasMajor = new Player();
        placePlayer(prospectiveHaasMajor, world);
    }

    public static void initializeLevel(CoordinateSystem world, Random rng) {
        Level L = world.getLevel();
        GamePlayer.setUpLevel(world, rng);
    }

}
