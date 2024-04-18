package sr.api;

import sr.api.factory.ScoreboardFactory;
import sr.game.Team;

import java.util.HashSet;
import java.util.Set;

/**
 * General type for score board. May be used for various games, such as football, basketball and cricket.
 */
public interface Scoreboard {
    public static final Set<Scoreboard> _INSTANCES = new HashSet();

    public static Scoreboard getInstance(String gameName) throws ClassNotFoundException {
        String fqClassName = "sr.game.football." + gameName + "Scoreboard";
        Class cls = Class.forName(fqClassName);
        if (!_INSTANCES.stream().anyMatch(game -> game.getClass().equals(cls))) {
            _INSTANCES.add(ScoreboardFactory.get(fqClassName));
            System.out.printf("Created new %s instance.", gameName);
        }
        return _INSTANCES.stream().findFirst().get();
    }
    /**
     * to create a new game model and add it to score board
     *
     * @param homeTeam
     * @param awayTeam
     * @return
     */
    public boolean startGame(Team homeTeam, Team awayTeam);

    /**
     * to accept the score data for the two teams and update the live scores on our board.
     * Notes:
     * 1. The incoming numbers are absolute scores, not increments.
     * 2. The order of team names and score numbers is important.
     *
     * @param homeTeam
     * @param homeTeamScore
     * @param awayTeam
     * @param awayTeamScore
     */
    public void updateScore(Team homeTeam, short homeTeamScore, Team awayTeam, short awayTeamScore); //assumption on order of scores

    /**
     * to mark a game as finished (ended) and remove it from live score board also.
     *
     * @param homeTeam
     * @param awayTeam
     */
    public boolean finishGame(Team homeTeam, Team awayTeam);

    /**
     * to cook, format and return the score summary data of all ongoing matches for ingestion to
     * client systems, which utilize this library (https://github.com/rishiraj88/footballWcScoreboard).
     * README.md file may help with more up-to-date notes and assumptions.
     *
     * @return a summary of games in progress
     */
    public String getScoreboardSummary();
}
