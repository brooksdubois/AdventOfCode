package day3

fun main(args: Array<String>) {
//    val startStr = """
//        vJrwpWtwJgWrhcsFMMfFFhFp
//        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
//        PmmdzqPrVvPwwTWBwg
//        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
//        ttgJtRGJQctTZtZT
//        CrZsJsPPZsGzwwsLwLmpwMDw
//    """.trimIndent()
    val startStr = realData

    val inventoryItemBuilder = InventoryItemBuilder(inventoryStr = startStr)
    val rucksacks = inventoryItemBuilder.parseItemsToGroupRucksacks()

    val rucksackCollections = RucksackCommonFinder(rucksacks = rucksacks)
    val rucksackCommons = rucksackCollections.findRuckSackCommons()

    //there should only be one item in every list, this is not enforced
    val prioritySums = rucksackCommons.map{ it.sum() }

    println("Inventory Comparisons as Priority Sums: $prioritySums")
    println("Total Sums: ${prioritySums.sum()}")

}
