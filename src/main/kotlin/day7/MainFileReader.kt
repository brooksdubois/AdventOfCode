package day7

const val SAMPLE_PATH = "SampleData.txt"

//using file reading code generated with ChatGPT 😳
fun main(){
    val fileData = ReadWrite.readFromFile(fileName = SAMPLE_PATH)

    val logParser = CommandLogParser(inputStr = fileData)
    println("Processed Operations ${logParser.commandLogs}")
    val treeProcessor = FileTreeProcessor(commandEntries = logParser.commandLogs)
    val fileTree = treeProcessor.createDirectoryTreeFromLogs()
    println("File Directory Tree:")
    println(fileTree)
    val directoryMap = treeProcessor.findFileDirectorySizes(fileTree = fileTree!!)
    println("Directory Size Map: $directoryMap")
    val totalsUnder100k = treeProcessor.findSumSizesBelowThreshold(directorySizeMap = directoryMap)
    val resultStr = "Total sum of files under 100k: $totalsUnder100k"
    println(resultStr)

    ReadWrite.writeToFile(fileName = "result.txt", content = resultStr)
}
