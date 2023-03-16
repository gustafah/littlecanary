package com.gustafah.android.littlecanary.watcher

import android.text.Editable
import android.widget.EditText
import com.gustafah.android.littlecanary.common.Masks.CHAR_MASK
import com.gustafah.android.littlecanary.common.Masks.DIGIT_CHAR_MASK
import com.gustafah.android.littlecanary.common.Masks.DIGIT_MASK
import com.gustafah.android.littlecanary.validator.Validator
import java.util.regex.Pattern

abstract class MaskedTextWatcher : android.text.TextWatcher {

    private val validMaskChars = listOf(DIGIT_MASK, CHAR_MASK, DIGIT_CHAR_MASK)
    private val fillerChar = '_'

    private var isUpdating = false
    private var isDeleting = false
    private var isPasting = false
    private var shouldChangeTextInput = true
    private var editText: EditText? = null

    abstract var clearPattern: Pattern
    abstract var watcherMask: String
    abstract val watcherValidation: Validator
    abstract val validation: (Boolean) -> Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        isDeleting = count > after
        isPasting = after - 1 > count
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (isUpdating) return
        isUpdating = true

        val builder = StringBuilder()
        val input = clearPattern.matcher(editable!!).replaceAll("")
        val editableLength = input.length

        if (editableLength > 0) {
            run breaking@{
                var editablePos = 0
                var invalidChar = false
                watcherMask.forEach { c ->
                    if (c !in validMaskChars) {
                        builder.append(c)
                        if (!isPasting)
                            return@forEach
                    }
                    if (editablePos < editableLength && !invalidChar) {
                        val newChar = input[editablePos]
                        if (isValidChar(newChar, c)) {
                            builder.append(newChar)
                            editablePos++
                        } else {
                            builder.append(fillerChar)
                            invalidChar = true
                        }
                    } else {
                        builder.append(fillerChar)
                    }
                }
            }
        }

        editable.replace(0, editable.length, builder, 0, builder.length)

        builder.toString().let {
            if (getLastActualCharPosition(it) == watcherMask.length)
                validation(watcherValidation.isValid(it))
            placeCursor(it)
        }

        isUpdating = false
    }

    internal fun bindEditText(edt: EditText) {
        this.editText = edt
    }

    internal fun shouldChangeTextInput(enable: Boolean) {
        this.shouldChangeTextInput = enable
    }

    internal fun maskAsHint(): String {
        return watcherMask
            .replace(DIGIT_MASK, '_', false)
            .replace(CHAR_MASK, '_', false)
            .replace(DIGIT_CHAR_MASK, '_', false)
    }

    private fun getLastActualCharPosition(input: String): Int =
        input.replace(fillerChar.toString(), "").let { unfilled ->
            if (unfilled.isEmpty()) return@let 0
            else if (!unfilled.first().isLetterOrDigit())
                return@let getLastActualCharPosition(input.drop(1)) + 1
            var shrink = 0
            while (!unfilled[unfilled.length.minus(1).minus(shrink)].isLetterOrDigit())
                shrink++
            unfilled.length - shrink
        }

    private fun isValidChar(newChar: Char, maskChar: Char) =
        (newChar.isDigit() && maskChar == DIGIT_MASK) ||
                (newChar.isLetter() && maskChar == CHAR_MASK) ||
                (newChar.isLetterOrDigit() && maskChar == DIGIT_CHAR_MASK)

    private fun placeCursor(input: String) {
        editText?.setSelection(getLastActualCharPosition(input))
    }
}