import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sr.entity.Team;
import sr.game.football.FootballScoreboard;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith({MockitoExtension.class})
public class FootballScoreboardTests {
    @Spy
    static private FootballScoreboard scoreboard;
    static private Team homeTeam = null;
    static private Team awayTeam = null;

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
        scoreboard.updateScore(homeTeam, (short) 2, awayTeam, (short) 7);
        fail();
    }

    @Test
    @DisplayName("positive: finish game")
    public void finishGameTest() {
        scoreboard.finishGame(homeTeam, awayTeam);
        fail();
    }

    @Test
    @DisplayName("positive: get game score summary")
    public void getScoreboardSummaryTest() {
        scoreboard.getScoreboardSummary();
        fail();
    }
}


