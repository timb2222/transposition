package com.example.parser

import com.example.exception.NoteOutOfRangeException
import com.example.validator.NoteValidator
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

class NoteParser(
    private val objectMapper: ObjectMapper = ObjectMapper(),
    private val noteValidator: NoteValidator = NoteValidator(),
) {
    fun parse(json: String): List<List<Int>> {
        val type: TypeReference<List<List<Int>>> = object: TypeReference<List<List<Int>>>(){}
        val result = objectMapper.createParser(json).readValueAs<List<List<Int>>>(type)
        val validationReport = noteValidator.validateNoteInRange(result)
        
        if (validationReport.errors.isNotEmpty()) 
            throw NoteOutOfRangeException("Validation for note is failed", validationReport)
        
        return result
    }
}