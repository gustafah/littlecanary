package com.gustafah.android.littlecanary.formatter

import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.formatter.model.FormatterPattern

object CNPJFormatter : Formatter() {
    override val formatterPattern: FormatterPattern
        get() = Patterns.CNPJ_PATTERN
}