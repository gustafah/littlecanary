package com.gustafah.android.littlecanary.formatter.model

import java.util.regex.Pattern

class FormatterPattern(
    val formatPattern: Pattern,
    val unformatPattern: Pattern,
    val formatReplacement: String,
    val unformatReplacement: String
)