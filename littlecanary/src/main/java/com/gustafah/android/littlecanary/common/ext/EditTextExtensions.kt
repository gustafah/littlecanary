package com.gustafah.android.littlecanary.common.ext

import android.widget.EditText
import com.gustafah.android.littlecanary.watcher.MaskedTextWatcher

fun EditText.addMaskedTextWatcher(
    maskedTextWatcher: MaskedTextWatcher,
    replaceHint: Boolean = false,
    shouldChangeTextInput: Boolean = true,
    shouldFillBlanks: Boolean = false
) {
    maskedTextWatcher.bindEditText(this)
    maskedTextWatcher.setShouldFillBlanks(shouldFillBlanks)
    maskedTextWatcher.setShouldChangeTextInput(shouldChangeTextInput)

    if (this.hint.isNullOrEmpty() || replaceHint) {
        hint = maskedTextWatcher.maskAsHint()
    }
    addTextChangedListener(maskedTextWatcher)
}