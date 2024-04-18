import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sr.game.Team;
import sr.game.football.FootballGame;
import sr.game.football.FootballScoreboard;
import sr.util.FootballGameComparator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class FootballScoreboardTests {
    @Spy
    static private FootballScoreboard scoreboard;
    static private Team homeTeam = null;
    static private Team awayTeam = null;

    @Nested
    class ScoreTests {

        private PriorityBlockingQueue<FootballGame> board = new PriorityBlockingQueue<>(7, FootballGameComparator.get());

        private Set<GameMatch> matchSet;

        @BeforeEach
        void init() {
            when(scoreboard.getBoard()).thenReturn(board);
            matchSet = startGames((short) 9);
            assignTeams();

        }

        @Test
        @DisplayName("Update scores: positive")
        public void updateScoresTest() {
            scoreboard.updateScore(homeTeam, (short) 2, awayTeam, (short) 7);
        }

        @Test
        @DisplayName("Update scores: negative: with home team null")
        public void updateScoresWithHomeTeamNullTest() {
            homeTeam = null;
            System.out.print("(homeTeam: " + homeTeam);
            System.out.println(" ; awayTeam: " + awayTeam + ")");
            scoreboard.updateScore(null, (short) 2, awayTeam, (short) 7);
        }

        @Test
        @DisplayName("Update scores: negative: with away team null")
        public void updateScoresWithAwayTeamNullTest() {
            awayTeam = null;
            System.out.print("(homeTeam: " + homeTeam);
            System.out.println(" ; awayTeam: " + awayTeam + ")");
            scoreboard.updateScore(homeTeam, (short) 2, awayTeam, (short) 7);
        }

        @Test
        @DisplayName("Update scores: negative: make the score of home team negative")
        public void updateScoresOfHomeTeamToNegativeTest() {
            assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore(null, (short) -1, awayTeam, (short) 7));
        }

        @Test
        @DisplayName("Update scores: negative: make the score of away team negative")
        public void updateScoresOfAwayTeamToNegativeTest() {
            assertThrows(IllegalArgumentException.class, () -> scoreboard.updateScore(null, (short) 5, awayTeam, (short) -125));
        }

        @Test
        @DisplayName("Finish game: positive: finish at least one game")
        public void finishGameTest() {
            System.out.print("(homeTeam: " + homeTeam);
            System.out.println(" ; awayTeam: " + awayTeam + ")");
            assertTrue(scoreboard.finishGame(homeTeam, awayTeam));
        }

        @Test
        @DisplayName("Finish game: alternate: finish no games")
        public void finishNoGamesTest() {
            homeTeam = new Team("India");
            awayTeam = new Team("Germany");
            System.out.print("(homeTeam: " + homeTeam);
            System.out.println(" ; awayTeam: " + awayTeam + ")");
            assertFalse(scoreboard.finishGame(homeTeam, awayTeam));
        }

        @Test
        @DisplayName("Game score summary: positive: get summary")
        public void getScoreboardSummaryTest() {
            Random random = new Random();
            for (GameMatch teamPair : matchSet) {
                updateScores(new Team(teamPair.teamOne), (short) random.nextInt(12), new Team(teamPair.teamTwo), (short) random.nextInt(6));
            }
            board.stream().forEach(System.out::print);
            System.out.println("\nScore Summary: " + scoreboard.getScoreboardSummary());
        }

        private void updateScores(Team homeTeam, short team1Score, Team awayTeam, short team2Score) {
            board.stream().filter(footballGame -> footballGame.getTeamOne().equals(homeTeam) && footballGame.getTeamTwo().equals(awayTeam)).findFirst().ifPresent(g -> g.setTeamOneScore(team1Score));
            board.stream().filter(footballGame -> footballGame.getTeamOne().equals(homeTeam) && footballGame.getTeamTwo().equals(awayTeam)).findFirst().ifPresent(g -> g.setTeamTwoScore(team2Score));
        }

        private Team[] assignTeams() {
            GameMatch matchPair = matchSet.stream().findAny().get();
            homeTeam = new Team(matchPair.teamOne);
            awayTeam = new Team(matchPair.teamTwo);
            return new Team[]{homeTeam, awayTeam};
        }

        private Set<GameMatch> startGames(short nGames) {
            String[] allTeams = {"Mexico", "Canada", "Spain", "Brazil", "Germany", "France", "Uruguay", "Italy", "Argentina", "Australia"};
            String teamOne, teamTwo;
            Random random = new Random();
            Set<GameMatch> matchSet = new HashSet<>();
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
            System.out.println("\nboard.size(): " + board.size());
            System.out.println("matchSet.size(): " + matchSet.size());
            return matchSet;
        }

        @AfterEach
        void afterTest() {
            board = null;
            matchSet = null;
        }

        private static record GameMatch(String teamOne, String teamTwo) {
            }
        }

    @Nested
    class StartGameTests {

        @BeforeAll
        static void init() {
            when()
            //initialize teams
            homeTeam = new Team("Mexico");
            awayTeam = new Team("Canada");
        }

        @AfterAll
        static void destroy() {
            // remove teams
            homeTeam = null;
            awayTeam = null;
        }

        @Test
        @DisplayName("Start game: positive")
        public void startGameTest() {
            scoreboard.startGame(homeTeam, awayTeam);
        }

        @Test
        @DisplayName("Start game: negative: with home team as null")
        public void startGameWithHomeTeamNameNullTest() {
            awayTeam = new Team("Canada");
            assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame(null, awayTeam));
        }

        @Test
        @DisplayName("Start game: negative: with away team as null")
        public void startGameWithAwayTeamNameNullTest() {
            homeTeam = new Team("Canada");
            assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame(homeTeam, null));
        }

        @Test
        @DisplayName("Start game: negative: with both teams as null")
        public void startGameWithBothTeamNamesNullTest() {
            assertThrows(IllegalArgumentException.class, () -> scoreboard.startGame(null, null));
        }
    }
}


