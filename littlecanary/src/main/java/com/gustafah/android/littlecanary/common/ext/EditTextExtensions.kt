package com.gustafah.android.littlecanary.common.ext

import android.widget.EditText
import com.gustafah.android.littlecanary.watcher.MaskedTextWatcher
import com.gustafah.android.littlecanary.watcher.model.ReflectionTextWatcher

fun EditText.setMaskedTextWatcher(
    maskedTextWatcher: MaskedTextWatcher,
    replaceHint: Boolean = false,
    shouldChangeTextInput: Boolean = true,
    shouldFillBlanks: Boolean = false
) {
    removeAllTextWatcher()
    addMaskedTextWatcher(maskedTextWatcher, replaceHint, shouldChangeTextInput, shouldFillBlanks)
}

fun EditText.addMaskedTextWatcher(
    maskedTextWatcher: MaskedTextWatcher,
    replaceHint: Boolean = false,
    shouldChangeTextInput: Boolean = true,
    shouldFillBlanks: Boolean = false
) {
    maskedTextWatcher.setShouldFillBlanks(shouldFillBlanks)
    maskedTextWatcher.bindEditText(this)
    maskedTextWatcher.setShouldChangeTextInput(shouldChangeTextInput)

    if (this.hint.isNullOrEmpty() || replaceHint) {
        hint = maskedTextWatcher.maskAsHint()
    }
    addTextChangedListener(maskedTextWatcher)
}

private fun EditText.removeAllTextWatcher() {
    ReflectionTextWatcher.removeAll(this)
}