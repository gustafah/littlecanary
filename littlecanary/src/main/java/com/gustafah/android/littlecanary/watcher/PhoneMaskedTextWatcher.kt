package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputFilter
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.PhoneValidator
import com.gustafah.android.littlecanary.validator.Validator

class PhoneMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = PhoneValidator

    override fun afterTextChanged(editable: Editable?) {
        editable!!.filters = arrayOf(InputFilter.LengthFilter("(##) #####-####".length))
        watcherMask =
            if (Patterns.NOT_NUMBER.matcher(editable).replaceAll("").length > 10)
                "(##) #####-####"
            else
                "(##) ####-####"
        super.afterTextChanged(editable)
    }

}