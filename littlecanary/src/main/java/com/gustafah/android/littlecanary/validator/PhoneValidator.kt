package com.gustafah.android.littlecanary.validator

import com.gustafah.android.littlecanary.formatter.Formatter
import com.gustafah.android.littlecanary.formatter.PhoneFormatter
import com.gustafah.android.littlecanary.validator.rules.LengthRule
import com.gustafah.android.littlecanary.validator.rules.Rule

object PhoneValidator : Validator() {

    override val formatter: Formatter
        get() = PhoneFormatter
    override val rules: List<Rule>
        get() = listOf(
            LengthRule(10, 11)
        )

}