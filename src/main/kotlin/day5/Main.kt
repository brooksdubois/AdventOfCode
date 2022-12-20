package day5

fun main(args: Array<String>) {
   /* val startStr = """
            [D]
        [N] [C]
        [Z] [M] [P]
         1   2   3

        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent()*/
    val startStr = realData

    val movementParser = StackMovementParser(inputStr = startStr)

    println("Stack Arrangement: ${movementParser.elfStackArrangement}")
    println("Ops: ${movementParser.elfMovementOperations}")

    val elfContainerMover = ElfContainerMover(
        movementOperations = movementParser.elfMovementOperations,
        stackArrangement = movementParser.elfStackArrangement
    )

    val movementResult = elfContainerMover.processInventorySingleStack()
    val topCrates = elfContainerMover.findTopCrates(processedStackArrangement = movementResult)
    println("Movement Result: $movementResult")
    println("Top Crates: $topCrates")
}
