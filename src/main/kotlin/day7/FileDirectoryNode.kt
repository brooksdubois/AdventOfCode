package day7

import java.util.Random

typealias FileSize = Long
typealias FileName = String
typealias FileHash = Int
typealias File = Pair<FileName, FileSize>
typealias FileDirectoryTree = FileDirectoryNode

class FileDirectoryNode(val directoryName: FileName = SystemSymbol.BASE_DIR.strValue) {
    var files = mutableListOf<File>()
    val children= mutableListOf<FileDirectoryNode>()
    var parent: FileDirectoryNode? = null
    val fileHash: FileHash = (-Int.MAX_VALUE..Int.MAX_VALUE).random()

    fun addChild(node:FileDirectoryNode){
        children.add(node)
        node.parent = this
    }

    fun findFileSize(): FileSize = this.files.sumOf { it.second }

    override fun toString(): String {
        return """
           |($directoryName)${"#".padEnd(14, '#')}````````|
           |Files: $files
           |Files size: ${findFileSize()}
           |Children: ${ children.map{ it.toString() } }
           |#######(end ${directoryName})${"#".padEnd(15, '#')}|
            """
    }
}
