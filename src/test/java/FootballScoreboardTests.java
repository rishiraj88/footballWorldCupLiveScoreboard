import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sr.entity.Team;
import sr.game.football.FootballScoreboard;

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
    @DisplayName("positive: update scores")
    public void updateScoresTest() {
        scoreboard.updateScore(homeTeam, (short) 2, awayTeam, (short) 7);
    }

    @Test
    @DisplayName("positive: finish game")
    public void finishGameTest() {
        scoreboard.finishGame(homeTeam, awayTeam);
    }

    @Test
    @DisplayName("positive: get game score summary")
    public void getScoreboardSummaryTest() {
        scoreboard.getScoreboardSummary();
    }
}


