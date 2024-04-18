package sr.entity;

/**
 * General type for score board. May be used for various games, such as football, basketball and cricket.
 */
public interface Scoreboard {
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
