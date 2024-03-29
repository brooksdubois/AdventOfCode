package day8


const val sampleData = """
30373
25512
65332
33549
35390
"""

fun main(args: Array<String>) {
    val treetopParser = TreetopHeightListParser(treeRawData = sampleData)
    val treetops = treetopParser.parseTreetops()
    println("Initial $treetops")
    treetopParser.processRemainingVisibilities(treetops = treetops)
    println("Processed values $treetops")
    val numVisible = treetopParser.toCount(treetops = treetops)
    println("Count visible $numVisible")
}

