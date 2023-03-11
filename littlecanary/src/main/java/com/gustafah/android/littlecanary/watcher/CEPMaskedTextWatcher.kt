package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputFilter
import com.gustafah.android.littlecanary.common.Masks.CEP_MASK
import com.gustafah.android.littlecanary.validator.CEPValidator
import com.gustafah.android.littlecanary.validator.Validator

class CEPMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = CEPValidator

    override fun afterTextChanged(editable: Editable?) {
        watcherMask = CEP_MASK
        editable!!.filters = arrayOf(InputFilter.LengthFilter(watcherMask.length))
        super.afterTextChanged(editable)
    }

}