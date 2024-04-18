package sr.api.factory;

import sr.api.Scoreboard;

import java.lang.reflect.InvocationTargetException;

public class ScoreboardFactory {
    public static Scoreboard get(String gameName) throws RuntimeException {
        try {
            System.out.println("Creating new scoreboard...");
            return (Scoreboard) Class.forName(gameName).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
