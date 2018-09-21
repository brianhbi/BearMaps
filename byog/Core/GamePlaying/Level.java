package byog.Core.GamePlaying;

import java.io.Serializable;

public class Level implements Serializable {

    private int level;
    private int snekCount;
    private int knucklesCount;
    private int financeCount;
    private int monieCount;
    private int monieValue;
    private int hellYeahCount;
    private String annoucnement;
    private String filePath;

    public Level() {
        this.level = level = 1;
        this.snekCount = snekCount = 0;
        this.knucklesCount = knucklesCount = 0;
        this.financeCount = financeCount = 0;
        this.monieCount = monieCount = 0;
        this.monieValue = monieValue = 0;
        this.hellYeahCount = hellYeahCount = 0;
        this.annoucnement = annoucnement = "suck my dick";
        this.filePath = filePath = "i sharted";
    }

    public static Level getLevel(int level) {
        return new Level();
    }
}
