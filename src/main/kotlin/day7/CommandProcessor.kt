package day7

const val dollar: ReservedKeyword = "$"
const val callDirectory: ReservedKeyword = "cd"
const val listFiles: ReservedKeyword = "ls"
const val error: ReservedKeyword = "ERROR"
const val directory: ReservedKeyword = "dir"
const val baseDirectory: ReservedKeyword = "/"
const val doubleDot: ReservedKeyword = ".."

enum class SystemSymbol(val strValue: ReservedKeyword) {
    BASH_DOLLAR(strValue = dollar),
    DIRECTORY(strValue = directory),
    BASE_DIR(strValue = baseDirectory),
    MOVE_UP_DIR(strValue = doubleDot)
}

enum class OperatingCommand(val strValue: ReservedKeyword) {
    CALL_DIR(strValue = callDirectory),
    LIST_FILES(strValue = listFiles),
    UNKNOWN_COMMAND(strValue = error)
}

typealias ReservedKeyword = String
typealias CommandInput = String?
typealias CommandOutput = MutableList<String>?
typealias Command = Pair<ReservedKeyword, CommandInput>
typealias Operation = Pair<Command?, CommandOutput>

data class CommandLogEntry(
    val command: OperatingCommand,
    val input: CommandInput = null,
    val output: CommandOutput = mutableListOf()
)

class CommandProcessor {

    fun parseCommand(inputStrLine: String): CommandLogEntry {
        val noBash = inputStrLine.drop(2)
        val commandInputStr = noBash.takeLastWhile { !it.isWhitespace() }
        return when (noBash.takeWhile { !it.isWhitespace() }) {
            callDirectory -> CommandLogEntry(command = OperatingCommand.CALL_DIR, input = commandInputStr)
            listFiles -> CommandLogEntry(command = OperatingCommand.LIST_FILES)
            else -> CommandLogEntry(command = OperatingCommand.UNKNOWN_COMMAND)
        }
    }
}
