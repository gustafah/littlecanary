package com.gustafah.android.littlecanary.watcher

import com.gustafah.android.littlecanary.common.Masks.CPF_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.CPFValidator
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

class CPFMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = CPFValidator
    override var watcherMask = CPF_MASK
    override var clearPattern: Pattern = Patterns.NOT_DIGIT
}