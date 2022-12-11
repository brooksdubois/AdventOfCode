package day3

import Inventory
import InventoryItem
import InventoryMap
import InventoryMapGenerator

class InventoryDiffFinder(
    inventoryItemBuilder: InventoryItemBuilder,
    private val inventoryPairs: List<Pair<Inventory, Inventory>>,
    private val inventoryMap: InventoryMap = InventoryMapGenerator().inventoryMap
    ){

    val differences: List<List<InventoryItem>>

    init {
        differences = inventoryPairs.map {
            inventoryItemBuilder.compareInventoryDifferences(it.first, it.second)
        }
    }

    fun convertItemsToPriorities() = differences.map { diffList ->
        diffList.sumOf { inventoryMap[it] ?: 0 }
    }
}
