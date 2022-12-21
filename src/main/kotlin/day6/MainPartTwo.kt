package day6

fun main(args: Array<String>) {
    val streamDetector = StreamRepeatDetector(START_MSG_LENGTH = 14)
    val result = streamDetector.processStream(inputSequence = realData)
    println("The sequence starting index is $result")
}
