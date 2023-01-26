package day8

fun main(args: Array<String>) {
    val fileData = ReadWrite.readFromFile(fileName = SAMPLE_PATH)
    val treetopParser = TreetopHeightListParser(treeRawData = sampleData)// fileData)
    val treetops = treetopParser.parseTreetops()
    println("Initial $treetops")
    treetopParser.processRemainingVisibilities(treetops = treetops)
    println("Processed values $treetops")
    val bestVisibilityScore = treetopParser.toBestVisibilty(treetops = treetops)
    println("Count visible $bestVisibilityScore")
}
