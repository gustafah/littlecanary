package com.gustafah.android.littlecanary.validator

import com.gustafah.android.littlecanary.formatter.Formatter
import com.gustafah.android.littlecanary.validator.rules.Rule

abstract class Validator {

    protected abstract val formatter: Formatter
    protected abstract val rules: List<Rule>

    open fun isValid(validate: String): Boolean =
        formatter.unformat(validate).let { unformatted ->
            rules.all { rule -> rule.comply(unformatted) }
        }
}