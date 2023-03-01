package com.gustafah.android.littlecanary.validator

import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.formatter.CPFFormatter
import com.gustafah.android.littlecanary.formatter.Formatter
import com.gustafah.android.littlecanary.validator.rules.LengthRule
import com.gustafah.android.littlecanary.validator.rules.ModRule
import com.gustafah.android.littlecanary.validator.rules.PatternRule
import com.gustafah.android.littlecanary.validator.rules.Rule

object CPFValidator : Validator() {

    override val rules: List<Rule>
        get() = listOf(
            PatternRule(Patterns.NUMBER),
            LengthRule(11, 11),
            ModRule(
                digits = 2,
                modValue = 11,
                multipliers = 2..11,
                useDifference = true,
                addIndividual = false,
                modReplacement = 0,
                modToReplace = listOf(10, 11)
            )
        )

    override val formatter: Formatter
        get() = CPFFormatter

}