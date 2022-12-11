package day3


fun main(args: Array<String>) {
/*    val startStr = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent()*/
    val startStr = realData

    val inventoryItemBuilder = InventoryItemBuilder(inventoryStr = startStr)
    val itemLines = inventoryItemBuilder.parseItemsToInventoryPairs()

    val diffFinderResults = InventoryDiffFinder(inventoryItemBuilder = inventoryItemBuilder, inventoryPairs = itemLines)
    println("DifferenceMap: ${diffFinderResults.differences}")

    val diffPriorities = diffFinderResults.convertItemsToPriorities()

    println("Inventory Comparisons as Priority Sums: $diffPriorities")
    println("Total Priority Sums: ${diffPriorities.sum()}")

}
