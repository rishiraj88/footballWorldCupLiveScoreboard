package cl;

import sr.api.Scoreboard;
import sr.game.Team;

public class Client {
    public static void main(String[] args) {
        try {
            Scoreboard board = Scoreboard.getInstance("Football");
            board.startGame(new Team("Germany"), new Team("Italy"));
            System.out.println(board.getScoreboardSummary());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Scoreboard board = Scoreboard.getInstance("Basketball");
            board.startGame(new Team("Germany"), new Team("Spain"));
            System.out.println(board.getScoreboardSummary());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
