package com.example.parser

import com.example.exception.NoteOutOfRangeException
import com.fasterxml.jackson.core.JsonParseException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NoteParserTest {
    var noteParser: NoteParser = NoteParser()

    @BeforeEach
    fun clean() {
        noteParser = NoteParser()
    }

    @Test
    fun parsingJson_shouldReturnCorrectStructure() {
        val result = noteParser.parse("[[1,1],[-2,11]]")
        val expected = listOf(listOf(1,1), listOf(-2,11))

        assertThat(result)
            .isEqualTo(expected)
    }

    @Test
    fun parsingJson_shouldReturnCorrectStructure_forEmptyJsonArray() {
        val result = noteParser.parse("[]")
        val expected = emptyList<List<Int>>()

        assertThat(result)
            .isEqualTo(expected)
    }

    @Test
    fun parsingJson_shouldThrowException_ifStructureIsInvalid() {
        assertThrows<JsonParseException> { noteParser.parse("[invalid]") }
    }

    @Test
    fun parsingJson_shouldThrowException_ifNotesHasMoreThanTwoIdentifiers() {
        val actual = assertThrows<NoteOutOfRangeException> { noteParser.parse("[[1,1,1],[-2,11]]") }
                .validationReport
            
        assertThat(actual.errors)
            .hasSize(1)
            .contains("Note [1, 1, 1] has to have 2 identifiers")
    }

    @Test
    fun parsingJson_shouldThrowException_ifNotesOutOfRange() {
        val actual = assertThrows<NoteOutOfRangeException> { noteParser.parse("[[111,111],[-2,11]]") }
            .validationReport

        assertThat(actual.errors)
            .hasSize(2)
            .contains("Note [111, 111] is out of range")
            .contains("Note [111, 111] has wrong semitone")
    }
}