package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputFilter
import com.gustafah.android.littlecanary.validator.CNPJValidator
import com.gustafah.android.littlecanary.validator.Validator

class CNPJMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = CNPJValidator

    override fun afterTextChanged(editable: Editable?) {
        watcherMask = "##.###.###/####-##"
        editable!!.filters = arrayOf(InputFilter.LengthFilter(watcherMask.length))
        super.afterTextChanged(editable)
    }

}