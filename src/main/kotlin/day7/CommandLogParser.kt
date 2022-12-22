package day7


fun String.fixStartEndWhiteSpace() = this.lines()
    .dropWhile { it.isEmpty() }
    .dropLastWhile { it.isEmpty() }

class CommandLogParser(inputStr: String? = null){
    lateinit var commandLogs: List<CommandLogEntry>
    private val commandProcessor = CommandProcessor()

    init {
       inputStr?.also {
           val inputLines = it.fixStartEndWhiteSpace()
           commandLogs = parseCommandString(inputLines = inputLines)
       }
    }

    fun parseCommandString(inputLines: List<String>): List<CommandLogEntry> {
        fun String.isInputtedCommand() = this.startsWith(SystemSymbol.BASH_DOLLAR.strValue)
        fun nextLineIsCommand(index: Int) = inputLines.getOrNull(index + 1)?.isInputtedCommand() == true
        fun isEndOfList(index: Int) = index == inputLines.size - 1
        val commandOutput = mutableListOf<String>()
        return inputLines.foldIndexed(mutableListOf()){ index, commandAccumulator, inputStrLine ->
            if(inputStrLine.isInputtedCommand())
                commandAccumulator.add(commandProcessor.parseCommand(inputStrLine))
            else {
                commandOutput.add(inputStrLine)
                if(nextLineIsCommand(index = index) || isEndOfList(index = index)) {
                    commandAccumulator.last().output?.addAll(commandOutput)
                    commandOutput.clear()
                }
            }
            return@foldIndexed commandAccumulator
        }
    }
}
