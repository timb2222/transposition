package com.example.unit

import com.example.validator.MAX_SEMITONES
import java.lang.Math.abs

fun getIndex(octave: Int, note: Int): Int {
    return if (octave < 0) ((octave + 1) * MAX_SEMITONES) - (MAX_SEMITONES - note + 1)
    else octave * MAX_SEMITONES + note
}

fun getNote(index: Int): List<Int> {
    return if (index < 0) getNegativeNote(index)
    else getPositiveNote(index)
}


private fun getNegativeNote(index: Int): List<Int> {
    val octave = if (index % MAX_SEMITONES != 0) index / MAX_SEMITONES - 1 else index / MAX_SEMITONES
    val note =
        if (index % MAX_SEMITONES != 0) MAX_SEMITONES - abs(index % MAX_SEMITONES + 1)
        else 1

    return listOf(octave, note)
}

private fun getPositiveNote(index: Int): List<Int> {
    val octave = if (index % MAX_SEMITONES == 0) index / MAX_SEMITONES - 1 else index / MAX_SEMITONES
    val note = if (index % MAX_SEMITONES == 0) MAX_SEMITONES else index % MAX_SEMITONES

    return listOf(octave, note)
}