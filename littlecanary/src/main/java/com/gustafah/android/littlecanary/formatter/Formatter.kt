package com.gustafah.android.littlecanary.formatter

import com.gustafah.android.littlecanary.formatter.model.FormatterPattern

abstract class Formatter {

    protected abstract val formatterPattern: FormatterPattern

    open fun format(unformatted: String, strict: Boolean = false) =
        if (!strict || (isFormattable(unformatted) && isUnformatted(unformatted)))
            formatterPattern.unformatPattern.toRegex().replace(
                unformatted,
                formatterPattern.formatReplacement
            )
        else
            throw IllegalArgumentException("$unformatted cannot be formatted")

    open fun unformat(formatted: String, strict: Boolean = false) =
        if (!strict || (isUnformattable(formatted) && isFormatted(formatted)))
            formatterPattern.formatPattern.toRegex().replace(
                formatted,
                formatterPattern.unformatReplacement
            )
        else
            throw IllegalArgumentException("$formatted cannot be unformatted")

    open fun isFormatted(formatted: String) =
        formatterPattern.formatPattern.matcher(formatted).matches()

    open fun isUnformatted(formatted: String) =
        formatterPattern.unformatPattern.matcher(formatted).matches()

    open fun isFormattable(unformatted: String) =
        formatterPattern.unformatPattern.matcher(unformatted).matches()

    open fun isUnformattable(formatted: String) =
        formatterPattern.formatPattern.matcher(formatted).matches()

}