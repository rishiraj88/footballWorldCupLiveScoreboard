package cl;

import sr.api.Scoreboard;
import sr.game.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args) throws ClassNotFoundException, IOException {

            Scoreboard board = Scoreboard.getInstance("Football");
            board.startGame(new Team("Germany"), new Team("Italy"));
            System.out.println(board.getScoreboardSummary());
        Stream<String> teamPairs = Files.lines(Paths.get(".\\src\\test\\resources\\teams.csv"));
        teamPairs.forEach(s -> {
            Team homeTeam = new Team(s.split(",")[0]);
            Team awayTeam = new Team(s.split(",")[1]);
            board.startGame(homeTeam, awayTeam);
        });
        Team homeTeam = new Team("Mexico");
        Team awayTeam = new Team("Argentina");
        board.updateScore(homeTeam, (short) 23, awayTeam, (short) 33);
        homeTeam = new Team("Germany");
        awayTeam = new Team("Italy");
        board.updateScore(homeTeam, (short) 23, awayTeam, (short) 33);
        System.out.println(board.getScoreboardSummary());

    }
}
