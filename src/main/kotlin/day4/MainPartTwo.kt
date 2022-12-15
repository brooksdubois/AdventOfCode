package day4

fun main(args: Array<String>) {
//    val startStr = """2-4,6-8
//        2-3,4-5
//        5-7,7-9
//        2-8,3-7
//        6-6,4-6
//        2-6,4-8""".trimIndent()
    val startStr = realData
    val cleaningAssignments = CleaningAssignmentParser(elfAssignmentStr = startStr)
    println("Parsed Assignments: ${cleaningAssignments.elfAssignments}")
    val cleaningRangeValidator = CleaningRangeValidator(elfAssignments = cleaningAssignments.elfAssignments)
    val overlappingRanges = cleaningRangeValidator.findOverlapping()
    println("Overlapping Ranges: $overlappingRanges")
    println("Total Count Overlapping: ${overlappingRanges.size}")
}
