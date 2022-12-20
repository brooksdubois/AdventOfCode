package day5

fun main(args: Array<String>) {
    val movementParser = StackMovementParser(inputStr = realData)
    val elfContainerMover = ElfContainerMover(
        movementOperations = movementParser.elfMovementOperations,
        stackArrangement = movementParser.elfStackArrangement
    )
    val movementResult = elfContainerMover.processInventoryOrderedMultiStack()
    val topCrates = elfContainerMover.findTopCrates(processedStackArrangement = movementResult)
    println("Movement Result: $movementResult")
    println("Top Crates: $topCrates")
}
