package day1


//Part 1 and 2 challenges are in this
// single main function for this one day
fun main(args: Array<String>) {

    val startStr = realData

    val elfCollection = ElfCollection(rawStr = startStr)
    println("Elves: " + elfCollection.elves.map { it.elfList })

    val maxElf = elfCollection.findMax()
    println("the max elf is: $maxElf")

    //part two
    val topThreeTotal = elfCollection.findTopThreeTotal()
    println("the topThree total is: $topThreeTotal")

}
