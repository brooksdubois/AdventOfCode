
typealias InventoryPriority = Int
typealias InventoryItem = Char
typealias InventoryMap = Map<InventoryItem, InventoryPriority>
typealias Inventory = List<InventoryItem>


class InventoryMapGenerator() {
    val inventoryMap: InventoryMap

    init {
        val allChars = ('a'..'z').toList().union(('A'..'Z').toList())
        this.inventoryMap = allChars.mapIndexed { index,  char -> char to index + 1 }.toMap()
    }
}
