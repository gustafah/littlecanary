package com.gustafah.android.littlecanary.validator.rules

abstract class Rule {

    abstract fun comply(reference: String): Boolean

}