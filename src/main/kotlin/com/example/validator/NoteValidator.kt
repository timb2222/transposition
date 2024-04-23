package com.example.validator

import com.example.unit.getIndex

const val MAX_NOTE: Int = 61
const val MIN_NOTE: Int = -27
const val MAX_SEMITONES: Int = 12
const val MIN_SEMITONES: Int = 1

class NoteValidator {

    fun validateNoteInRange(notes: List<List<Int>>): ValidationReport {
        val result = mutableListOf<String>()
        notes.filter { it.size != 2 }
            .forEach { result.add("Note $it has to have 2 identifiers") }

        notes.map {it to it[1] }
            .filterNot { it.second in MIN_SEMITONES..MAX_SEMITONES }
            .forEach { result.add("Note ${it.first} has wrong semitone") }
        
        notes.filter { it[0] >= 0 }
            .map { it to getIndex(it[0], it[1])}
            .filter { it.second > MAX_NOTE }
            .forEach { result.add("Note ${it.first} is out of range") }
        
        notes.filter { it[0] < 0 }
            .map { it to getIndex(it[0], it[1])}
            .filter { it.second < MIN_NOTE }
            .forEach { result.add("Note ${it.first} is out of range") }
        
        return ValidationReport(result)
    }
}

data class ValidationReport(
    val errors: List<String>
)