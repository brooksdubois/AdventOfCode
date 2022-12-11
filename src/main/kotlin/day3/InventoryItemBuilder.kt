package day3

import InventoryItem


class Rucksack(val inventories: List<String>)

fun String.parseLine() = this
    .filterNot { it.isWhitespace() }

class InventoryItemBuilder(inventoryStr: String) {

    private var inventoryRawLines: List<String>

    init {
        this.inventoryRawLines = inventoryStr.lines().map{ it.parseLine() }
    }

    fun parseItemsToInventoryPairs() = this.inventoryRawLines
        .map { chars ->
            val charArray = chars.toCharArray()
            val numChars = charArray.size
            if (numChars % 2 != 0) {
                println("Invalid line size")
                return@map Pair(emptyList(), emptyList())
            }
            val pivot = numChars / 2
            val firstHalf = charArray.take(pivot)
            val secondHalf = charArray.takeLast(pivot)
            return@map Pair(firstHalf, secondHalf)
        }


    fun parseItemsToGroupRucksacks() = this.inventoryRawLines
        .chunked(3)
        .map{ Rucksack(inventories = it) }

    fun compareInventoryDifferences(
        firstList: List<InventoryItem>, secondList: List<InventoryItem>
    ) = firstList.intersect(secondList.toSet()).toList()


}
