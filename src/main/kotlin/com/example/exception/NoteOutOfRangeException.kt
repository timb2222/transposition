package com.example.exception

import com.example.validator.ValidationReport

class NoteOutOfRangeException(message: String, val validationReport: ValidationReport): Exception(message)