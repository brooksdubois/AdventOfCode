package day6

fun main(args: Array<String>) {
    val streamDetector = StreamRepeatDetector()
    val result = streamDetector.processStream(inputSequence = realData)
    println("The sequence starting index is $result")
}
