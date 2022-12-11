package day1


fun main(args: Array<String>) {

    val startStr = realData

    val elfCollection = ElfCollection(rawStr = startStr)
    println("Elves: " + elfCollection.elves.map { it.elfList })

    val maxElf = elfCollection.findMax()
    println("the max elf is: $maxElf")

    val topThreeTotal = elfCollection.findTopThreeTotal()
    println("the topThree total is: $topThreeTotal")

}
