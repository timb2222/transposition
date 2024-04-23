package com.example.service

import com.example.exception.TranspositionNotAvailableException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TranspositionServiceTest{
    var transpositionService: TranspositionService = TranspositionService()
    var objectMapper: ObjectMapper = ObjectMapper()

    @BeforeEach
    fun clean() {
        transpositionService = TranspositionService()
    }

    @Test
    fun transposition_shouldTransposeCorrectly() {
        val type: TypeReference<List<List<Int>>> = object: TypeReference<List<List<Int>>>(){}
        val raw = this.javaClass.getResource("/input.json")?.readText()
        val input = objectMapper.createParser(raw).readValueAs<List<List<Int>>>(type)
        val actual = transpositionService.transpose(input, -3)

        val expectedRaw = this.javaClass.getResource("/expected.json")?.readText()
        val expected = objectMapper.createParser(expectedRaw).readValueAs<List<List<Int>>>(type)
        
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun transposition_shouldThrowException_ifOutOfRange() {
        val type: TypeReference<List<List<Int>>> = object: TypeReference<List<List<Int>>>(){}
        val raw = this.javaClass.getResource("/input.json")?.readText()
        val input = objectMapper.createParser(raw).readValueAs<List<List<Int>>>(type)
        assertThrows<TranspositionNotAvailableException> { transpositionService.transpose(input, 9999) }
    }

    @Test
    fun transpositionWithParsing_shouldTransposeCorrectly() {
        val type: TypeReference<List<List<Int>>> = object: TypeReference<List<List<Int>>>(){}
        val raw = this.javaClass.getResource("/input.json")?.readText() ?: throw NullPointerException()
        val actual = transpositionService.transposeAndParse(raw, -3)

        val expectedRaw = this.javaClass.getResource("/expected.json")?.readText()
        val expected = objectMapper.createParser(expectedRaw).readValueAs<List<List<Int>>>(type)
        
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun transpositionWithParsing_shouldThrowException_ifOutOfRange() {
        val raw = this.javaClass.getResource("/input.json")?.readText() ?: throw NullPointerException()
        assertThrows<TranspositionNotAvailableException> { transpositionService.transposeAndParse(raw, 9999) }
    }
}