package day3

import InventoryMap
import InventoryMapGenerator
import InventoryPriority

class RucksackCommonFinder (
    private val rucksacks: List<Rucksack>,
    private val inventoryMap: InventoryMap = InventoryMapGenerator().inventoryMap
) {

    fun findRuckSackCommons() = rucksacks
        .fold(
            mutableListOf<List<InventoryPriority>>()
        ) { acc, rucksack ->

            val rucksack1 = rucksack.inventories[0].toList()
            val rucksack2 = rucksack.inventories[1].toSet()
            val rucksack3 = rucksack.inventories[2].toSet()

            val priorityOverlaps = rucksack1
                .intersect(rucksack2)
                .intersect(rucksack3)
                .toList()
                .map { inventoryMap[it] ?: 0 }

            acc.add(priorityOverlaps)
            return@fold acc
        }.toList()

}
