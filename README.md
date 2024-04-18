# footballWorldCupLiveScoreboard
a data feed library for Live Score Board of Football World Cup

## What It Features

1. _What it is:_ a live football World Cup Scoreboard
2. _What it shows:_ all ongoing football matches from our providers, and the live scores

## Main Functionalities, in a Bit Technical Terms

1. _start a new game:_ initialize the score in the new game as ("0" -- "0") and add the game data to scoreboard for display as "homeTeam 0 - awayTeam 0"
2. _update score:_ update an ongoing match with inputs: newScoreToShow,newScoreToShow
3. _finish game:_ Mark an ongoing game (match) as finished and remove it from scoreboard
4. _get summary:_ return the summaries (teams and scores) of all ongoing games for ingestion to client systems. One
   possible format with unique pairs is:
<pre>homeTeam score - awayTeam score
homeTeam score - awayTeam score
homeTeam score - awayTeam score
homeTeam score - awayTeam score</pre>

5. Right now only football games are supported. Support for more game types will require further sophistication of the library.

## Hint about Internal System

A number of sorting rules apply to resolve the order of team pairs for display on score board.

## Assumptions, too Technical, Only for Product Owners, Business Analysts and Programmers

- The first team name in the data from any vendor is deemed homeTeam. The other one is deemed awayTeam.
- The first score in the data from any vendor is deemed homeTeam score. The other one is deemed awayTeam score.
- The highest score for any team in any match is assumed to be 127.
- Negative scores are considered to be invalid and are thus not supported in the current implementation of this library.If an ongoing game with both team names matching is not found in internal data store, then the method used to update the scores finishes without any exception thrown. In short, scores are updated only for the ongoing games found in the internal store of data. _A simple implementation of finishing a game is implemented in this release. It approximates the finish time of the game to its own system time at the moment of effecting such an update of game state. A more versatile implementation is also possible wherein the finish time for the game in question is to be supplied by the respective data provider (data source).
- If an ongoing game with a valid pair of team names is not found in internal data store, then the method used to update
  the scores finishes without any exception thrown. In short, scores are updated only for the ongoing games found in
  the internal store of data.
- A simple implementation of finishing a game is implemented in this release. It approximates the finish time of the
   game to its own system time at the moment of effecting such an update of game state. A more versatile implementation
   is also possible wherein the finish time for the game in question is to be supplied by the respective data provider (
   data source).
- 
## Implementation Tools

- Java 17
- IDE with JDK support
- Maven for project object model and dependency management
- lombok for quick data method and constructor facilitation

# Who We Are

SR - The 21st SportsTech Co.

# Contact Points

- Project Portfolio: https://github.com/rishiraj88
- Contact Card: https://bio.link/rishiraj49de
