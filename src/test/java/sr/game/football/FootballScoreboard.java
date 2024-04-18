package sr.game.football;

import sr.entity.Scoreboard;
import sr.entity.Team;
import sr.util.FootballGameComparator;

import java.util.concurrent.PriorityBlockingQueue;

public class FootballScoreboard implements Scoreboard {
    private PriorityBlockingQueue<FootballGame> board = new PriorityBlockingQueue<>(7, FootballGameComparator.get());

    @Override
    public boolean startGame(Team homeTeam, Team awayTeam) {
        //create and start a game
        // add the ongoing game to score board
        boolean gameStarted = board.offer(new FootballGame(homeTeam, awayTeam, true));
        board.stream().forEach(game -> System.out.println(game + " started!"));
        return gameStarted;
    }

    @Override
    public void updateScore(Team homeTeam, short homeTeamScore, Team awayTeam, short awayTeamScore) {
        //update scores of the game identified by its teams
    }

    @Override
    public void finishGame(Team homeTeam, Team awayTeam) {
        // mark a game as finished, and make sure to remove it from score board

    }

    @Override
    public String getScoreboardSummary() {
        // format, sort and then return the scores of all ongoing matches
        //board.stream().forEach(System.out::println);
        return "";
    }
}
