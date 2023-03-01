package com.gustafah.android.littlecanary.formatter

import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.formatter.model.FormatterPattern

object PhoneFormatter : Formatter() {
    override val formatterPattern: FormatterPattern
        get() = Patterns.PHONE_PATTERN
}