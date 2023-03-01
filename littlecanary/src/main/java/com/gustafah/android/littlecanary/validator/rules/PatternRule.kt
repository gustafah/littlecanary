package com.gustafah.android.littlecanary.validator.rules

import java.util.regex.Pattern

class PatternRule(private val pattern: Pattern) : Rule() {

    override fun comply(reference: String) = pattern.matcher(reference).matches()

}