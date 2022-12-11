package day2


enum class RPS(val pointValue: Int) {
    ROCK(pointValue = 1),
    PAPER(pointValue = 2),
    SCISSORS(pointValue = 3)
}

enum class Outcome(val resultValue: Int){
    TIE(resultValue = 3),
    LOSE(resultValue = 0),
    WIN(resultValue = 6)
}

typealias RPSRepresentation = Char
typealias RPSStrategy = Map<RPSRepresentation, RPS>

class RPSRules(private val strategy: RPSStrategy){
    fun convertToValue(charValue: Char): RPS = strategy[charValue]!!
}

class RPSGameStraightUp(private val player: RPS, private val opponent: RPS){

    private fun winnerLogic(): Boolean =
        player == RPS.ROCK && opponent == RPS.SCISSORS ||
        player == RPS.SCISSORS && opponent == RPS.PAPER ||
        player == RPS.PAPER && opponent == RPS.ROCK

    fun findGameValue(): Int = player.pointValue +
        if(player == opponent) Outcome.TIE.resultValue
        else if(winnerLogic()) Outcome.WIN.resultValue
        else Outcome.LOSE.resultValue

}


typealias OutcomeRepresentation = Char
typealias OutcomeStrategy = Map<OutcomeRepresentation, Outcome>

class RPSOutComeRules(private val strategy: OutcomeStrategy){
    fun convertToValue(charValue: Char): Outcome = strategy[charValue]!!
}

class RPSGameWinFinder(private val opponent: RPS, private val result: Outcome){

    fun findGameValue(): Int {
        val myChoice = if(opponent == RPS.ROCK && result == Outcome.WIN){
            RPS.PAPER
        }else if(opponent == RPS.ROCK && result == Outcome.LOSE){
            RPS.SCISSORS
        }else if(opponent == RPS.PAPER && result == Outcome.WIN){
            RPS.SCISSORS
        }else if(opponent == RPS.PAPER && result == Outcome.LOSE){
            RPS.ROCK
        }else if(opponent == RPS.SCISSORS && result == Outcome.WIN){
            RPS.ROCK
        }else if(opponent == RPS.SCISSORS && result == Outcome.LOSE){
            RPS.PAPER
        }else{
            opponent
        }

        return result.resultValue + myChoice.pointValue

    }

}
