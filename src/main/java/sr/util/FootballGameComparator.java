package sr.util;

import sr.game.football.FootballGame;

import java.util.Comparator;

public interface FootballGameComparator extends Comparator<FootballGame> {
    public static Comparator<? super FootballGame> get() {
        Comparator<FootballGame> byTotalScoreComparator = (gameOne, gameTwo) -> Integer.valueOf(gameOne.getTeamOneScore() + gameOne.getTeamTwoScore()).compareTo(gameTwo.getTeamOneScore() + gameTwo.getTeamTwoScore());

        Comparator<FootballGame> byStartTimeComparator = Comparator.comparing(FootballGame::getStartTime);

        return byTotalScoreComparator.thenComparing(byStartTimeComparator);
    }
}
