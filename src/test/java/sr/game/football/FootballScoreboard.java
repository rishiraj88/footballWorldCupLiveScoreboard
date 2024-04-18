package sr.game.football;

import lombok.Data;
import sr.entity.Scoreboard;
import sr.entity.Team;
import sr.util.FootballGameComparator;

import java.time.OffsetDateTime;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

@Data
public class FootballScoreboard implements Scoreboard {
    private PriorityBlockingQueue<FootballGame> board = new PriorityBlockingQueue<>(7, FootballGameComparator.get());

    @Override
    public boolean startGame(Team homeTeam, Team awayTeam) {
        //create and start a game
        // add the ongoing game to score board
        boolean gameStarted = board.offer(new FootballGame(homeTeam, awayTeam, true));
        getBoard().stream().forEach(game -> System.out.println(game + " started!"));
        return gameStarted;
    }

    @Override
    public void updateScore(Team homeTeam, short homeTeamScore, Team awayTeam, short awayTeamScore) {
        //update scores of the game identified by its teams
        if (0 > homeTeamScore || 0 > awayTeamScore) throw new IllegalArgumentException("Negative score not good.");
        getBoard().stream().filter(footballGame -> footballGame.getTeamOne().equals(homeTeam) && footballGame.getTeamTwo().equals(awayTeam)).findFirst().ifPresent(g -> g.setTeamOneScore(homeTeamScore));
        getBoard().stream().filter(footballGame -> footballGame.getTeamOne().equals(homeTeam) && footballGame.getTeamTwo().equals(awayTeam)).findFirst().ifPresent(g -> g.setTeamTwoScore(awayTeamScore));
        System.out.print("Updated :: ");
        getBoard().stream().forEach(System.out::println);
    }

    @Override
    public void finishGame(Team homeTeam, Team awayTeam) {
        // mark a game as finished, and make sure to remove it from score board
        System.out.print("finishgame:: (" + homeTeam);
        System.out.println(": " + awayTeam + ")");
        getBoard().stream().filter(footballGame -> footballGame.getTeamOne().equals(homeTeam) && footballGame.getTeamTwo().equals(awayTeam)).findFirst().ifPresent(g -> g.setFinishTime(OffsetDateTime.now()));
        setBoard(getBoard().stream().filter(footballGame -> null == footballGame.getFinishTime()).collect(Collectors.toCollection(() -> new PriorityBlockingQueue<>(7, FootballGameComparator.get()))));
        getBoard().stream().forEach(System.out::println);
    }

    @Override
    public String getScoreboardSummary() {
        // format, sort and then return the scores of all ongoing matches
        //board.stream().forEach(System.out::println);
        return "";
    }
}
