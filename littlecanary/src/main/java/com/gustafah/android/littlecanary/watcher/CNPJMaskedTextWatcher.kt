package com.gustafah.android.littlecanary.watcher

import android.text.InputType
import android.widget.EditText
import com.gustafah.android.littlecanary.common.Masks.CNPJ_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.common.ext.forceNumbers
import com.gustafah.android.littlecanary.validator.CNPJValidator
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

class CNPJMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = CNPJValidator
    override var clearPattern: Pattern = Patterns.NOT_DIGIT
    override var watcherMask: String = CNPJ_MASK

    override fun bindEditText(edt: EditText) {
        edt.inputType = if (shouldFillBlanks) InputType.TYPE_CLASS_TEXT.forceNumbers()
        else InputType.TYPE_CLASS_PHONE
        super.bindEditText(edt)
    }

}