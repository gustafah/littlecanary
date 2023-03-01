package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.text.InputFilter
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.Validator

abstract class MaskedTextWatcher : android.text.TextWatcher {

    private val validMaskChars = listOf('#', 'L')

    private var isUpdating = false
    private var isDeleting = false
    private var isPasting = false

    protected var watcherMask: String = ""
    abstract val watcherValidation: Validator
    abstract val validation: (Boolean) -> Unit

    private val shouldIgnore
        get() = isUpdating || isDeleting

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after
        isPasting = after - 1 > count
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (shouldIgnore) return
        isUpdating = true

        val builder = StringBuilder()
        val input = Patterns.NOT_NUMBER.matcher(editable!!).replaceAll("")
        val editableLength = input.length

        if (editableLength > 0) {
            run breaking@{
                var editablePos = 0
                watcherMask.forEach { c ->
                    if (c != '#' && c != 'L') {
                        builder.append(c)
                        if (!isPasting)
                            return@forEach
                    }
                    if (editablePos >= editableLength) return@breaking

                    val newChar = input[editablePos]
                    if (
                        (newChar.isDigit() && c == '#') ||
                        (newChar.isLetter() && c == 'L') ||
                        (newChar.isLetterOrDigit() && c in validMaskChars)
                    ) {
                        builder.append(newChar)
                        editablePos++
                    }
                }
            }
        }

        editable.replace(0, editable.length, builder, 0, builder.length)

        if (builder.length == watcherMask.length)
            validation(watcherValidation.isValid(builder.toString()))

        isUpdating = false
    }
}