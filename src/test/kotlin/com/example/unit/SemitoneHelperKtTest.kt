package com.example.unit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class SemitoneHelperKtTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "-3:9:-28",
            "-3:10:-27",
            "-3:11:-26",
            "0:1:1",
            "4:11:59",
            "4:12:60",
            "5:1:61",
            "5:2:62",
        ], delimiter = ':'
    )
    fun getIndex_shouldReturnCorrectIndex(octave: Int, note: Int, expected: Int) {
        val actual = getIndex(octave, note)
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "-3:9:-28",
            "-3:10:-27",
            "-3:11:-26",
            "-3:12:-25",
            "-2:1:-24",
            "0:1:1",
            "4:11:59",
            "4:12:60",
            "5:1:61",
            "5:2:62",
        ], delimiter = ':'
    )
    fun getNote_shouldReturnCorrectNote(expectedOctave: Int, expectedNote: Int, index: Int) {
        val actualOctave = getNote(index)[0]
        val actualNote = getNote(index)[1]
        assertThat(actualOctave).isEqualTo(expectedOctave)
        assertThat(actualNote).isEqualTo(expectedNote)
    }
}