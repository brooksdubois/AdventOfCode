package day6

typealias RepeatableStream = String
typealias FirstMarkerIdx = Int

const val START_MSG_LENGTH = 4
const val NOT_FOUND_INDEX = -1

class StreamRepeatDetector() {
    private fun String.isAllUniqueChars() = this.toCharArray().toSet().size == this.length

    fun processStream(inputSequence: RepeatableStream): FirstMarkerIdx {
        var startMessageCandidate = inputSequence.take(n = START_MSG_LENGTH)
        repeat(inputSequence.length - START_MSG_LENGTH){ sequenceCounter ->
            if(startMessageCandidate.isAllUniqueChars())
                return sequenceCounter + START_MSG_LENGTH
            else
                startMessageCandidate = inputSequence
                    .drop(n = sequenceCounter + 1)
                    .take(n = START_MSG_LENGTH)
        }
        return NOT_FOUND_INDEX
    }

}
