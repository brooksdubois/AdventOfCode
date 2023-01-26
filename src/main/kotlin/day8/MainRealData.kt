package day8

const val SAMPLE_PATH = "src/main/kotlin/day8/SampleData.txt"

fun main(){
    val fileData = ReadWrite.readFromFile(fileName = SAMPLE_PATH)
    val treetopParser = TreetopHeightListParser(treeRawData = fileData)
    val treetops = treetopParser.parseTreetops()
    println("Initial $treetops")
    treetopParser.processRemainingVisibilities(treetops = treetops)
    println("Processed values $treetops")
    val numVisible = treetopParser.toCount(treetops = treetops)
    println("Count visible $numVisible")
}
