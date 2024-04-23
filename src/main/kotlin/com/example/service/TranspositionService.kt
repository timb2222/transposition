package com.example.service

import com.example.exception.TranspositionNotAvailableException
import com.example.parser.NoteParser
import com.example.unit.getIndex
import com.example.unit.getNote
import com.example.validator.MAX_NOTE
import com.example.validator.MIN_NOTE

class TranspositionService(
    val noteParser: NoteParser = NoteParser()
) {
    fun transposeAndParse(json: String, transposeTo: Int): List<List<Int>> {
        val notes = noteParser.parse(json)

        val transpositionAvailable = notes.any {
            (getIndex(it[0], it[1]) + transposeTo) in MIN_NOTE..MAX_NOTE
        }
        if (!transpositionAvailable)
            throw TranspositionNotAvailableException("Transposition not available")

        return notes.map { getIndex(it[0], it[1]) }
            .map { it + transposeTo }
            .map { getNote(it) }
            .toList()
    }
    
    fun transpose(notes: List<List<Int>>, transposeTo: Int): List<List<Int>> {
        val transpositionAvailable = notes.any {
            (getIndex(it[0], it[1]) + transposeTo) in MIN_NOTE..MAX_NOTE
        }
        if (!transpositionAvailable)
            throw TranspositionNotAvailableException("Transposition not available")

        return notes.map { getIndex(it[0], it[1]) }
            .map { it + transposeTo }
            .map { getNote(it) }
            .toList()
    }
}