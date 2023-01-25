package `day8(inc)`

enum class FromSide { TOP, LEFT, RIGHT, BOTTOM }

typealias TreetopHeight = Int
data class TreetopAnalysis(val fromSide: FromSide, val treetopHeight: TreetopHeight, val indexes: List<Int>)

class TreetopHeightListParser(treeRawData: String ) {

    private val treeData: List<List<Int>>
    init {
        this.treeData = treeRawData.fixStartEndWhiteSpace()
            .map {
                it.toCharArray().map { char -> char.toInt() }
            }
    }

    fun parseTreetops(){
        val fromLeftMaxes = treeData.map{ it.max() }
        println(fromLeftMaxes)
        val fromLeftIndexes = treeData.map{
            it.foldIndexed(mutableListOf<Pair<Int, Int>>()) { index, acc, treetop ->
                if(treetop == fromLeftMaxes[index])
                    acc.add(Pair(treetop, index))
                acc
            }.toList()
        }
        println(fromLeftIndexes)
    }
}
