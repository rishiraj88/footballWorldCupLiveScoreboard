package sr.game;

import lombok.Data;
import sr.entity.Team;

import java.time.OffsetDateTime;

@Data
public abstract class Game {
    protected Team teamOne = null;
    protected short teamOneScore = 0;
    protected Team teamTwo = null;
    protected short teamTwoScore = 0;
    protected OffsetDateTime startTime = null;

    public Game(Team homeTeam, Team awayTeam, boolean startGame) {
        if (null == homeTeam || null == awayTeam) throw new IllegalArgumentException();
        this.teamOne = homeTeam;
        this.teamTwo = awayTeam;
        if (startGame) startTime = OffsetDateTime.now();
    }
}
