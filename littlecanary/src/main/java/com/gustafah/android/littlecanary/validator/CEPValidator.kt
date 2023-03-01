package com.gustafah.android.littlecanary.validator

import com.gustafah.android.littlecanary.formatter.CEPFormatter
import com.gustafah.android.littlecanary.formatter.Formatter
import com.gustafah.android.littlecanary.validator.rules.LengthRule
import com.gustafah.android.littlecanary.validator.rules.Rule

object CEPValidator : Validator() {

    override val formatter: Formatter
        get() = CEPFormatter
    override val rules: List<Rule>
        get() = listOf(
            LengthRule(8, 8)
        )

}