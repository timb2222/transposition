package com.example.validator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NoteValidatorTest {
    var noteValidator: NoteValidator = NoteValidator()

    @BeforeEach
    fun clean() {
        noteValidator = NoteValidator()
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "-3:10",
            "-2:12",
            "-1:12",
            "0:1",
            "4:12",
            "5:1",
        ], delimiter = ':'
    )
    fun validatingNote_shouldReturnNoErrors_ifNoteCorrect(octave: Int, note: Int) {
        val actual = noteValidator.validateNoteInRange(listOf(listOf(octave, note)))
        assertThat(actual.errors)
            .isEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "-5:1",
            "-3:9",
            "0:13",
            "0:0",
            "1:-1",
            "5:2",
            "6:1",
        ], delimiter = ':'
    )
    fun validatingNote_shouldReturnErrors_ifNoteIncorrect(octave: Int, note: Int) {
        val actual = noteValidator.validateNoteInRange(listOf(listOf(octave, note)))
        assertThat(actual.errors)
            .isNotEmpty()
    }

}