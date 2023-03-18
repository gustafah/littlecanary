package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputType
import android.widget.EditText
import com.gustafah.android.littlecanary.common.Masks.PHONE_EIGHT_MASK
import com.gustafah.android.littlecanary.common.Masks.PHONE_NINE_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.common.ext.forceNumbers
import com.gustafah.android.littlecanary.validator.PhoneValidator
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

class PhoneMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = PhoneValidator
    override var clearPattern: Pattern = Patterns.NOT_DIGIT
    override var watcherMask = PHONE_EIGHT_MASK

    override fun bindEditText(edt: EditText) {
        edt.inputType = if (shouldFillBlanks) InputType.TYPE_CLASS_TEXT.forceNumbers()
        else InputType.TYPE_CLASS_PHONE
        super.bindEditText(edt)
    }

    override fun afterTextChanged(editable: Editable?) {
        watcherMask =
            if (Patterns.NOT_DIGIT.matcher(editable!!).replaceAll("").length > 10)
                PHONE_NINE_MASK
            else
                PHONE_EIGHT_MASK
        super.afterTextChanged(editable)
    }

}