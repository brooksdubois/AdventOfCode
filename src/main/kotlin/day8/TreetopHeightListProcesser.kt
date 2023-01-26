package day8

typealias TreetopHeight = Int
typealias VisibilityScore = Int
data class TreetopCoordinates(val x:Int, val y: Int)
typealias Treetops = List<TreetopAnalysis>
data class TreetopAnalysis(
    var visible: Boolean?,
    val height: TreetopHeight,
    val coords: TreetopCoordinates,
    var visibleScore: VisibilityScore? = null
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
                    visible = if(isEdgePiece) true else null, visibleScore = 0
                )
            }
        }.flatten()

    fun processRemainingVisibilities(treetops: Treetops) {

        fun isVisibleFromList(list: List<Int>, position: Int, height: TreetopHeight): Boolean {
            val leftList = list.take(position)
            val rightList = list.drop(position + 1)
            val leftMax = leftList.maxOrNull() ?: -1
            val rightMax = rightList.maxOrNull() ?: -1
            val succeedsOnLeft = height > leftMax && !leftList.contains(height)
            val succeedsOnRight = height > rightMax && !rightList.contains(height)
            return succeedsOnLeft || succeedsOnRight
        }

        fun List<Int>.processTotalBeforeDip(): Int {
            var runningTotal = 1
            forEachIndexed{ idx, it ->
                if (idx != size - 1) {
                    val next = this[idx + 1]
                    if(next < it) return@processTotalBeforeDip runningTotal
                    runningTotal += 1
                    if(idx != 0){
                        val prev = this[idx - 1]
                        if(prev == it) runningTotal -= 1
                    }
                }
            }
            return runningTotal
        }

        fun findVisibilityScore(row: List<Int>, column:List<Int>, coords: TreetopCoordinates): Int {
            coords.apply{
                val leftRow = row.take(x).reversed()
                val rightRow = row.drop(x + 1)
                val topColumn = column.take(x).reversed()
                val bottomColumn = column.drop(x + 1)
                val leftView = leftRow.processTotalBeforeDip()
                val rightView = rightRow.processTotalBeforeDip()
                val topView = topColumn.processTotalBeforeDip()
                val bottomView = bottomColumn.processTotalBeforeDip()
                return leftView * rightView * topView * bottomView
            }
        }

        treetops.forEach { treetop ->
            treetop.apply {
                val column = treetops.filter{ it.coords.x == coords.x }.map{ it.height }
                val row = treetops.filter { it.coords.y == coords.y }.map{ it.height }
                if(visible == null){
                    val visibleInColumn = isVisibleFromList(list = column, position = coords.y, height = height)
                    val visibleInRow = isVisibleFromList(list = row, position = coords.x, height = height)
                    visible = visibleInRow || visibleInColumn
                }
                visibleScore = findVisibilityScore(row = row, column = column, coords = coords)
            }

        }
    }

    fun toBestVisibilty(treetops: Treetops) = treetops.maxBy { it.visibleScore!! }
    fun toCount(treetops: Treetops) = treetops.filter{ it.visible!! }.size
}
