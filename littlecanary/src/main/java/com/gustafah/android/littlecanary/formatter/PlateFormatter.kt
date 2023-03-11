package com.gustafah.android.littlecanary.formatter

import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.formatter.model.FormatterPattern

object PlateFormatter : Formatter() {
    override val formatterPattern: FormatterPattern
        get() = Patterns.PLATE_PATTERN
}