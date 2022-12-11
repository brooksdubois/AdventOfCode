package day2

fun main(args: Array<String>) {

//    val startStr = """A Y
//    B X
//    C Z"""

    val startStr = realData
    val rpsRulesOpponent = RPSRules(mapOf('A' to RPS.ROCK, 'B' to RPS.PAPER, 'C' to RPS.SCISSORS))
    val rpsRulesOutcome = RPSOutComeRules(mapOf('X' to Outcome.LOSE, 'Y' to Outcome.TIE, 'Z' to Outcome.WIN))

    val rpsParser = RPSGameParser(startStr = startStr, rulesOpponent = rpsRulesOpponent, outcomeRules = rpsRulesOutcome)

    val gameResults = rpsParser.processGamesWinFinder()
    println("The result is: $gameResults")
    println("The total of the game is: ${gameResults.sum()}")

}
