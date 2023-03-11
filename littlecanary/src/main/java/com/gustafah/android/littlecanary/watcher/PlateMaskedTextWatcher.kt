package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputFilter.AllCaps
import android.text.InputFilter.LengthFilter
import com.gustafah.android.littlecanary.common.Masks.PLATE_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.PlateValidator
import com.gustafah.android.littlecanary.validator.Validator

class PlateMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = PlateValidator

    override fun afterTextChanged(editable: Editable?) {
        watcherMask = PLATE_MASK
        clearPattern = Patterns.NOT_DIGIT_CHAR
        editable!!.filters = arrayOf(LengthFilter(watcherMask.length), AllCaps())
        super.afterTextChanged(editable)
    }

}