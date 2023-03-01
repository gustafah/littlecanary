package com.gustafah.android.littlecanary.formatter

import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.formatter.model.FormatterPattern

object CEPFormatter : Formatter() {
    override val formatterPattern: FormatterPattern
        get() = Patterns.CEP_PATTERN
}