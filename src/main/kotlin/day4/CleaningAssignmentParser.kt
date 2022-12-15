package day4


typealias SeatAssignment = Int
typealias CleaningRoutine = Pair<SeatAssignment, SeatAssignment>
typealias ElfSchedule = Pair<CleaningRoutine, CleaningRoutine>


class CleaningAssignmentParser(val elfAssignmentStr: String ) {
    val elfAssignments: List<ElfSchedule>

    init {
        elfAssignments = processAssignments()
    }

    private fun String.parseLine() = this.filterNot { it.isWhitespace() }

    private fun List<String>.convertToIntPair() = Pair(
        first = first().toInt(),
        second = last().toInt()
    )

    private fun processAssignments(): List<ElfSchedule> = elfAssignmentStr
        .lines()
        .toList()
        .map{ assignmentValues: String ->
            val assignments = assignmentValues.parseLine()
            val assignments2 = assignments.split(',')
            return@map Pair(
                first = assignments2.first().split('-').convertToIntPair(),
                second = assignments2.last().split('-').convertToIntPair()
            )
        }

}
