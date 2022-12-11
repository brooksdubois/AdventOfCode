package day2


fun main(args: Array<String>) {

    val startStr = """A Y
    B X
    C Z"""

    //val startStr = realData

    val rpsRulesOpponent = RPSRules(mapOf('A' to RPS.ROCK, 'B' to RPS.PAPER, 'C' to RPS.SCISSORS))
    val rpsRulesPlayer = RPSRules(mapOf('X' to RPS.ROCK, 'Y' to RPS.PAPER, 'Z' to RPS.SCISSORS))

    val rpsParser = RPSGameParser(startStr = startStr, rulesPlayer = rpsRulesPlayer, rulesOpponent = rpsRulesOpponent)

    val gameResults = rpsParser.processGamesStraightUp()

    println("The result is: $gameResults")
    println("The total of the game is: ${gameResults.sum()}")

}
