import lombok.Data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sr.entity.Team;
import sr.game.football.FootballGame;
import sr.game.football.FootballScoreboard;
import sr.util.FootballGameComparator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FootballScoreboardTests {
    @Spy
    static private FootballScoreboard scoreboard;

    static private Team homeTeam = null;
    static private Team awayTeam = null;
    private PriorityBlockingQueue<FootballGame> board = new PriorityBlockingQueue<>(7, FootballGameComparator.get());

    @Test
    @DisplayName("positive: start game")
    public void startGameTest() {
        homeTeam = new Team("Mexico");
        awayTeam = new Team("Canada");
        scoreboard.startGame(homeTeam, awayTeam);
    }

    @Test
    @DisplayName("negative: start game with home team as null")
    public void startGameWithHomeTeamNameNullTest() {
        awayTeam = new Team("Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame(null, awayTeam));
    }

    @Test
    @DisplayName("negative: start game with away team as null")
    public void startGameWithAwayTeamNameNullTest() {
        homeTeam = new Team("Canada");
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame(homeTeam, null));
    }

    @Test
    @DisplayName("negative: start game with both teams as null")
    public void startGameWithBothTeamNamesNullTest() {
        assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame(null, null));
    }

    @Test
    @DisplayName("positive: update scores")
    public void updateScoresTest() {
        when(scoreboard.getBoard()).thenReturn(board);
        startGames((short) 9);
        assignTeams();
        scoreboard.updateScore(homeTeam, (short) 2, awayTeam, (short) 7);
    }

    @Test
    @DisplayName("negative: update scores with home team null")
    public void updateScoresWithHomeTeamNullTest() {
        startGames((short) 7);
        assignTeams();
        homeTeam = null;
        when(scoreboard.getBoard()).thenReturn(board);
        System.out.print("(homeTeam: " + homeTeam);
        System.out.println(" ; awayTeam: " + awayTeam + ")");
        scoreboard.updateScore(null, (short) 2, awayTeam, (short) 7);
    }

    @Test
    @DisplayName("negative: update scores with away team null")
    public void updateScoresWithAwayTeamNullTest() {
        startGames((short) 7);
        assignTeams();
        awayTeam = null;
        System.out.print("(homeTeam: " + homeTeam);
        System.out.println(" ; awayTeam: " + awayTeam + ")");
        when(scoreboard.getBoard()).thenReturn(board);
        scoreboard.updateScore(homeTeam, (short) 2, awayTeam, (short) 7);
    }

    @Test
    @DisplayName("negative: make the score of home team negative")
    public void updateScoresOfHomeTeamToNegativeTest() {
        startGames((short) 7);
        assignTeams();
        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore(null, (short) -1, awayTeam, (short) 7));
    }

    private Set<GameMatch> matchSet;

    @Test
    @DisplayName("negative: make the score of away team negative")
    public void updateScoresOfAwayTeamToNegativeTest() {
        startGames((short) 7);
        assignTeams();

        assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore(null, (short) 5, awayTeam, (short) -125));
    }

    @Test
    @DisplayName("positive: finish game")
    public void finishGameTest() {
        when(scoreboard.getBoard()).thenReturn(board);
        startGames((short) 6);
        assignTeams();
        System.out.print("(homeTeam: " + homeTeam);
        System.out.println(" ; awayTeam: " + awayTeam + ")");
        scoreboard.finishGame(homeTeam, awayTeam);
    }

    @Test
    @DisplayName("positive: get game score summary")
    public void getScoreboardSummaryTest() {
        scoreboard.getScoreboardSummary();
        fail();
    }

    private Team[] assignTeams() {
        GameMatch matchPair = matchSet.stream().findAny().get();
        homeTeam = new Team(matchPair.getHome());
        awayTeam = new Team(matchPair.getAway());
        return new Team[]{homeTeam, awayTeam};
    }

    private Set<GameMatch> startGames(short nGames) {
        String[] allTeams = {"Mexico", "Canada", "Spain", "Brazil", "Germany", "France", "Uruguay", "Italy", "Argentina", "Australia"};
        String teamOne, teamTwo;
        Random random = new Random();
        matchSet = new HashSet<>();
        for (short ctr = 0; matchSet.size() < nGames; ctr++) {
            int i = random.nextInt(allTeams.length);
            int j = random.nextInt(allTeams.length);
            while (j == i) j = random.nextInt(allTeams.length);
            teamOne = allTeams[i];
            teamTwo = allTeams[j];
            if (matchSet.add(new GameMatch(teamOne, teamTwo))) {
                //   scoreboard.startGame(new Team(teamOne), new Team(teamTwo));
                boolean gameStarted = board.offer(new FootballGame(new Team(teamOne), new Team(teamTwo), true));
            }
        }
        board.stream().forEach(game -> System.out.print(game + "  game added!"));
        System.out.println("board.size(): " + board.size());
        System.out.println("matchSet.size(): " + matchSet.size());

        return matchSet;
    }

    @AfterEach
    void afterTest() {
        //create scoreboard
        board = null;
    }

    @Data
    private static class GameMatch {
        String home;
        String away;

        public GameMatch(String teamOne, String teamTwo) {
            home = teamOne;
            away = teamTwo;
        }
    }
}


