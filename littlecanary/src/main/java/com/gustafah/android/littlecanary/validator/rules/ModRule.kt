package com.gustafah.android.littlecanary.validator.rules

import com.gustafah.android.littlecanary.common.ext.aggregate

class ModRule(
    private val digits: Int,
    private val modValue: Int,
    private val multipliers: IntRange,
    private val useDifference: Boolean,
    private val addIndividual: Boolean,
    private val modReplacement: Int,
    private val modToReplace: List<Int>
) : Rule() {

    override fun comply(reference: String): Boolean {
        var sequence = reference.substring(0, reference.length - digits)
        for (i in 0 until digits) {
            val digit = calculateDigit(sequence)
            sequence = sequence.plus(digit)
        }
        return sequence == reference
    }

    private fun calculateDigit(charSequence: String): Int {
        var sum = 0
        var multiplierPosition = 0
        charSequence.reversed().forEach { c ->
            val multiplier = multipliers.elementAt(multiplierPosition)
            val charInt = c.digitToInt()
            val stepSum = (charInt * multiplier).let { if (!addIndividual) it else it.aggregate() }
            sum += stepSum
            multiplierPosition = (multiplierPosition + 1).takeIf { it < multipliers.count() } ?: 0
        }
        val digit = (sum % modValue).let { if (!useDifference) it else modValue - it }
        return if (modToReplace.contains(digit)) modReplacement else digit
    }

}