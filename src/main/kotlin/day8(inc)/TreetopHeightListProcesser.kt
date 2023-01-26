package `day8(inc)`

enum class FromSide { TOP, LEFT, RIGHT, BOTTOM }

typealias TreetopHeight = Int
data class TreetopCoordinates(val x:Int, val y: Int)
typealias Treetops = List<TreetopAnalysis>
data class TreetopAnalysis(
    var visible: Boolean?,
    val height: TreetopHeight,
    val coords: TreetopCoordinates
)

class TreetopHeightListParser(treeRawData: String ) {

    private val treeData: List<List<Int>>
    init {
        this.treeData = treeRawData.fixStartEndWhiteSpace()
            .map {treetopLine ->
                treetopLine.map { it.digitToInt() }
            }
    }

    fun parseTreetops(): List<TreetopAnalysis> = treeData.mapIndexed { indexY, treetopRow ->
            treetopRow.mapIndexed{ indexX, treetop ->
                val isEdgePiece = indexX == 0 || indexY == 0
                        || indexY == treeData.lastIndex
                        || indexY == treetopRow.lastIndex

                TreetopAnalysis(
                    height = treetop, coords = TreetopCoordinates(x = indexX, y = indexY),
                    visible = if(isEdgePiece) true else null
                )
            }
        }.flatten()

    fun processRemainingVisibilities(treetops: Treetops) {
        fun Treetops.withVisibilityUnset() = this.filter { it.visible == null}
        fun isVisibleFromList(list: List<Int>, position: Int, height: TreetopHeight): Boolean {
            val leftList = list.take(position)
            val rightList = list.drop(position + 1)
            val leftMax = leftList.maxOrNull() ?: -1
            val rightMax = rightList.maxOrNull() ?: -1
            val succeedsOnLeft = height > leftMax && !leftList.contains(height)
            val succeedsOnRight = height > rightMax && !rightList.contains(height)
            return succeedsOnLeft || succeedsOnRight
        }
        treetops.withVisibilityUnset().forEach { treetop ->
            treetop.apply {
                val column = treetops.filter{ it.coords.x == coords.x }.map{ it.height }
                val row = treetops.filter { it.coords.y == coords.y }.map{ it.height }
                val visibleInColumn = isVisibleFromList(list = column, position = coords.y, height = height)
                val visibleInRow = isVisibleFromList(list = row, position = coords.x, height = height )
                treetop.visible = visibleInRow || visibleInColumn
            }

        }
    }

    fun toCount(treetops: Treetops) = treetops.filter{ it.visible!! }.size
}
