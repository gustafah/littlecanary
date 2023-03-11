package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputFilter
import com.gustafah.android.littlecanary.common.Masks.PHONE_EIGHT_MASK
import com.gustafah.android.littlecanary.common.Masks.PHONE_NINE_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.PhoneValidator
import com.gustafah.android.littlecanary.validator.Validator

class PhoneMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = PhoneValidator

    override fun afterTextChanged(editable: Editable?) {
        editable!!.filters = arrayOf(InputFilter.LengthFilter("(##) #####-####".length))
        watcherMask =
            if (Patterns.NOT_DIGIT.matcher(editable).replaceAll("").length > 10)
                PHONE_NINE_MASK
            else
                PHONE_EIGHT_MASK
        super.afterTextChanged(editable)
    }

}