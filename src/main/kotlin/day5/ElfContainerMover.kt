package day5

class ElfContainerMover(val elfStackArrangement: StackArrangement, val elfMovementOperations: List<MovementOperation>){

    private fun StackArrangement.makeMutableMap() = this
        .map{ it.key to it.value.toMutableList() }
        .toMap()
        .toMutableMap()

    fun processInventorySingleStack(): StackArrangement {
        val elfMovementState = elfStackArrangement.makeMutableMap()

        elfMovementOperations.forEach { crateMovement ->
            val crateCount = crateMovement.first
            val moveFromStack = crateMovement.second.first
            val moveToStack = crateMovement.second.second
            repeat(crateCount) {
                val topCrate = elfMovementState[moveFromStack]?.lastOrNull() ?: return@repeat
                elfMovementState[moveFromStack]?.removeLast()
                elfMovementState[moveToStack]?.add(topCrate)
            }
        }
        return elfMovementState.toMap()
    }

    fun processInventoryOrderedMultiStack(): StackArrangement {
        val elfMovementState = elfStackArrangement.makeMutableMap()

        elfMovementOperations.forEach { crateMovement ->
            val crateCount = crateMovement.first
            val moveFromStack = crateMovement.second.first
            val moveToStack = crateMovement.second.second
            val topCrates = elfMovementState[moveFromStack]!!.takeLast(crateCount)
            elfMovementState[moveFromStack] = elfMovementState[moveFromStack]!!.dropLast(crateCount).toMutableList()
            elfMovementState[moveToStack]!!.addAll(topCrates)
        }
        return elfMovementState.toMap()
    }

    fun findTopCrates(processedStackArrangement: StackArrangement) =
        processedStackArrangement.keys.map { processedStackArrangement[it]!!.last() }
            .joinToString("")

}
