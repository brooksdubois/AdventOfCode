package day7

class FileTreeProcessor(commandEntries: List<CommandLogEntry>? = null){

    private lateinit var commandLogs: List<CommandLogEntry>
    init {
        commandEntries?.also { commandLogs = it }
    }
    private fun String.takeLeftHalf() = this.takeWhile { !it.isWhitespace() }

    private fun String.takeRightHalf() = this.takeLastWhile { !it.isWhitespace() }

    private fun FileDirectoryNode.findTopMostParent(): FileDirectoryNode {
        var temp = this
        while(temp.parent !== null) temp = temp.parent!!
        return temp
    }

    fun createDirectoryTreeFromLogs(): FileDirectoryTree? {
        var traversingDirectoryNode: FileDirectoryNode? = null
        fun addDirectory(directoryName: FileName) {
            val newDirectoryNode = FileDirectoryNode(directoryName = directoryName)
            traversingDirectoryNode?.addChild(newDirectoryNode)
        }
        fun addFile(fileName: FileName, fileSize: FileSize){
            val newFile = File(fileName, fileSize)
            traversingDirectoryNode?.files?.add(newFile)
        }
        commandLogs.forEach { commandLogEntry ->
            if(commandLogEntry.command == OperatingCommand.CALL_DIR){
                traversingDirectoryNode = if(traversingDirectoryNode == null)
                        FileDirectoryTree()
                    else if(commandLogEntry.input == SystemSymbol.MOVE_UP_DIR.strValue)
                        traversingDirectoryNode!!.parent
                    else
                        traversingDirectoryNode!!
                            .children.first {
                                it.directoryName.contains(commandLogEntry.input!!)
                            }
            }else if(commandLogEntry.command == OperatingCommand.LIST_FILES){
                commandLogEntry.output?.forEach { line ->
                    if(line.startsWith(SystemSymbol.DIRECTORY.strValue))
                        addDirectory(line.takeRightHalf())
                    else
                        addFile(fileSize = line.takeLeftHalf().toLong(), fileName = line.takeRightHalf())
                }
            }
        }
        return traversingDirectoryNode?.findTopMostParent()
    }

    private fun FileDirectoryNode.findLowestFirstChild(): FileDirectoryNode {
        var temp = this
        while(temp.children.isNotEmpty()) temp = temp.children.first()
        return temp
    }

    fun findFileDirectorySizes(fileTree: FileDirectoryTree): Map<FileHash, FileSize> {
        val directorySizes = mutableMapOf<FileHash, FileSize>()
        var tempDir: FileDirectoryNode = fileTree
        var escape = false
        fun FileDirectoryNode.addFilesSizeToMap(sizeOfFiles: FileSize? = null): FileSize {
            var currentFileSize = directorySizes[fileHash] ?: 0
            currentFileSize += sizeOfFiles ?: findFileSize() //for some reason this must be a separate statement
            directorySizes[fileHash] = currentFileSize
            return currentFileSize
        }
        while(!escape) {
            tempDir = tempDir.findLowestFirstChild()
            val sizeOfFiles = tempDir.addFilesSizeToMap()
            if(tempDir.parent != null){
                tempDir = tempDir.parent!! //move into the parent, or we risk telling items to delete themselves
                tempDir.addFilesSizeToMap(sizeOfFiles = sizeOfFiles)
                tempDir.children.removeFirst()
            }
            if(tempDir.parent == null && tempDir.children.isEmpty()){
                tempDir.addFilesSizeToMap() //for the top level node
                escape = true
            }
        }
        return directorySizes
    }

    fun findSumSizesBelowThreshold(threshold: Int = 100000, directorySizeMap: Map<FileHash, FileSize>)
        = directorySizeMap.values.filter{ it <= threshold }.sum()

}
