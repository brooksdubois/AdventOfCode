package `day8(inc)`


const val sampleData = """
30373
25512
65332
33549
35390
"""

fun main(args: Array<String>) {
    val treetopParser = TreetopHeightListParser(treeRawData = sampleData)
    println(treetopParser.parseTreetops())
}

