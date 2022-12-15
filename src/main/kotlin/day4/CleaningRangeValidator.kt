package day4

class CleaningRangeValidator(val elfAssignments: List<ElfSchedule>) {

    private fun isRangeOverlapping(elf1Routine: CleaningRoutine, elf2Routine: CleaningRoutine)
        = if(elf1Routine.first >= elf2Routine.first && elf1Routine.second <= elf2Routine.second) {
            true
        } else {
            elf1Routine.first <= elf2Routine.first && elf1Routine.second >= elf2Routine.second
        }

    fun findOverlapping() = elfAssignments.filter{
        isRangeOverlapping(elf1Routine = it.first, elf2Routine = it.second)
    }
}
