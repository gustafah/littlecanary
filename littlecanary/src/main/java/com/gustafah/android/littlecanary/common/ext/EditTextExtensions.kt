package com.gustafah.android.littlecanary.common.ext

import android.widget.EditText
import com.gustafah.android.littlecanary.watcher.MaskedTextWatcher

fun EditText.addMaskedTextWatcher(
    maskedTextWatcher: MaskedTextWatcher,
    replaceHint: Boolean = false,
    shouldChangeTextInput: Boolean = true
) {
    maskedTextWatcher.bindEditText(this)
    maskedTextWatcher.shouldChangeTextInput(shouldChangeTextInput)

    if (this.hint.isNullOrEmpty() || replaceHint) {
        hint = maskedTextWatcher.maskAsHint()
    }
    addTextChangedListener(maskedTextWatcher)
}