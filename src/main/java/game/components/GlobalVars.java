package game.components;

import game.Pair;

public class GlobalVars {
    public static Boolean turn = false; //false = sheep move
    public static Pair sheepCoordinate = new Pair(8,1);
    public static String[][] array = {
            {"WA", "WA", "WA", "WA", "WA", "WA", "WA", "WA", "WA", "WA",},
            {"WA", "O", "W1", "0", "W2", "0", "W3", "0", "W4", "WA",},
            {"WA", "1", "0", "1", "0", "1", "0", "1", "0", "WA",},
            {"WA", "0", "1", "0", "1", "0", "1", "0", "1", "WA",},
            {"WA", "1", "0", "1", "0", "1", "0", "1", "0", "WA",},
            {"WA", "0", "1", "0", "1", "0", "1", "0", "1", "WA",},
            {"WA", "1", "0", "1", "0", "1", "0", "1", "0", "WA",},
            {"WA", "0", "1", "0", "1", "0", "1", "0", "1", "WA",},
            {"WA", "1", "0", "1", "0", "1", "0", "1", "0", "WA",},
            {"WA", "WA", "WA", "WA", "WA", "WA", "WA", "WA", "WA", "WA",}
    };
}
