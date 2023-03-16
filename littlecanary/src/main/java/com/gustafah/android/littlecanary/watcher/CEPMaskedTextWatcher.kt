package com.gustafah.android.littlecanary.watcher

import com.gustafah.android.littlecanary.common.Masks.CEP_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.CEPValidator
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

class CEPMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = CEPValidator

    override var clearPattern: Pattern = Patterns.NOT_DIGIT
    override var watcherMask = CEP_MASK

}