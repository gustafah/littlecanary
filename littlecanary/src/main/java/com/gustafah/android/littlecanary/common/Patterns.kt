package com.gustafah.android.littlecanary.common

import com.gustafah.android.littlecanary.formatter.model.FormatterPattern

object Patterns {

    //region Basic Patterns
    val NUMBER = "\\d*".toPattern()
    val NOT_NUMBER = "\\D*".toPattern()
    //endregion

    //region Formats Patterns
    private val CPF_FORMAT = "(\\d{3}).(\\d{3}).(\\d{3})-(\\d{2})".toPattern()
    private val CPF_UNFORMAT = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})".toPattern()
    private val CNPJ_FORMAT = "(\\d{2}).(\\d{3}).(\\d{3})/(\\d{4})-(\\d{2})".toPattern()
    private val CNPJ_UNFORMAT = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})".toPattern()
    private val CEP_FORMAT = "(\\d{5})-(\\d{3})".toPattern()
    private val CEP_UNFORMAT = "(\\d{5})(\\d{3})".toPattern()
    private val PHONE_FORMAT = "\\((\\d{2})\\)\\s(\\d{4,5})-(\\d{4})".toPattern()
    private val PHONE_UNFORMAT = "(\\d{2})(\\d{4,5})(\\d{4})".toPattern()

    val CPF_PATTERN = FormatterPattern(
        formatPattern = CPF_FORMAT,
        unformatPattern = CPF_UNFORMAT,
        formatReplacement = "$1.$2.$3-$4",
        unformatReplacement = "$1$2$3$4"
    )
    val CNPJ_PATTERN = FormatterPattern(
        formatPattern = CNPJ_FORMAT,
        unformatPattern = CNPJ_UNFORMAT,
        formatReplacement = "$1.$2.$3/$4-$5",
        unformatReplacement = "$1$2$3$4$5"
    )
    val CEP_PATTERN = FormatterPattern(
        formatPattern = CEP_FORMAT,
        unformatPattern = CEP_UNFORMAT,
        formatReplacement = "$1-$2",
        unformatReplacement = "$1$2"
    )
    val PHONE_PATTERN = FormatterPattern(
        formatPattern = PHONE_FORMAT,
        unformatPattern = PHONE_UNFORMAT,
        formatReplacement = "($1) $2-$3",
        unformatReplacement = "$1$2$3"
    )
    //endregion

}