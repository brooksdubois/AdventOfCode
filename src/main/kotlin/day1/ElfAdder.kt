package day1

typealias ElfPrice = Int

class ElfAdder{
    val elfList: MutableList<ElfPrice> = mutableListOf()

    fun findTotal() = elfList.toList().reduce{ acc, it -> acc + it } //or just .sum()
}
