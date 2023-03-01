package com.gustafah.android.littlecanary.validator.rules

class LengthRule(private val minLength: Int, private val maxLength: Int) : Rule() {

    override fun comply(reference: String) = comply(reference.length)

    private fun comply(length: Int) = length in minLength..maxLength

}