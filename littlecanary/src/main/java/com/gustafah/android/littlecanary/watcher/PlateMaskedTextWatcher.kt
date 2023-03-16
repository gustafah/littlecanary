package com.gustafah.android.littlecanary.watcher

import com.gustafah.android.littlecanary.common.Masks.PLATE_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.PlateValidator
import com.gustafah.android.littlecanary.validator.Validator

class PlateMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = PlateValidator
    override var watcherMask = PLATE_MASK
    override var clearPattern = Patterns.NOT_DIGIT_CHAR

}