package com.gustafah.android.littlecanary.watcher

import android.text.InputFilter
import android.text.InputType
import android.widget.EditText
import com.gustafah.android.littlecanary.common.Masks.PLATE_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.PlateValidator
import com.gustafah.android.littlecanary.validator.Validator

class PlateMaskedTextWatcher(override val validation: (Boolean) -> Unit) : MaskedTextWatcher() {

    override val watcherValidation: Validator
        get() = PlateValidator
    override var watcherMask = PLATE_MASK
    override var clearPattern = Patterns.NOT_DIGIT_CHAR

    override fun bindEditText(edt: EditText) {
        edt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        edt.filters = arrayOf(InputFilter.AllCaps())
        super.bindEditText(edt)
    }

}