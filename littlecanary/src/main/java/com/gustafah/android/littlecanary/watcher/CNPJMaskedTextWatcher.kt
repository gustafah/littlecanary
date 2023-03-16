package com.gustafah.android.littlecanary.watcher

import com.gustafah.android.littlecanary.common.Masks.CNPJ_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.CNPJValidator
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

class CNPJMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = CNPJValidator
    override var clearPattern: Pattern = Patterns.NOT_DIGIT
    override var watcherMask: String = CNPJ_MASK

}