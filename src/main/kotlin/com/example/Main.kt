package com.example

import com.example.service.TranspositionService


fun main() {
    val transpositionService = TranspositionService()
    println("Welcome to Transposition tool")
    println("This tool will transpose your music piece into different tone")
    println("Please input the notes in JSON format like [[2,1],[2,6]]")
    val json = readLine() ?: throw IllegalArgumentException("No notes were entered")
    println("Please input the semitone number to transpose to. May be negative")
    val transpose = readLine()?.toInt() ?: throw IllegalArgumentException("No notes were entered")
    val result = transpositionService.transposeAndParse(json, transpose)
    println("Result: $result")
}