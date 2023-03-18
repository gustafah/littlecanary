package com.gustafah.android.littlecanary.watcher

import android.graphics.Typeface
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
    var shouldFillBlanks = true
        private set

    abstract var clearPattern: Pattern
    abstract var watcherMask: String
    abstract val watcherValidation: Validator
    abstract val validation: (Boolean) -> Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        if (isUpdating) return
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
                    val isLastInput = editablePos >= editableLength
                    if (c !in validMaskChars) {
                        if (!isDeleting || !isLastInput) builder.append(c)
                        if (!isPasting) return@forEach
                    }
                    if (!isLastInput && !invalidChar) {
                        val newChar = input[editablePos]
                        if (isValidChar(newChar, c)) {
                            builder.append(newChar)
                            editablePos++
                        } else {
                            if (shouldFillBlanks) builder.append(fillerChar)
                            else return@breaking
                            invalidChar = true
                        }
                    } else {
                        if (shouldFillBlanks) builder.append(fillerChar)
                        else return@breaking
                    }
                }
            }
        }

        editable.replace(0, editable.length, builder)

        builder.toString().let {
            if (getLastActualCharPosition(it) == watcherMask.length)
                validation(watcherValidation.isValid(it))
            placeCursor(it)
        }

        isUpdating = false
    }

    internal open fun bindEditText(edt: EditText) {
        edt.typeface = Typeface.DEFAULT
        this.editText = edt
    }

    internal fun setShouldChangeTextInput(enable: Boolean) {
        this.shouldChangeTextInput = enable
    }

    internal fun setShouldFillBlanks(enable: Boolean) {
        this.shouldFillBlanks = enable
    }

    internal fun maskAsHint(): String {
        return watcherMask
            .replace(DIGIT_MASK, '_', false)
            .replace(CHAR_MASK, '_', false)
            .replace(DIGIT_CHAR_MASK, '_', false)
    }

    private fun getLastActualCharPosition(input: String): Int =
        if (!shouldFillBlanks)
            input.length
        else
            input.replace(fillerChar.toString(), "").let { text ->
                if (text.isEmpty()) return@let 0
                else if (!text.first().isLetterOrDigit())
                    return@let getLastActualCharPosition(input.drop(1)) + 1
                var shrink = 0
                while (!text[text.length.minus(1).minus(shrink)].isLetterOrDigit())
                    shrink++
                text.length - shrink
            }

    private fun isValidChar(newChar: Char, maskChar: Char) =
        (newChar.isDigit() && maskChar == DIGIT_MASK) ||
                (newChar.isLetter() && maskChar == CHAR_MASK) ||
                (newChar.isLetterOrDigit() && maskChar == DIGIT_CHAR_MASK)

    private fun placeCursor(input: String) {
        val placeCursorAt = if (shouldFillBlanks) getLastActualCharPosition(input)
        else getNextNonMaskPosition(input)
        editText?.setSelection(placeCursorAt)
    }

    private fun getNextNonMaskPosition(input: String): Int {
        return if (!shouldFillBlanks) {
            input
        } else {
            input.replace(fillerChar.toString(), "")
        }.let {
            if (it.isEmpty()) return@let 0

            if (it.length >= watcherMask.length) return it.length
            var cursorPos = it.length
            do {
                val charAtPos = watcherMask[cursorPos]
                cursorPos++
            } while (charAtPos !in validMaskChars)
            cursorPos--
            while (isDeleting && watcherMask[cursorPos - 1] !in validMaskChars) cursorPos--
            cursorPos
        }
    }

}