import day6.StreamRepeatDetector
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day6Test {

    private lateinit var repeatDetector: StreamRepeatDetector

    @BeforeEach
    fun beforeEach(){
        repeatDetector = StreamRepeatDetector()
    }

    @Test
    fun `it detects no repeats for a short stream of 4 chars`() {
        val initialStr = "abcd"
        val result = repeatDetector.processStream(inputSequence = initialStr)
        val expectedFirstMarkerIndex = -1
        assertEquals(expected = expectedFirstMarkerIndex, actual = result)
    }

    @Test
    fun `it detects the first marker after character 7 for sequence mjqjpqmgbljsphdztnvjfqwrcgsmlb`(){
        val initialStr = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
        val result = repeatDetector.processStream(inputSequence = initialStr)
        val expectedFirstMarkerIndex = 7
        assertEquals(expected = expectedFirstMarkerIndex, actual = result)
    }

    @Test
    fun `it detects the first marker after character 5 for sequence bvwbjplbgvbhsrlpgdmjqwftvncz`(){
        val initialStr = "bvwbjplbgvbhsrlpgdmjqwftvncz"
        val result = repeatDetector.processStream(inputSequence = initialStr)
        val expectedFirstMarkerIndex = 5
        assertTrue { expectedFirstMarkerIndex == result }
    }

    @Test
    fun `it detects the first marker after character 6 for sequence nppdvjthqldpwncqszvftbrmjlhg`(){
        val initialStr = "nppdvjthqldpwncqszvftbrmjlhg"
        val result = repeatDetector.processStream(inputSequence = initialStr)
        val expectedFirstMarkerIndex = 6
        assertTrue { expectedFirstMarkerIndex == result }
    }

    @Test
    fun `it detects the first marker after character 10 for sequence nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg`(){
        val initialStr = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        val result = repeatDetector.processStream(inputSequence = initialStr)
        val expectedFirstMarkerIndex = 10
        assertTrue { expectedFirstMarkerIndex == result }
    }

    @Test
    fun `it detects the first marker after character 11 for sequence zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw`(){
        val initialStr = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        val result = repeatDetector.processStream(inputSequence = initialStr)
        val expectedFirstMarkerIndex = 11
        assertTrue { expectedFirstMarkerIndex == result }
    }

}

/*
it detects the first marker after character 7 for sequence mjqjpqmgbljsphdztnvjfqwrcgsmlb
it detects the first marker after character 5 for sequence bvwbjplbgvbhsrlpgdmjqwftvncz
it detects the first marker after character 6 for sequence nppdvjthqldpwncqszvftbrmjlhg
it detects the first marker after character 10 for sequence nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
it detects the first marker after character 11 for sequence zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
 */
