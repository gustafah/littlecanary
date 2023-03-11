package com.gustafah.android.littlecanary.validator

import com.gustafah.android.littlecanary.formatter.Formatter
import com.gustafah.android.littlecanary.formatter.PlateFormatter
import com.gustafah.android.littlecanary.validator.rules.LengthRule
import com.gustafah.android.littlecanary.validator.rules.Rule

object PlateValidator : Validator() {

    override val formatter: Formatter
        get() = PlateFormatter
    override val rules: List<Rule>
        get() = listOf(
            LengthRule(7, 7)
        )

}