package day7

fun main(args: Array<String>) {
    val logParser = CommandLogParser(inputStr = realData)
    println("Processed Operations ${logParser.commandLogs}")
    val treeProcessor = FileTreeProcessor(commandEntries = logParser.commandLogs)
    val fileTree = treeProcessor.createDirectoryTreeFromLogs()
    println("File Directory Tree:")
    println(fileTree)
    val directoryMap = treeProcessor.findFileDirectorySizes(fileTree = fileTree!!)
    println("Directory Size Map: $directoryMap")
    val bestDeletionCandidate = treeProcessor.determineBestDeletionCandidate(
        directorySizeMap = directoryMap, fileTree = fileTree
    )
    println("The best file to delete is: $bestDeletionCandidate")
}
