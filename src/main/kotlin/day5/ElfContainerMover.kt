package day5

class ElfContainerMover(val elfStackArrangement: StackArrangement, val elfMovementOperations: List<MovementOperation>){

    fun processInventoryMovements(): StackArrangement {
        val elfMovementState = elfStackArrangement
            .map{ it.key to it.value.toMutableList() }
            .toMap()
            .toMutableMap()

        elfMovementOperations.forEach { crateMovement ->
            println("-------")
            println("MovementOP = $crateMovement")
            println("StackArrangementBefore = $elfMovementState")
            val crateCount = crateMovement.first
            val moveFromStack = crateMovement.second.first
            val moveToStack = crateMovement.second.second
            repeat(crateCount) {
                //println("OP #$it \n State: $elfMovementState")
                val topCrate = elfMovementState[moveFromStack]?.lastOrNull()
                if(topCrate == null) println("ERROR ON $it") else {
                    elfMovementState[moveFromStack]?.removeLast()
                    elfMovementState[moveToStack]?.add(topCrate)
                }
            }
            println("StackArrangementAfter = $elfMovementState")
        }
        println("-------")
        return elfMovementState.toMap()
    }

    fun findTopCrates(processedStackArrangement: StackArrangement) =
        processedStackArrangement.keys.map { processedStackArrangement[it]!!.last() }
            .joinToString("")

}
