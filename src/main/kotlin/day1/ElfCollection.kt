package day1
typealias inputElvesStr = String
class ElfCollection(rawStr: inputElvesStr) {
    val elves: List<ElfAdder>

    init {
        this.elves = rawStr.convertToList()
    }

    private fun String.convertToList(): List<ElfAdder> {
        var tempElf = ElfAdder()
        val tempElfList = mutableListOf<ElfAdder>()
        this.lines().toList().forEach{ it ->
            if(it.isEmpty()){
                tempElfList.add(tempElf)
                tempElf = ElfAdder()
            }else{
                tempElf.elfList.add(it.toInt())
            }
        }
        return tempElfList
    }

    fun findMax(): Int = this.elves.maxOf { it.findTotal() }

    private fun findTopThree() = this.elves
        .sortedBy { it.findTotal() }
        .takeLast(3)

    fun findTopThreeTotal() = this.findTopThree()
        .fold(0){ acc, it -> acc + it.findTotal() }
}
