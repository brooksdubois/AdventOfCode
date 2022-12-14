package day5

typealias ContainerIndex = Int
typealias ContainerCount = Int
typealias ContainerName = Char
typealias StackArrangement = Map<ContainerIndex, List<ContainerName>>

data class MovementOperation(val count: ContainerCount, val fromIndex: ContainerIndex, val toIndex: ContainerIndex)

class StackMovementParser(val inputStr: String) {

    val elfStackArrangement: StackArrangement
    val elfMovementOperations: List<MovementOperation>

    init {
        val linesNormalized = fixStartEndWhiteSpace()
        val parsedValues = linesNormalized.divideOnNewLine()

        elfStackArrangement = processInputArrangement(input = parsedValues.first)
        elfMovementOperations = processInputMovementMap(input = parsedValues.second)
    }

    private fun fixStartEndWhiteSpace(): List<String> = inputStr.lines()
        .dropWhile { it.isEmpty() }
        .dropLastWhile { it.isEmpty() }

    private fun List<String>.divideOnNewLine(): Pair<List<String>, List<String>> {
        val splitIndex = this.indexOf("")
        val arrangementString = this.subList(0, splitIndex)
        val movementPairsString = this.subList(splitIndex + 1, this.size)
        return Pair(first = arrangementString, second = movementPairsString)
    }

    private fun processInputArrangement(input: List<String>):Map<Int, List<Char>> {
        val columnDesignators = input.last()
            .filterNot { it.isWhitespace() }
            .toCharArray()
            .map { it.digitToInt() }
            .toList()

        val columnDataNoDesignators = input.dropLast(1)
        val columnsNormalized = columnDataNoDesignators.map { column ->
            column.filterIndexed{ index, _ -> index % 4 == 1 }
        }
        return columnDesignators.map { columnDesignator ->
            val columnValues = columnsNormalized.map {
               it.toCharArray().getOrNull(columnDesignator-1) ?: ' '
            }
            val columnContainerStack = columnValues.filterNot { it.isWhitespace() }.reversed()
            return@map columnDesignator to columnContainerStack
        }.toMap()
    }

    private fun String.takeFirstInt() = this.toCharArray()
        .takeWhile { it.isDigit() }
        .joinToString("")
        .toInt()

    private fun processInputMovementMap(input: List<String>): List<MovementOperation>
        = input.map { movementString ->
            var tempString = movementString.replace("move ", "")
            val containerCount = tempString.takeFirstInt()
            tempString = tempString.replace( "$containerCount from ", "")
            val fromValue = tempString.takeFirstInt()
            tempString = tempString.replace("$fromValue to ", "")
            val toValue = tempString.takeFirstInt()
            return@map MovementOperation(count = containerCount, fromIndex = fromValue, toIndex = toValue)
        }
}
