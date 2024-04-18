import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import sr.api.Scoreboard;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith({MockitoExtension.class})
public class ScoreboardInstanceTests {
    @AfterEach
    public void destroy() {
        Scoreboard.clearAll();
    }

    @Test
    @DisplayName("Get instance: positive")
    public void getFootballScoreboardInstanceTest() {
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
    }

    @Test
    @DisplayName("Get instance: negative")
    public void getSwimmingScoreboardInstanceTest() {
        assertThrows(ClassNotFoundException.class, () -> Scoreboard.getInstance("Swimming"));
    }

    @Test
    @DisplayName("Get instance: positive: get six instances of a single game type")
    public void getSixFootballScoreboardInstancesTest() {
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
        assertDoesNotThrow(() -> Scoreboard.getInstance("Football"));
    }
}