package day2


class RPSGameParser(
    private val startStr: String,
    private val rulesOpponent: RPSRules,
    private val rulesPlayer: RPSRules? = null,
    private val outcomeRules: RPSOutComeRules? = null
){

    private fun String.parseLine() = this
        .filterNot { it.isWhitespace() }
        .toCharArray()
        .toList()

    fun processGamesStraightUp(): List<Int> = startStr
        .lines()
        .toList()
        .map{ gameValues: String ->
            val gameValues = gameValues.parseLine()
            val opponent: RPS = rulesOpponent.convertToValue(gameValues.first())
            val player: RPS = rulesPlayer!!.convertToValue(gameValues.last())
            val rpsGame = RPSGameStraightUp(player = player, opponent = opponent)
            return@map rpsGame.findGameValue()
        }

    fun processGamesWinFinder(): List<Int> = startStr
        .lines()
        .toList()
        .map{ gameValues: String ->
            val gameValues = gameValues.parseLine()
            val opponent: RPS = rulesOpponent.convertToValue(gameValues.first())
            val outcome: Outcome = outcomeRules!!.convertToValue(gameValues.last())
            val rpsGame = RPSGameWinFinder(opponent = opponent, result = outcome)
            return@map rpsGame.findGameValue()
        }
}


