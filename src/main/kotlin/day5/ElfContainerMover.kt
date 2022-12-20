package day5

class ElfContainerMover(val stackArrangement: StackArrangement, val movementOperations: List<MovementOperation>){

    private fun StackArrangement.makeMutableMap() = this
        .map{ it.key to it.value.toMutableList() }
        .toMap()
        .toMutableMap()

    fun processInventorySingleStack(): StackArrangement {
        val elfMovementState = stackArrangement.makeMutableMap()

        movementOperations.forEach { crateMovement ->
            repeat(crateMovement.count) {
                val topCrate = elfMovementState[crateMovement.fromIndex]?.lastOrNull()
                    ?: return@repeat //shouldn't be reached
                elfMovementState[crateMovement.fromIndex]?.removeLast()
                elfMovementState[crateMovement.toIndex]?.add(topCrate)
            }
        }
        return elfMovementState.toMap()
    }

    fun processInventoryOrderedMultiStack(): StackArrangement {
        val elfMovementState = stackArrangement.makeMutableMap()

        movementOperations.forEach { crateMovement ->
            val topCrates = elfMovementState[crateMovement.fromIndex]
                ?.takeLast(crateMovement.count) ?: return@forEach
            val newFromCrates = elfMovementState[crateMovement.fromIndex]
                ?.dropLast(crateMovement.count)?.toMutableList()
            elfMovementState[crateMovement.fromIndex] = newFromCrates ?: mutableListOf()
            elfMovementState[crateMovement.toIndex]?.addAll(topCrates)
        }
        return elfMovementState.toMap()
    }

    fun findTopCrates(processedStackArrangement: StackArrangement) =
        processedStackArrangement.keys
            .map { processedStackArrangement[it]!!.last() }
            .joinToString("")

}
