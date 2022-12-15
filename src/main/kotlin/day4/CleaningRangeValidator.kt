package day4

class CleaningRangeValidator(val elfAssignments: List<ElfSchedule>) {

    private fun isRangeEncompassed(elf1Routine: CleaningRoutine, elf2Routine: CleaningRoutine)
        = if(elf1Routine.first >= elf2Routine.first && elf1Routine.second <= elf2Routine.second) {
            true
        } else {
            elf1Routine.first <= elf2Routine.first && elf1Routine.second >= elf2Routine.second
        }

    private fun isRangeOverlapped(elf1Routine: CleaningRoutine, elf2Routine: CleaningRoutine)
        = (elf1Routine.first..elf1Routine.second)
            .intersect(elf2Routine.first.. elf2Routine.second)
            .isNotEmpty()

    fun findEncompassing() = elfAssignments.filter{
        isRangeEncompassed(elf1Routine = it.first, elf2Routine = it.second)
    }

    fun findOverlapping() = elfAssignments.filter{
        isRangeOverlapped(elf1Routine = it.first, elf2Routine = it.second)
    }
}
