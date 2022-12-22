
import day7.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

const val sampleData = """
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
"""

class Day7Test {

    private lateinit var commandLogParser: CommandLogParser
    private var inputLines = sampleData.fixStartEndWhiteSpace()

    @BeforeEach
    fun beforeEach(){
        commandLogParser = CommandLogParser()
    }

    @Test
    fun `it parses parses a cd command with no output`() {
        val inputLines = listOf("$ cd /")
        val result = commandLogParser.parseCommandString(inputLines = inputLines)
        val expected = "[CommandLogEntry(command=CALL_DIR, input=/, output=[])]"
        assertEquals(expected = expected, actual = result.toString())
    }

    @Test
    fun `it parses an ls command with a file list output`() {
        val inputLines = "$ ls\ndir a\n14848514 b.txt\n8504156 c.dat\ndir d".fixStartEndWhiteSpace()
        val result = commandLogParser.parseCommandString(inputLines = inputLines)
        val expected = "[CommandLogEntry(command=LIST_FILES, input=null, output=[dir a, 14848514 b.txt, 8504156 c.dat, dir d])]"
        assertEquals(expected = expected, actual = result.toString())
    }

    @Test
    fun `it parses string directly into base level models`() {
        val result = commandLogParser.parseCommandString(inputLines = inputLines)
        val expected = "[CommandLogEntry(command=CALL_DIR, input=/, output=[]), CommandLogEntry(command=LIST_FILES, input=null, output=[dir a, 14848514 b.txt, 8504156 c.dat, dir d]), CommandLogEntry(command=CALL_DIR, input=a, output=[]), CommandLogEntry(command=LIST_FILES, input=null, output=[dir e, 29116 f, 2557 g, 62596 h.lst]), CommandLogEntry(command=CALL_DIR, input=e, output=[]), CommandLogEntry(command=LIST_FILES, input=null, output=[584 i]), CommandLogEntry(command=CALL_DIR, input=.., output=[]), CommandLogEntry(command=CALL_DIR, input=.., output=[]), CommandLogEntry(command=CALL_DIR, input=d, output=[]), CommandLogEntry(command=LIST_FILES, input=null, output=[4060174 j, 8033020 d.log, 5626152 d.ext, 7214296 k])]"
        assertEquals(expected = expected, actual = result.toString())
    }

    @Test
    fun `it builds the base of a tree from a simple cd and ls`(){
        val commandOperationsLogs = listOf(
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = SystemSymbol.BASE_DIR.strValue, output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("dir a", "14848514 b.txt", "8504156 c.dat", "dir d")),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = "a", output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("29116 f", "2557 g")),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = SystemSymbol.MOVE_UP_DIR.strValue, output = null),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = "d", output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k")),
        )

        val result = FileTreeProcessor(commandEntries = commandOperationsLogs).createDirectoryTreeFromLogs()
        val expectedDirectoryTree = FileDirectoryTree()
        expectedDirectoryTree.files = mutableListOf(File("b.txt", 14848514), File("c.dat", 8504156))
        val expectedADirectory = FileDirectoryNode(directoryName = "a")
        expectedADirectory.files = mutableListOf(File("f", 29116), File("g", 2557))
        val expectedDDirectory = FileDirectoryNode(directoryName = "d")
        expectedDDirectory.files = mutableListOf(File("j", 4060174), File("d.log", 8033020), File("d.ext", 5626152), File("k", 7214296))

        expectedDirectoryTree.addChild(expectedADirectory)
        expectedDirectoryTree.addChild(expectedDDirectory)

        assertEquals(expected = expectedDirectoryTree.toString(), actual = result.toString())
    }
    @Test
    fun `it builds the base of a tree from a series of cd and ls that goes 2 levels deep`(){
        val commandOperationsLogs = listOf(
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = SystemSymbol.BASE_DIR.strValue, output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("dir a", "14848514 b.txt", "8504156 c.dat", "dir d")),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = "a", output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("dir c", "29116 f", "2557 g")),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = "c", output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("1234 Brooks", "344 Brd.odo")),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = SystemSymbol.MOVE_UP_DIR.strValue, output = null),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = SystemSymbol.MOVE_UP_DIR.strValue, output = null),
            CommandLogEntry(command = OperatingCommand.CALL_DIR, input = "d", output = null),
            CommandLogEntry(command = OperatingCommand.LIST_FILES, input = null, output = mutableListOf("4060174 j", "8033020 d.log", "5626152 d.ext", "7214296 k")),
        )

        val result = FileTreeProcessor(commandEntries = commandOperationsLogs).createDirectoryTreeFromLogs()

        val expectedDirectoryTree = FileDirectoryTree()
        expectedDirectoryTree.files = mutableListOf(File("b.txt", 14848514), File("c.dat", 8504156))
        val expectedADirectory = FileDirectoryNode(directoryName = "a")
        expectedADirectory.files = mutableListOf(File("f", 29116), File("g", 2557))
        val expectedCDirectory = FileDirectoryNode(directoryName = "c")
        expectedCDirectory.files = mutableListOf(File("Brooks", 1234), File("Brd.odo", 344))
        expectedADirectory.addChild(expectedCDirectory)
        val expectedDDirectory = FileDirectoryNode(directoryName = "d")
        expectedDDirectory.files = mutableListOf(File("j", 4060174), File("d.log", 8033020), File("d.ext", 5626152), File("k", 7214296))
        expectedDirectoryTree.addChild(expectedADirectory)
        expectedDirectoryTree.addChild(expectedDDirectory)

        assertEquals(expected = expectedDirectoryTree.toString(), actual = result.toString())
    }

    @Test
    fun `it calculates a proper directory sizes from the file tree that is 1 level deep`(){
        val directorySize1: FileSize = 14848514
        val directorySize2: FileSize = 8504156
        val directoryASize1: FileSize = 29116
        val directoryASize2: FileSize = 2557
        val directoryDSize1: FileSize = 4060174
        val directoryDSize2: FileSize = 8033020
        val directoryDSize3: FileSize = 5626152
        val directoryDSize4: FileSize = 7214296
        val directoryTree = FileDirectoryTree()
        directoryTree.files = mutableListOf(File("b.txt", directorySize1), File("c.dat", directorySize2))
        val directoryA = FileDirectoryNode(directoryName = "a")
        directoryA.files = mutableListOf(File("f", directoryASize1), File("g", directoryASize2))
        val directoryD = FileDirectoryNode(directoryName = "d")
        directoryD.files = mutableListOf(File("j", directoryDSize1), File("d.log", directoryDSize2), File("d.ext", directoryDSize3), File("k", directoryDSize4))
        directoryTree.addChild(directoryA)
        directoryTree.addChild(directoryD)

        println("Inserting file tree $directoryTree")

        val result = FileTreeProcessor().findFileDirectorySizes(fileTree = directoryTree)

        val rootFileSize = directorySize1 + directorySize2
        val aFileSize = directoryASize1 + directoryASize2
        val dFileSize = directoryDSize1 + directoryDSize2 + directoryDSize3 + directoryDSize4
        val expectedMap = mapOf(
            Pair("/", rootFileSize + aFileSize + dFileSize),
            Pair("a", aFileSize),
            Pair("d", dFileSize )
        )

        assertEquals(expected = expectedMap, actual = result)
    }
    @Test
    fun `it calculates a proper directory sizes from the file tree that is 2 levels deep`(){
        val directorySize1: FileSize = 14848514
        val directorySize2: FileSize = 8504156
        val directoryASize1: FileSize = 29116
        val directoryASize2: FileSize = 2557
        val directoryCSize1: FileSize = 1223
        val directoryCSize2: FileSize = 234
        val directoryDSize1: FileSize = 4060174
        val directoryDSize2: FileSize = 8033020
        val directoryDSize3: FileSize = 5626152
        val directoryDSize4: FileSize = 7214296
        val directoryTree = FileDirectoryTree()
        directoryTree.files = mutableListOf(File("b.txt", directorySize1), File("c.dat", directorySize2))
        val directoryA = FileDirectoryNode(directoryName = "a")
        directoryA.files = mutableListOf(File("f", directoryASize1), File("g", directoryASize2))
        val directoryC = FileDirectoryNode(directoryName = "c")
        directoryC.files = mutableListOf(File("lol.ol", directoryCSize1), File("ok.bird", directoryCSize2))
        directoryA.addChild(directoryC)
        val directoryD = FileDirectoryNode(directoryName = "d")
        directoryD.files = mutableListOf(File("j", directoryDSize1), File("d.log", directoryDSize2), File("d.ext", directoryDSize3), File("k", directoryDSize4))
        directoryTree.addChild(directoryA)
        directoryTree.addChild(directoryD)

        val result = FileTreeProcessor().findFileDirectorySizes(fileTree = directoryTree)

        val rootFileSize = directorySize1 + directorySize2
        val aFileSize = directoryASize1 + directoryASize2
        val dFileSize = directoryDSize1 + directoryDSize2 + directoryDSize3 + directoryDSize4
        val cFileSize = directoryCSize1 + directoryCSize2
        val expectedMap = mapOf(
            Pair("/", rootFileSize + aFileSize + dFileSize + cFileSize),
            Pair("a", aFileSize + cFileSize),
            Pair("c", cFileSize),
            Pair("d", dFileSize)
        )

        assertEquals(expected = expectedMap, actual = result)
    }
}
