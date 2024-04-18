package sr.game.football;

import sr.entity.Team;
import sr.game.Game;

public class FootballGame extends Game {
    public FootballGame(Team homeTeam, Team awayTeam, boolean startGame) {
        super(homeTeam, awayTeam, startGame);
    }
}
