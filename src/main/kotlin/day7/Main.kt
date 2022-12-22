package day7

/*
val startStr = """
    $ cd /
    $ ls
    dir a
    14848514 b.txt
    8504156 c.dat
    dir d
    $ cd a
    $ ls
    dir e
    29116 f
    2557 g
    62596 h.lst
    $ cd e
    $ ls
    584 i
    $ cd ..
    $ cd ..
    $ cd d
    $ ls
    4060174 j
    8033020 d.log
    5626152 d.ext
    7214296 k
""".trimIndent()
*/

fun main(args: Array<String>) {
    val logParser = CommandLogParser(inputStr = realData)
    println("Processed Operations ${logParser.commandLogs}")
    val treeProcessor = FileTreeProcessor(commandEntries = logParser.commandLogs)
    val fileTree = treeProcessor.createDirectoryTreeFromLogs()
    println("File Directory Tree:")
    println(fileTree)
    val directoryMap = treeProcessor.findFileDirectorySizes(fileTree = fileTree!!)
    println("Directory Size Map: $directoryMap")
    val totalsUnder100k = treeProcessor.findSumSizesBelowThreshold(directorySizeMap = directoryMap)
    println("Total sum of files under 100k: $totalsUnder100k")
}
