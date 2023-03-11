package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import com.gustafah.android.littlecanary.common.Masks.CHAR_MASK
import com.gustafah.android.littlecanary.common.Masks.DIGIT_CHAR_MASK
import com.gustafah.android.littlecanary.common.Masks.DIGIT_MASK
import com.gustafah.android.littlecanary.common.Patterns
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

abstract class MaskedTextWatcher : android.text.TextWatcher {

    private val validMaskChars = listOf(DIGIT_MASK, CHAR_MASK, DIGIT_CHAR_MASK)

    private var isUpdating = false
    private var isDeleting = false
    private var isPasting = false

    protected var watcherMask: String = ""
    protected var clearPattern: Pattern = Patterns.NOT_DIGIT
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
        val input = clearPattern.matcher(editable!!).replaceAll("")
        val editableLength = input.length

        if (editableLength > 0) {
            run breaking@{
                var editablePos = 0
                watcherMask.forEach { c ->
                    if (c !in validMaskChars) {
                        builder.append(c)
                        if (!isPasting)
                            return@forEach
                    }
                    if (editablePos >= editableLength) return@breaking

                    val newChar = input[editablePos]
                    if (
                        (newChar.isDigit() && c == DIGIT_MASK) ||
                        (newChar.isLetter() && c == CHAR_MASK) ||
                        (newChar.isLetterOrDigit() && c == DIGIT_CHAR_MASK)
                    ) {
                        builder.append(newChar)
                        editablePos++
                    } else return@breaking
                }
            }
        }

        editable.replace(0, editable.length, builder, 0, builder.length)

        if (builder.length == watcherMask.length)
            validation(watcherValidation.isValid(builder.toString()))

        isUpdating = false
    }
}